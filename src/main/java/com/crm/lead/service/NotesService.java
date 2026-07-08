package com.crm.lead.service;

import com.crm.lead.dto.NotesDTOs.NoteRequest;
import com.crm.lead.dto.NotesDTOs.NoteResponse;
import java.util.List;

public interface NotesService {
    NoteResponse addNote(NoteRequest request);
    NoteResponse updateNote(Long id, NoteRequest request);
    List<NoteResponse> getNotesByLeadId(Long leadId);
    NoteResponse getNoteById(Long id);
    void deleteNote(Long id);
}