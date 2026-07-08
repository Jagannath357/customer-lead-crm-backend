package com.crm.lead.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.lead.dto.CustomerLeadDTOs.LeadRequest;
import com.crm.lead.dto.CustomerLeadDTOs.LeadResponse;
import com.crm.lead.dto.DashboardDTO;
import com.crm.lead.response.ApiResponse;
import com.crm.lead.service.CustomerLeadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/leads")
@Tag(name = "Customer Leads Management APIs")
@CrossOrigin(origins = "*")
public class CustomerLeadController {

    private final CustomerLeadService leadService;

    // Explicit constructor injection replacing Lombok's @RequiredArgsConstructor
    public CustomerLeadController(CustomerLeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a brand new customer lead into the CRM database")
    public ResponseEntity<ApiResponse<LeadResponse>> createLead(@Valid @RequestBody LeadRequest request) {
        LeadResponse response = leadService.createLead(request);
        return ResponseEntity.ok(ApiResponse.success("Lead registered successfully", response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing customer lead profile by ID")
    public ResponseEntity<ApiResponse<LeadResponse>> updateLead(@PathVariable Long id, @Valid @RequestBody LeadRequest request) {
        LeadResponse response = leadService.updateLead(id, request);
        return ResponseEntity.ok(ApiResponse.success("Lead updated successfully", response));
    }

    @GetMapping
    @Operation(summary = "Retrieve all compiled customer leads data collections")
    public ResponseEntity<ApiResponse<List<LeadResponse>>> getAllLeads() {
        List<LeadResponse> leads = leadService.getAllLeads();
        return ResponseEntity.ok(ApiResponse.success("Leads data collections compiled", leads));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch a single customer lead details by ID")
    public ResponseEntity<ApiResponse<LeadResponse>> getLeadById(@PathVariable Long id) {
        LeadResponse lead = leadService.getLeadById(id);
        return ResponseEntity.ok(ApiResponse.success("Lead details found", lead));
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Retrieve calculated dashboard metrics and breakdowns")
    public ResponseEntity<ApiResponse<DashboardDTO>> getDashboard() {
        DashboardDTO dashboardData = leadService.getDashboardData();
        return ResponseEntity.ok(ApiResponse.success("Dashboard datasets parsed", dashboardData));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently remove or archive a lead profile")
    public ResponseEntity<ApiResponse<Void>> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.ok(ApiResponse.success("Lead profile archived and deleted", null));
    }
}