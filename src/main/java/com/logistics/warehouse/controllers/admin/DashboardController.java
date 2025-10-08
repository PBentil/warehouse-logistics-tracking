package com.logistics.warehouse.controllers.admin;

import com.logistics.warehouse.DTO.admin.DashboardSummaryDTO;
import com.logistics.warehouse.services.admin.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin/dashboard")
    public DashboardSummaryDTO getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }


}
