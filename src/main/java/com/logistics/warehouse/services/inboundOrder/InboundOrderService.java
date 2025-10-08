package com.logistics.warehouse.services.inboundOrder;

import com.logistics.warehouse.DTO.inbound.*;
import com.logistics.warehouse.models.*;
import com.logistics.warehouse.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundOrderService {
    private final InboundOrderRepository inboundOrderRepository;
    private final InboundOrderItemRespository inboundOrderItemRepository;
    private final ProductRespository productRepository;
    private final WarehouseRespository warehouseRepository;
    private final SupplierRespository supplierRepository;

    public InboundOrderService(
            InboundOrderRepository inboundOrderRepository,
            InboundOrderItemRespository inboundOrderItemRepository,
            ProductRespository productRepository,
            WarehouseRespository warehouseRepository,
            SupplierRespository supplierRepository
    ) {
        this.inboundOrderRepository = inboundOrderRepository;
        this.inboundOrderItemRepository = inboundOrderItemRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
    }

    public InboundOrderResponse createInboundOrder(InboundOrderRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        InboundOrder order = new InboundOrder();
        order.setSupplier(supplier);
        order.setWarehouse(warehouse);
        order.setStatus(OrderStatus.PENDING_SUPPLIER_APPROVAL);
        order.setCreatedAt(LocalDateTime.now());

        InboundOrder savedOrder = inboundOrderRepository.save(order);

        List<InboundOrderItem> items = request.getItems().stream().map(itemReq -> {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            InboundOrderItem item = new InboundOrderItem();
            item.setInboundOrder(savedOrder);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            return inboundOrderItemRepository.save(item);
        }).collect(Collectors.toList());

        savedOrder.setItems(items);

        return mapToResponse(savedOrder);
    }

    public List<InboundOrderResponse> getAllInboundOrders() {
        return inboundOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public InboundOrderResponse getInboundOrder(Long id) {
        InboundOrder order = inboundOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inbound order not found"));
        return mapToResponse(order);
    }

    @Transactional
    public InboundOrderResponse updateInboundOrder(Long id, InboundOrderRequest request) {
        InboundOrder order = inboundOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inbound order not found"));

        if (request.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            order.setSupplier(supplier);
        }

        if (request.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found"));
            order.setWarehouse(warehouse);
        }

        if (request.getStatus() != null) {
            order.setStatus(OrderStatus.valueOf(request.getStatus().toUpperCase()));
        }

        if (request.getItems() != null) {
            order.getItems().clear();

            for (InboundOrderItemRequest itemReq : request.getItems()) {
                Product product = productRepository.findById(itemReq.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                InboundOrderItem item = new InboundOrderItem();
                item.setInboundOrder(order);
                item.setProduct(product);
                item.setQuantity(itemReq.getQuantity());

                order.getItems().add(item);
            }
        }

        InboundOrder updated = inboundOrderRepository.save(order);
        return mapToResponse(updated);
    }


    public List<InboundOrderResponse> getPendingordersforSupplier(Long user_id){
        Supplier supplier = supplierRepository.findByUser_Id(user_id)
                .orElseThrow(() -> new RuntimeException("Supplier not found for this user"));

        List<InboundOrder> orders =inboundOrderRepository
                .findBySupplier_SupplierIdAndStatus(supplier.getSupplierId(), OrderStatus.PENDING_SUPPLIER_APPROVAL);
         return orders.stream().map(this::mapToResponse).collect(Collectors.toList());

    }
    public List<InboundOrderResponse> getApprovedOrdersForSupplier(Long userId) {
        Supplier supplier = supplierRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Supplier not found for this user"));

        List<InboundOrder> orders = inboundOrderRepository
                .findBySupplier_SupplierIdAndStatus(supplier.getSupplierId(), OrderStatus.APPROVED);

        return orders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<InboundOrderResponse> getDeclinedOrdersForSupplier(Long userId) {
        Supplier supplier = supplierRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Supplier not found for this user"));

        List<InboundOrder> orders = inboundOrderRepository
                .findBySupplier_SupplierIdAndStatus(supplier.getSupplierId(), OrderStatus.DECLINED);

        return orders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    public InboundOrderResponse approveOrder(Long orderId) {
        InboundOrder order = inboundOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.APPROVED);
        inboundOrderRepository.save(order);

        return mapToResponse(order);
    }

    public InboundOrderResponse declineOrder(Long orderId, DeclineOrderRequest request) {
        InboundOrder order = inboundOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.DECLINED);
        order.setDeclineReason(request.getReason());


        inboundOrderRepository.save(order);

        return mapToResponse(order);
    }


    public List<InboundOrderResponse> getOrdersByWarehouse(Long warehouseId) {
        List<InboundOrder> orders = inboundOrderRepository.findByWarehouse_WarehouseId(warehouseId);
        return orders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }



    private InboundOrderResponse mapToResponse(InboundOrder order) {
        InboundOrderResponse response = new InboundOrderResponse();
        response.setId(order.getId());
        response.setSupplierName(order.getSupplier().getName());
        response.setWarehouseName(order.getWarehouse().getName());
        response.setStatus(order.getStatus().name());
        response.setCreatedAt(order.getCreatedAt());

        if(order.getShipment() != null) {
            Shipment shipment = order.getShipment();
            response.setDriverName(shipment.getDriverName());
            response.setDriverContact(shipment.getDriverContact());
            response.setVehicleNumber(shipment.getVehicleNumber());
            response.setShipmentStatus(shipment.getStatus().name());
        }

        List<InboundOrderItemResponse> itemResponses = order.getItems().stream().map(item -> {
            InboundOrderItemResponse ir = new InboundOrderItemResponse();
            ir.setProductName(item.getProduct().getName());
            ir.setQuantity(item.getQuantity());
            return ir;
        }).collect(Collectors.toList());

        response.setItems(itemResponses);
        return response;
    }
}