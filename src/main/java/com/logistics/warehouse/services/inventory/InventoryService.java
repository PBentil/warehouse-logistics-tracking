package com.logistics.warehouse.services.inventory;

import com.logistics.warehouse.DTO.inventory.InventoryRequest;
import com.logistics.warehouse.DTO.inventory.InventoryResponse;
import com.logistics.warehouse.models.Inventory;
import com.logistics.warehouse.models.Product;
import com.logistics.warehouse.models.Warehouse;
import com.logistics.warehouse.repositories.InventoryRespository;
import com.logistics.warehouse.repositories.ProductRespository;
import com.logistics.warehouse.repositories.WarehouseRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRespository inventoryRespository;
    private final ProductRespository productRespository;
    private final WarehouseRespository warehouseRespository;

    public InventoryService(InventoryRespository inventoryRespository, ProductRespository productRespository, WarehouseRespository warehouseRespository) {
        this.inventoryRespository = inventoryRespository;
        this.productRespository = productRespository;
        this.warehouseRespository = warehouseRespository;
    }

    public InventoryResponse addInventory(InventoryRequest request){
        Warehouse warehouse = warehouseRespository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Product product = productRespository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = new Inventory(
                warehouse,
                product,
                request.getQuantity(),
                request.getBinLocation()
        );

        Inventory saved = inventoryRespository.save(inventory);
        return mapToResponse(saved);
    }

    public List<InventoryResponse> getAllInventory() {
        return inventoryRespository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public InventoryResponse getInventory(Long id) {
        Inventory inv = inventoryRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return mapToResponse(inv);
    }

    public InventoryResponse updateInventory(Long id, InventoryRequest request) {
        Inventory inv = inventoryRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inv.setQuantity(request.getQuantity());
        inv.setBinLocation(request.getBinLocation());
        Inventory updated = inventoryRespository.save(inv);

        return mapToResponse(updated);
    }


    public void deleteInventory(Long id) {
        inventoryRespository.deleteById(id);
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        InventoryResponse response = new InventoryResponse();
        response.setId(inventory.getId());
        response.setWarehouse(inventory.getWarehouse().getName());
        response.setProduct(inventory.getProduct().getName());
        response.setQuantity(inventory.getQuantity());
        response.setBinLocation(inventory.getBinLocation());
        response.setLastUpdated(inventory.getLastUpdated());
        return response;
    }
}
