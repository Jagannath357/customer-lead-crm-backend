package com.crm.lead.controller;

import com.crm.lead.dto.FollowUpDTOs.FollowUpRequest;
import com.crm.lead.dto.FollowUpDTOs.FollowUpResponse;
import com.crm.lead.response.ApiResponse;
import com.crm.lead.service.FollowUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/followups")
@Tag(name = "Followup Management APIs")
public class FollowUpController {

	private final FollowUpService followUpService;

	public FollowUpController(FollowUpService followUpService) {
		this.followUpService = followUpService;
	}

	@PostMapping
	@Operation(summary = "Log an interaction track step and schedule the next milestone follow-up target date")
	public ResponseEntity<ApiResponse<FollowUpResponse>> addFollowUp(@Valid @RequestBody FollowUpRequest request) {
		FollowUpResponse response = followUpService.addFollowUp(request);
		return ResponseEntity.ok(ApiResponse.success("Follow-up added and master tracking synced", response));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update an existing follow-up entry details by its ID")
	public ResponseEntity<ApiResponse<FollowUpResponse>> updateFollowUp(@PathVariable Long id,
			@Valid @RequestBody FollowUpRequest request) {
		FollowUpResponse response = followUpService.updateFollowUp(id, request);
		return ResponseEntity.ok(ApiResponse.success("Follow-up updated successfully", response));
	}

	// FIXING THE MAPPED 'GET' METHOD ERROR
	@GetMapping
	@Operation(summary = "Fetch all global follow-ups recorded across the CRM")
	public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getAllFollowUps() {
		List<FollowUpResponse> list = followUpService.getAllFollowUps();
		return ResponseEntity.ok(ApiResponse.success("All follow-up history compiled", list));
	}

	@GetMapping("/lead/{leadId}")
	@Operation(summary = "Fetch full historical trace timelines of interactions for a target customer")
	public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getFollowUpHistory(@PathVariable Long leadId) {
		List<FollowUpResponse> context = followUpService.getFollowUpsByLeadId(leadId);
		return ResponseEntity.ok(ApiResponse.success("Timeline logs traced completely", context));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Fetch a single specific follow-up details sheet by ID")
	public ResponseEntity<ApiResponse<FollowUpResponse>> getFollowUpById(@PathVariable Long id) {
		FollowUpResponse response = followUpService.getFollowUpById(id);
		return ResponseEntity.ok(ApiResponse.success("Follow-up entry found", response));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Permanently remove an recorded follow-up tracking index card from database")
	public ResponseEntity<ApiResponse<Void>> deleteFollowUp(@PathVariable Long id) {
		followUpService.deleteFollowUp(id);
		return ResponseEntity.ok(ApiResponse.success("Follow-up history card dropped", null));
	}
}