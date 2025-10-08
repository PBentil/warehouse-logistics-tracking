package com.logistics.warehouse.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "tracking")
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trakingId;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;

    public Tracking(Long trakingId, Shipment shipment, Double latitude, Double longitude, LocalDateTime timestamp) {
        this.trakingId = trakingId;
        this.shipment = shipment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public Long getTrakingId() {
        return trakingId;
    }

    public void setTrakingId(Long trakingId) {
        this.trakingId = trakingId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
