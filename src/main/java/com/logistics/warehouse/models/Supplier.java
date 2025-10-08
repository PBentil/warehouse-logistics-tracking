package com.logistics.warehouse.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    private String name;
    private String contactInfo;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InboundOrder> inboundOrders;

    public Supplier() {
    }

    public Supplier(Long supplierId, String name, String contactInfo, String address, User user, List<InboundOrder> inboundOrders) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.user = user;
        this.inboundOrders = inboundOrders;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<InboundOrder> getInboundOrders() {
        return inboundOrders;
    }

    public void setInboundOrders(List<InboundOrder> inboundOrders) {
        this.inboundOrders = inboundOrders;
    }
}
