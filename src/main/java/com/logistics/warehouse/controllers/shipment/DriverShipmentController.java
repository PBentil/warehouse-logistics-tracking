package com.logistics.warehouse.controllers.shipment;


import com.logistics.warehouse.DTO.shipment.ShipmentResponse;
import com.logistics.warehouse.services.shipments.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driver/shipments")
public class DriverShipmentController {

    private final ShipmentService shipmentService;

    public DriverShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<List<ShipmentResponse>> getDriverShipments(@PathVariable Long driverId){
        return ResponseEntity.ok(shipmentService.getShipmentsForDriver(driverId));
    }
}
