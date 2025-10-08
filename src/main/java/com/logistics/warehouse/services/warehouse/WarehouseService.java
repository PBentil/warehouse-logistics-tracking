package com.logistics.warehouse.services.warehouse;


import com.logistics.warehouse.DTO.warehouse.WarehouseRequest;
import com.logistics.warehouse.DTO.warehouse.WarehouseResponse;
import com.logistics.warehouse.models.User;
import com.logistics.warehouse.models.Warehouse;
import com.logistics.warehouse.repositories.UserRepository;
import com.logistics.warehouse.repositories.WarehouseRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    private final WarehouseRespository warehouseRespository;
    private final UserRepository userRepository;
    public WarehouseService(WarehouseRespository warehouseRespository, UserRepository userRepository) {
        this.warehouseRespository = warehouseRespository;
        this.userRepository = userRepository;
    }
    public WarehouseResponse createWarehouse(WarehouseRequest request) {
        User manager = userRepository.findWarehouseManagerById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found or Invalid role"));
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());
        warehouse.setManager(manager);
        Warehouse saved = warehouseRespository.save(warehouse);
        return mapToResponse(saved);
    }
    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseRespository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public WarehouseResponse getWarehouse(Long id) {
        Warehouse wh = warehouseRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return mapToResponse(wh);
    }
    public WarehouseResponse updateWarehouse(Long id, WarehouseRequest request) {
        Warehouse wh = warehouseRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        wh.setName(request.getName());
        wh.setLocation(request.getLocation());
        wh.setManager(manager);
        Warehouse updated = warehouseRespository.save(wh);
        return mapToResponse(updated);
    }
    public void deleteWarehouse(Long id) {
        warehouseRespository.deleteById(id);
    }
    private WarehouseResponse mapToResponse(Warehouse warehouse) {
        WarehouseResponse response = new WarehouseResponse();
        response.setId(warehouse.getWarehouseId());
        response.setName(warehouse.getName());
        response.setLocation(warehouse.getLocation());
        response.setManager(warehouse.getManager() != null ? warehouse.getManager().getFullName() : null);
        return response;
    }
}
