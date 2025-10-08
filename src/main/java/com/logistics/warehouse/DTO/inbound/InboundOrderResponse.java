package com.logistics.warehouse.DTO.inbound;

import java.time.LocalDateTime;
import java.util.List;

public class InboundOrderResponse {
    private Long id;
    private String supplierName;
    private String warehouseName;
    private String status;
    private LocalDateTime createdAt;
    private List<InboundOrderItemResponse> items;
    private String driverName;
    private String driverContact;
    private String vehicleNumber;
    private String shipmentStatus;

    public InboundOrderResponse() {
    }

    public InboundOrderResponse(Long id, String supplierName, String warehouseName, String status, LocalDateTime createdAt, List<InboundOrderItemResponse> items, String driverName, String driverContact, String vehicleNumber, String shipmentStatus) {
        this.id = id;
        this.supplierName = supplierName;
        this.warehouseName = warehouseName;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.vehicleNumber = vehicleNumber;
        this.shipmentStatus = shipmentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<InboundOrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<InboundOrderItemResponse> items) {
        this.items = items;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
