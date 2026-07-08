package com.crm.lead.controller;

import com.crm.lead.dto.NotesDTOs.NoteRequest;
import com.crm.lead.dto.NotesDTOs.NoteResponse;
import com.crm.lead.response.ApiResponse;
import com.crm.lead.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notes")
@Tag(name = "Notes Management APIs")
@CrossOrigin(origins = "*")
public class NotesController {

	private final NotesService notesService;

	public NotesController(NotesService notesService) {
		this.notesService = notesService;
	}

	@PostMapping
	@Operation(summary = "Add an un-limited execution note to a target lead dashboard timeline")
	public ResponseEntity<ApiResponse<NoteResponse>> addNote(@Valid @RequestBody NoteRequest request) {
		NoteResponse response = notesService.addNote(request);
		return ResponseEntity.ok(ApiResponse.success("Note added successfully", response));
	}

	@GetMapping("/lead/{leadId}")
	@Operation(summary = "Pull all notes recorded for a specific customer history timeline")
	public ResponseEntity<ApiResponse<List<NoteResponse>>> getNotesByLead(@PathVariable Long leadId) {
		List<NoteResponse> notes = notesService.getNotesByLeadId(leadId);
		return ResponseEntity.ok(ApiResponse.success("Notes parsed completely", notes));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remove a historic execution notation snippet")
	public ResponseEntity<ApiResponse<Void>> deleteNote(@PathVariable Long id) {
		notesService.deleteNote(id);
		return ResponseEntity.ok(ApiResponse.success("Note dropped from timeline", null));
	}
}