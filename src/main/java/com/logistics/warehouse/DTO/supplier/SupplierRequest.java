package com.logistics.warehouse.DTO.supplier;

public class SupplierRequest {
    private String name;
    private String contactInfo;
    private String address;
    private String email;

    public SupplierRequest() {
    }

    public SupplierRequest(String name, String contactInfo, String address, String email) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
