package com.logistics.warehouse.DTO.shipment;

public class ShipmentRequest {
    private Long driverId;
    private String driverName;
    private String driverContact;
    private String vehicleNumber;

    public ShipmentRequest() {
    }

    public ShipmentRequest(Long driverId, String driverName, String driverContact, String vehicleNumber) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.vehicleNumber = vehicleNumber;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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
}
