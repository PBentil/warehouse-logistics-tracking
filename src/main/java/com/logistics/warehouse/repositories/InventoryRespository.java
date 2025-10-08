package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Inventory;
import com.logistics.warehouse.models.Product;
import com.logistics.warehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRespository  extends JpaRepository<Inventory , Long> {
    List<Inventory> findByProduct_Name(String productName);
    List<Inventory> findByWarehouse_WarehouseId(Long warehouseId);

    @Query("SELECT COALESCE(SUM(i.quantity),0) FROM Inventory i WHERE i.warehouse = :warehouse")
    long sumQuantityByWarehouse(@Param("warehouse") Warehouse warehouse);

    Optional<Inventory> findByWarehouseAndProductAndBinLocation (
            Warehouse warehouse,
            Product product,
            String binLocation
    );
}
