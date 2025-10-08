package com.logistics.warehouse.DTO.manager;

public class ManagerDashboardDTO {
    private long totalProducts;
    private long totalInventory;
    private long inboundOrders;


    public ManagerDashboardDTO(long totalProducts, long totalInventory, long inboundOrders) {
        this.totalProducts = totalProducts;
        this.totalInventory = totalInventory;
        this.inboundOrders = inboundOrders;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(long totalInventory) {
        this.totalInventory = totalInventory;
    }

    public long getInboundOrders() {
        return inboundOrders;
    }

    public void setInboundOrders(long inboundOrders) {
        this.inboundOrders = inboundOrders;
    }
}

