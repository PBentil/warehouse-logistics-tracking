package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
