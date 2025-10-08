package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
}
