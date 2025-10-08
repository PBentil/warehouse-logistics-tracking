package com.logistics.warehouse.DTO.shipment;

import java.time.LocalDateTime;

public class ShipmentResponse {
    private Long id;
    private Long orderId;
    private String driverName;
    private String driverContact;
    private String vehicleNumber;
    private  String currentLocation;
    private String status;
    private LocalDateTime updatedAt;


    public ShipmentResponse() {
    }

    public ShipmentResponse(Long id, Long orderId, String driverName, String driverContact, String vehicleNumber, String currentLocation, String status, LocalDateTime updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.vehicleNumber = vehicleNumber;
        this.currentLocation = currentLocation;
        this.status = status;
        this.updatedAt = updatedAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
