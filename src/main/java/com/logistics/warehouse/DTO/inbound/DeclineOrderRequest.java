package com.logistics.warehouse.DTO.inbound;

public class DeclineOrderRequest {
    private String reason;

    public DeclineOrderRequest(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
