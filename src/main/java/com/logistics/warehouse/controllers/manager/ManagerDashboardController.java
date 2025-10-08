package com.logistics.warehouse.controllers.manager;


import com.logistics.warehouse.DTO.manager.ManagerDashboardDTO;
import com.logistics.warehouse.services.manager.managerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager/dashboard")
public class ManagerDashboardController {
    private final managerDashboardService managerDashboardService;

    public ManagerDashboardController(managerDashboardService managerDashboardService) {
        this.managerDashboardService = managerDashboardService;
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerDashboardDTO> getDashboard(@PathVariable Long managerId){
        return ResponseEntity.ok(managerDashboardService.getDashboard(managerId));
    }
}
