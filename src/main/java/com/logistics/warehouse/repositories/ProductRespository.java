package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Product;
import com.logistics.warehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<Product , Long> {
    boolean existsBySku(String sku);
    long countByWarehouse(Warehouse warehouse);
}
