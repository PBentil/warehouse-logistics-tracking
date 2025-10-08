package com.logistics.warehouse.DTO.shipment;

public class LocationUpdateRequest {
    private String currentLocation;

    public LocationUpdateRequest() {
    }

    public LocationUpdateRequest(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
