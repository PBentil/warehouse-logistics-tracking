package com.logistics.warehouse.controllers.shipment;

import com.logistics.warehouse.DTO.shipment.*;
import com.logistics.warehouse.services.shipments.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{orderId}/assign")
    public ShipmentResponse assignShipment (
            @PathVariable Long orderId,
            @RequestBody ShipmentRequest request){
        return shipmentService.assignShipment(orderId, request);
    }

    @PostMapping("/{shipmentId}/dispatch")
    public ShipmentResponse dispatchShipment (
            @PathVariable Long shipmentId,
            @RequestBody LocationUpdateRequest request
    ){
        return shipmentService.dispatchShipment(shipmentId, request);
    };

    @PostMapping("/{shipmentId}/location")
    public ShipmentResponse updateLocation (
            @PathVariable Long shipmentId,
            @RequestBody LocationUpdateRequest request){
        return shipmentService.updateLocation(shipmentId, request);
    };

    @PostMapping("/{shipmentId}/delivered")
    public ShipmentResponse markDelivered(@PathVariable Long shipmentId){
        return shipmentService.markDelivered(shipmentId);
    }

    @PostMapping("/receive")
    public ResponseEntity<ReceiveShipmentResponse> markReceived(@RequestBody ReceiveShipmentRequest request) {
        ReceiveShipmentResponse response = shipmentService.receiveShipment(                                                                                                                                                           request);
        return ResponseEntity.ok(response);
    }

}
