package com.logistics.warehouse.DTO.warehouse;

public class WarehouseRequest {
    private String name;
    private String location;
    private Long managerId;

    public WarehouseRequest(String name, String location, Long managerId) {
        this.name = name;
        this.location = location;
        this.managerId = managerId;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
