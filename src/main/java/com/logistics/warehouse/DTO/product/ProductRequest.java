package com.logistics.warehouse.DTO.product;

public class ProductRequest {
    private String name;
    private String description;
    private String sku;
    private String category;
    private Double unitPrice;
    private Long warehouseId;

    public ProductRequest(String name, String description, String sku, String category, Double unitPrice, Long warehouseId) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.category = category;
        this.unitPrice = unitPrice;
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
