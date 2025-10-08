package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Order ,Long> {
    List<Order> findByStatus(String status);
}
