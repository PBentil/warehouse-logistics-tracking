package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.InboundOrder;
import com.logistics.warehouse.models.InboundOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InboundOrderItemRespository extends JpaRepository<InboundOrderItem , Long> {
List<InboundOrderItem> findByInboundOrder(InboundOrder inboundOrder);
}
