package com.logistics.warehouse.DTO.admin;

public class DashboardSummaryDTO {
    private long totalUsers;
    private long totalInventories;
    private long totalOrders;
    private long totalDeliveries;
    private long inboundOrders;
    private long suppliers;
    private long warehouses;
    private long products;

    public DashboardSummaryDTO(long totalUsers, long totalInventories, long totalOrders, long totalDeliveries, long inboundOrders, long suppliers, long warehouses, long products) {
        this.totalUsers = totalUsers;
        this.totalInventories = totalInventories;
        this.totalOrders = totalOrders;
        this.totalDeliveries = totalDeliveries;
        this.inboundOrders = inboundOrders;
        this.suppliers = suppliers;
        this.warehouses = warehouses;
        this.products = products;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalInventories() {
        return totalInventories;
    }

    public void setTotalInventories(long totalInventories) {
        this.totalInventories = totalInventories;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(long totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    public long getInboundOrders() {
        return inboundOrders;
    }

    public void setInboundOrders(long inboundOrders) {
        this.inboundOrders = inboundOrders;
    }

    public long getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(long suppliers) {
        this.suppliers = suppliers;
    }

    public long getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(long warehouses) {
        this.warehouses = warehouses;
    }

    public long getProducts() {
        return products;
    }

    public void setProducts(long products) {
        this.products = products;
    }
}
