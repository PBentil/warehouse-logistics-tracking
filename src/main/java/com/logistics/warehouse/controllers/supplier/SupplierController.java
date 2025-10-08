package com.logistics.warehouse.controllers.supplier;


import com.logistics.warehouse.DTO.inbound.DeclineOrderRequest;
import com.logistics.warehouse.DTO.inbound.InboundOrderResponse;
import com.logistics.warehouse.DTO.supplier.SupplierRequest;
import com.logistics.warehouse.DTO.supplier.SupplierResponse;
import com.logistics.warehouse.services.inboundOrder.InboundOrderService;
import com.logistics.warehouse.services.supplier.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final InboundOrderService inboundOrderService;

    public SupplierController(SupplierService supplierService, InboundOrderService inboundOrderService) {
        this.supplierService = supplierService;
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> addSupplier(@RequestBody SupplierRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.addSupplier(request));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplier(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierRequest request) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    // --- Order endpoints ---
    @GetMapping("/{supplierId}/orders/pending")
    public ResponseEntity<List<InboundOrderResponse>> getPendingOrders(@PathVariable Long supplierId) {
        return ResponseEntity.ok(inboundOrderService.getPendingordersforSupplier(supplierId));
    }

    @PostMapping("/orders/{orderId}/approve")
    public ResponseEntity<InboundOrderResponse> approveOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(inboundOrderService.approveOrder(orderId));
    }

    @PostMapping("/orders/{orderId}/decline")
    public ResponseEntity<InboundOrderResponse> declineOrder(
            @PathVariable Long orderId,
            @RequestBody DeclineOrderRequest request) {
        return ResponseEntity.ok(inboundOrderService.declineOrder(orderId, request));
    }

    @GetMapping("/{supplierId}/orders/approved")
    public ResponseEntity<List<InboundOrderResponse>> getApprovedOrders(@PathVariable Long supplierId) {
        List<InboundOrderResponse> orders = inboundOrderService.getApprovedOrdersForSupplier(supplierId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{supplierId}/orders/declined")
    public ResponseEntity<List<InboundOrderResponse>> getDeclinedOrders(@PathVariable Long supplierId) {
        List<InboundOrderResponse> orders = inboundOrderService.getDeclinedOrdersForSupplier(supplierId);
        return ResponseEntity.ok(orders);
    }

}
