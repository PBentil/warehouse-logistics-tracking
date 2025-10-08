package com.logistics.warehouse.repositories;

import com.logistics.warehouse.models.InboundOrder;
import com.logistics.warehouse.models.OrderStatus;
import com.logistics.warehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.function.LongFunction;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
    long countByWarehouse(Warehouse warehouse);
    List<InboundOrder> findByWarehouse_WarehouseId(Long warehouseId);

    @Query("SELECT o FROM InboundOrder o WHERE o.supplier.id = :supplierId AND o.status = :status")
    List<InboundOrder> findBySupplierAndStatus(@Param("supplierId") Long supplierId,
                                               @Param("status") OrderStatus status);
    List<InboundOrder> findBySupplier_SupplierIdAndStatus(Long supplierId, OrderStatus status);


}
