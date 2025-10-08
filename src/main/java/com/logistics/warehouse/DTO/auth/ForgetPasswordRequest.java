package com.logistics.warehouse.DTO.auth;

public class ForgetPasswordRequest {

    private String email;

    public ForgetPasswordRequest() {
    }

    public ForgetPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
