package com.logistics.warehouse.DTO.shipment;

public class ReceiveItemResponse {
    private Long productId;
    private String productName;
    private  Integer quantity;
    private String binLocation;

    public ReceiveItemResponse() {
    }

    public ReceiveItemResponse(Long productId, String productName, Integer quantity, String binLocation) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.binLocation = binLocation;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
