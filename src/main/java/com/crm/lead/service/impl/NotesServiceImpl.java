package com.crm.lead.service.impl;

import com.crm.lead.dto.NotesDTOs.NoteRequest;
import com.crm.lead.dto.NotesDTOs.NoteResponse;
import com.crm.lead.entity.CustomerLead;
import com.crm.lead.entity.Notes;
import com.crm.lead.entity.User;
import com.crm.lead.exception.ResourceNotFoundException;
import com.crm.lead.repository.CustomerLeadRepository;
import com.crm.lead.repository.NotesRepository;
import com.crm.lead.repository.UserRepository;
import com.crm.lead.service.NotesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;
    private final CustomerLeadRepository leadRepository;
    private final UserRepository userRepository;

    // Explicit constructor injection replacing Lombok
    public NotesServiceImpl(NotesRepository notesRepository, CustomerLeadRepository leadRepository,
                            UserRepository userRepository) {
        this.notesRepository = notesRepository;
        this.leadRepository = leadRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public NoteResponse addNote(NoteRequest request) {
        CustomerLead lead = leadRepository.findById(request.getLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
        User user = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notes notes = new Notes();
        notes.setLead(lead);
        notes.setNote(request.getNote());
        notes.setCreatedBy(user);

        return convertToResponse(notesRepository.save(notes));
    }

    @Override
    @Transactional
    public NoteResponse updateNote(Long id, NoteRequest request) {
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note entry not found"));
        
        notes.setNote(request.getNote());
        return convertToResponse(notesRepository.save(notes));
    }

    @Override
    public List<NoteResponse> getNotesByLeadId(Long leadId) {
        return notesRepository.findByLeadIdOrderByCreatedDateDesc(leadId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponse getNoteById(Long id) {
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note entry not found"));
        return convertToResponse(notes);
    }

    @Override
    @Transactional
    public void deleteNote(Long id) {
        if (!notesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note entry not found");
        }
        notesRepository.deleteById(id);
    }

    private NoteResponse convertToResponse(Notes notes) {
        NoteResponse response = new NoteResponse();
        response.setId(notes.getId());
        response.setLeadId(notes.getLead().getId());
        response.setNote(notes.getNote());
        response.setCreatedById(notes.getCreatedBy().getId());
        response.setCreatedByName(notes.getCreatedBy().getFullName());
        response.setCreatedDate(notes.getCreatedDate());
        return response;
    }
}