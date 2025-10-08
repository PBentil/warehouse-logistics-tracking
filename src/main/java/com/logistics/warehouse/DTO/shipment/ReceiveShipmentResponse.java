package com.logistics.warehouse.DTO.shipment;

import java.time.LocalDateTime;
import java.util.List;

public class ReceiveShipmentResponse {
    private Long shipmentId;
    private String string;
    private String warehouseName;
    private LocalDateTime receivedAt;
    private List<ReceiveItemResponse> items;


    public ReceiveShipmentResponse() {
    }

    public ReceiveShipmentResponse(Long shipmentId, String string, String warehouseName, LocalDateTime receivedAt, List<ReceiveItemResponse> items) {
        this.shipmentId = shipmentId;
        this.string = string;
        this.warehouseName = warehouseName;
        this.receivedAt = receivedAt;
        this.items = items;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public List<ReceiveItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ReceiveItemResponse> items) {
        this.items = items;
    }
}
