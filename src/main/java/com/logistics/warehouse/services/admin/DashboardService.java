package com.logistics.warehouse.services.admin;

import com.logistics.warehouse.DTO.admin.DashboardSummaryDTO;
import com.logistics.warehouse.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final UserRepository userRepository;
    private final InventoryRespository inventoryRespository;
    private final OrderRespository orderRespository;
    private final ShipmentRepository shipmentRepository;
    private final InboundOrderRepository inboundOrderRepository;
    private final WarehouseRespository warehouseRepository;
    private final ProductRespository productRepository;
    private final SupplierRespository supplierRespository;

    public DashboardService(UserRepository userRepository, InventoryRespository inventoryRespository, OrderRespository orderRespository, ShipmentRepository shipmentRepository, InboundOrderRepository inboundOrderRepository, WarehouseRespository warehouseRepository, ProductRespository productRepository, SupplierRespository supplierRespository) {
        this.userRepository = userRepository;
        this.inventoryRespository = inventoryRespository;
        this.orderRespository = orderRespository;
        this.shipmentRepository = shipmentRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.supplierRespository = supplierRespository;
    }

    public DashboardSummaryDTO getDashboardSummary() {
        long totalUsers = userRepository.count();
        long totalInventories = inventoryRespository.count();
        long totalOrders = orderRespository.count();
        long totalDeliveries = shipmentRepository.count();
        long inboundOrders = inboundOrderRepository.count();
        long suppliers = supplierRespository.count();
        long warehouses = warehouseRepository.count();
        long products = productRepository.count();

        return new DashboardSummaryDTO(
                totalUsers,
                totalInventories,
                totalOrders,
                totalDeliveries,
                inboundOrders,
                suppliers,
                warehouses,
                products
        );
    }
}
