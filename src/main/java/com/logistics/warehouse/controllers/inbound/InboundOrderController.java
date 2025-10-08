package com.logistics.warehouse.controllers.inbound;

import com.logistics.warehouse.DTO.inbound.*;
import com.logistics.warehouse.services.inboundOrder.InboundOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inbound-orders")
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping
    public ResponseEntity<InboundOrderResponse> createInboundOrder(@RequestBody InboundOrderRequest request) {
        return ResponseEntity.ok(inboundOrderService.createInboundOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<InboundOrderResponse>> getAllInboundOrders() {
        return ResponseEntity.ok(inboundOrderService.getAllInboundOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InboundOrderResponse> getInboundOrder(@PathVariable Long id) {
        return ResponseEntity.ok(inboundOrderService.getInboundOrder(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InboundOrderResponse> updateInboundOrder(@PathVariable Long id,@RequestBody InboundOrderRequest request){
        return ResponseEntity.ok(inboundOrderService.updateInboundOrder(id,request));
    }

    @GetMapping("/warehouses/{warehouseId}/orders")
    public ResponseEntity<List<InboundOrderResponse>> getOrdersByWarehouse(
            @PathVariable Long warehouseId
    ) {
        List<InboundOrderResponse> orders = inboundOrderService.getOrdersByWarehouse(warehouseId);
        return ResponseEntity.ok(orders);
    }

}
