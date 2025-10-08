package com.logistics.warehouse.services.manager;


import com.logistics.warehouse.DTO.manager.ManagerDashboardDTO;
import com.logistics.warehouse.models.Warehouse;
import com.logistics.warehouse.repositories.InboundOrderRepository;
import com.logistics.warehouse.repositories.InventoryRespository;
import com.logistics.warehouse.repositories.ProductRespository;
import com.logistics.warehouse.repositories.WarehouseRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class managerDashboardService {
    private final WarehouseRespository warehouseRespository;
    private final ProductRespository productRespository;
    private final InventoryRespository inventoryRespository;
    private final InboundOrderRepository inboundOrderRepository;

    public managerDashboardService(WarehouseRespository warehouseRespository, ProductRespository productRespository, InventoryRespository inventoryRespository, InboundOrderRepository inboundOrderRepository) {
        this.warehouseRespository = warehouseRespository;
        this.productRespository = productRespository;
        this.inventoryRespository = inventoryRespository;
        this.inboundOrderRepository = inboundOrderRepository;
    }

    public ManagerDashboardDTO getDashboard(Long managerId) {
        List<Warehouse> warehouses = warehouseRespository.findByManager_Id(managerId);

        if(warehouses.isEmpty()){
            throw new RuntimeException("No warehouse assigned to this manager");
        }

        long totalProducts = warehouses.stream()
                .mapToLong(productRespository::countByWarehouse)
                .sum();
        long totalInventory = warehouses.stream()
                .mapToLong(inventoryRespository::sumQuantityByWarehouse)
                .sum();
        long inboundOrders = warehouses.stream()
                .mapToLong(inboundOrderRepository::countByWarehouse)
                .sum();

        return  new ManagerDashboardDTO(totalProducts,totalInventory, inboundOrders);
    }
}
