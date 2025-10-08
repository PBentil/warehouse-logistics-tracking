package com.logistics.warehouse.controllers.shipment;

import com.logistics.warehouse.DTO.admin.UserResponse;
import com.logistics.warehouse.services.shipments.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllDrivers() {
        List<UserResponse> drivers = driverService.getAllDrivers().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getRole().name()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(drivers);
    }
}
