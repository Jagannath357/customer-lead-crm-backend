package com.crm.lead.controller;

import com.crm.lead.dto.LeadTypeDTOs.LeadTypeRequest;
import com.crm.lead.dto.LeadTypeDTOs.LeadTypeResponse;
import com.crm.lead.response.ApiResponse;
import com.crm.lead.service.LeadTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lead-types")
@Tag(name = "Lead Type Management APIs")
public class LeadTypeController {

    private final LeadTypeService leadTypeService;

    public LeadTypeController(LeadTypeService leadTypeService) {
        this.leadTypeService = leadTypeService;
    }

    @PostMapping
    @Operation(summary = "Create a custom business lead category")
    public ResponseEntity<ApiResponse<LeadTypeResponse>> createLeadType(@Valid @RequestBody LeadTypeRequest request) {
        LeadTypeResponse response = leadTypeService.createLeadType(request);
        return ResponseEntity.ok(ApiResponse.success("Lead Type created successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all business lead categories")
    public ResponseEntity<ApiResponse<List<LeadTypeResponse>>> getAllLeadTypes() {
        List<LeadTypeResponse> list = leadTypeService.getAllLeadTypes();
        return ResponseEntity.ok(ApiResponse.success("Lead types retrieved", list));
    }
}