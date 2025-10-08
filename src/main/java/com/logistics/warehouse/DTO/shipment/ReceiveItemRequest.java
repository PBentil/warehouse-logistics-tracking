package com.logistics.warehouse.DTO.shipment;

public class ReceiveItemRequest {
    private Long productId;
    private Integer quantity;
    private String binLocation;

    public ReceiveItemRequest() {
    }

    public ReceiveItemRequest(Long productId, Integer quantity, String binLocation) {
        this.productId = productId;
        this.quantity = quantity;
        this.binLocation = binLocation;
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
