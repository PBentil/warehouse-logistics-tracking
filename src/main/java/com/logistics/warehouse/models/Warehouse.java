package com.logistics.warehouse.models;

import jakarta.persistence.*;

@Entity
@Table(name ="warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn(name ="manager_id")
    private User manager;

    public Warehouse() {
    }

    public Warehouse(Long warehouseId, String name, String location, User manager) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
        this.manager = manager;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }


    }

