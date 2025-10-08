package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemsRespository extends JpaRepository <OrderItems ,Long> {
}
