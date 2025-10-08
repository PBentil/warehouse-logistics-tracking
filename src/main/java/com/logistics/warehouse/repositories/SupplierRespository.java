package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRespository  extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByName(String name);
    Optional<Supplier> findByContactInfo(String contactInfo);
    Optional<Supplier> findByUser_Id(Long user_id);
}
