package com.logistics.warehouse.DTO.inbound;

import java.util.List;

public class InboundOrderRequest {
    private Long supplierId;
    private Long warehouseId;
    private String status;
    private List<InboundOrderItemRequest> items;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InboundOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<InboundOrderItemRequest> items) {
        this.items = items;
    }
}
