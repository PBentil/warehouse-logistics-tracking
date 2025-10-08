package com.logistics.warehouse.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;
    private String name;
    private String description;
    private String sku;
    private String category;
    private Double unitPrice;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Product() {
    }

    public Product(Long productid, String name, String description, String sku, String category, Double unitPrice, LocalDateTime createdAt, Warehouse warehouse) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.category = category;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
        this.warehouse = warehouse;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
