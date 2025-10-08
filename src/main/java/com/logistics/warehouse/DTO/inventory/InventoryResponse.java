package com.logistics.warehouse.DTO.inventory;

import java.time.LocalDateTime;

public class InventoryResponse {
    private Long id;
    private String warehouse;
    private String product;
    private Integer quantity;
    private String binLocation;
    private LocalDateTime lastUpdated;

    public InventoryResponse(Long id, String warehouse, String product, Integer quantity, String binLocation, LocalDateTime lastUpdated) {
        this.id = id;
        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;
        this.binLocation = binLocation;
        this.lastUpdated = lastUpdated;
    }

    public InventoryResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
