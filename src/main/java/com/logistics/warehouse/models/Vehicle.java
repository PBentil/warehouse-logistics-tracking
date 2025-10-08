package com.logistics.warehouse.models;


import jakarta.persistence.*;

@Entity
@Table( name= "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String plateNumber;
    private String type;
    private Double capacity;

    @ManyToOne
    @JoinColumn( name = "driver_id")
    private User driver;

    public Vehicle(Long vehicleId, String plateNumber, String type, Double capacity, User driver) {
        this.vehicleId = vehicleId;
        this.plateNumber = plateNumber;
        this.type = type;
        this.capacity = capacity;
        this.driver = driver;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
