package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository  extends JpaRepository<Shipment , Long> {
    List<Shipment> findByDriver_Id(Long userId);

}
