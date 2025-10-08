package com.logistics.warehouse.DTO.shipment;

import java.util.List;

public class ReceiveShipmentRequest {
    private Long shipmentId;
    private List<ReceiveItemRequest> items;

    public ReceiveShipmentRequest() {
    }

    public ReceiveShipmentRequest(Long shipmentId, List<ReceiveItemRequest> items) {
        this.shipmentId = shipmentId;
        this.items = items;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<ReceiveItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ReceiveItemRequest> items) {
        this.items = items;
    }
}
