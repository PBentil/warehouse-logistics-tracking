package com.logistics.warehouse.DTO.inventory;

public class InventoryRequest {
    private Long warehouseId;
    private Long productId;
    private Integer quantity;
    private String binLocation;

    public InventoryRequest(Long warehouseId, Long productId, Integer quantity, String binLocation) {
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.quantity = quantity;
        this.binLocation = binLocation;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
}
