package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface WarehouseRespository extends JpaRepository<Warehouse,Long> {
    List<Warehouse> findByManager_Id(Long managerId);
}
