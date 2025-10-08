package com.logistics.warehouse.services.shipments;

import com.logistics.warehouse.DTO.shipment.*;
import com.logistics.warehouse.models.*;
import com.logistics.warehouse.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final InboundOrderRepository inboundOrderRepository;
    private final InventoryRespository inventoryRespository;
    private final ProductRespository productRespository;
    private final UserRepository userRepository;


    public ShipmentService(ShipmentRepository shipmentRepository, InboundOrderRepository inboundOrderRepository, InventoryRespository inventoryRespository, ProductRespository productRespository, UserRepository userRepository) {
        this.shipmentRepository = shipmentRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.inventoryRespository = inventoryRespository;
        this.productRespository = productRespository;
        this.userRepository = userRepository;
    }

    public ShipmentResponse assignShipment(Long orderId, ShipmentRequest request) {
        InboundOrder order = inboundOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Inbound order not found"));

        User driver = userRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (driver.getRole() != Role.DRIVER) {
            throw new RuntimeException("User is not a driver");
        }

        Shipment shipment = new Shipment();
        shipment.setInboundOrder(order);
        shipment.setDriver(driver);
        shipment.setDriverName(driver.getFullName());
        shipment.setDriverContact(driver.getPhone());
        shipment.setVehicleNumber(request.getVehicleNumber());
        shipment.setStatus(ShipmentStatus.ASSIGNED);
        shipment.setUpdatedAt(LocalDateTime.now());
        shipment.setCurrentLocation(order.getSupplier().getAddress());

        Shipment saved = shipmentRepository.save(shipment);
        return mapToResponse(saved);
    }


    public ShipmentResponse dispatchShipment(Long shipmentId, LocationUpdateRequest request){
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.setStatus(ShipmentStatus.DISPATCHED);
        shipment.setCurrentLocation(request.getCurrentLocation());
        shipment.setUpdatedAt(LocalDateTime.now());

        Shipment updated = shipmentRepository.save(shipment);
        return mapToResponse(updated);
    }

    public ShipmentResponse updateLocation(Long shipmentId , LocationUpdateRequest request){
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.setStatus(ShipmentStatus.IN_TRANSIT);
        shipment.setCurrentLocation(request.getCurrentLocation());
        shipment.setUpdatedAt(LocalDateTime.now());

        Shipment updated = shipmentRepository.save(shipment);
                return mapToResponse(updated);
    }


    @Transactional
    public ShipmentResponse markDelivered (Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.setStatus(ShipmentStatus.DELIVERED);
        shipment.setUpdatedAt(LocalDateTime.now());

        shipment.setCurrentLocation(
                shipment.getInboundOrder().getWarehouse().getName()
        );


        Shipment updated = shipmentRepository.save(shipment);
        return mapToResponse(updated);
    }



    @Transactional
    public ReceiveShipmentResponse receiveShipment(ReceiveShipmentRequest request) {
        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        if (!shipment.getStatus().equals(ShipmentStatus.DELIVERED)) {
            throw new RuntimeException("Shipment must be delivered before it can be received");
        }

        shipment.setStatus(ShipmentStatus.RECEIVED);
        shipment.setUpdatedAt(LocalDateTime.now());

        Warehouse warehouse = shipment.getInboundOrder().getWarehouse();

        List<ReceiveItemResponse> itemResponses = request.getItems().stream().map(itemReq -> {
            Product product = productRespository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

              Inventory inventory = inventoryRespository
                    .findByWarehouseAndProductAndBinLocation(warehouse, product, itemReq.getBinLocation())
                    .orElse(new Inventory(warehouse, product, 0, itemReq.getBinLocation()));

            inventory.setQuantity(inventory.getQuantity() + itemReq.getQuantity());
            inventory.setBinLocation(itemReq.getBinLocation());

            Inventory savedInventory = inventoryRespository.save(inventory);

            return new ReceiveItemResponse(
                    product.getProductid(),
                    product.getName(),
                    itemReq.getQuantity(),
                    savedInventory.getBinLocation()
            );
        }).collect(Collectors.toList());

        Shipment updated = shipmentRepository.save(shipment);

        return new ReceiveShipmentResponse(
                updated.getId(),
                updated.getStatus().name(),
                updated.getInboundOrder().getWarehouse().getName(),
                updated.getUpdatedAt(),
                itemResponses
        );

    }

    public List<ShipmentResponse> getShipmentsForDriver(Long userId) {
        List<Shipment> shipments = shipmentRepository.findByDriver_Id(userId);
        return shipments.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }




    private ShipmentResponse mapToResponse(Shipment shipment) {
        ShipmentResponse response = new ShipmentResponse();
        response.setId(shipment.getId());
        response.setOrderId(shipment.getInboundOrder().getId());
        response.setDriverName(shipment.getDriverName());
        response.setDriverContact(shipment.getDriverContact());
        response.setVehicleNumber(shipment.getVehicleNumber());
        response.setCurrentLocation(shipment.getCurrentLocation());
        response.setStatus(shipment.getStatus().name());
        response.setUpdatedAt(shipment.getUpdatedAt());
        return response;
    }

}
