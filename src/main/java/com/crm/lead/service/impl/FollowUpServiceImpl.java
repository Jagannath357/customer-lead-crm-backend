package com.crm.lead.service.impl;

import com.crm.lead.dto.FollowUpDTOs.FollowUpRequest;
import com.crm.lead.dto.FollowUpDTOs.FollowUpResponse;
import com.crm.lead.entity.CustomerLead;
import com.crm.lead.entity.FollowUp;
import com.crm.lead.entity.User;
import com.crm.lead.exception.ResourceNotFoundException;
import com.crm.lead.repository.CustomerLeadRepository;
import com.crm.lead.repository.FollowUpRepository;
import com.crm.lead.repository.UserRepository;
import com.crm.lead.service.FollowUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    private final FollowUpRepository followUpRepository;
    private final CustomerLeadRepository leadRepository;
    private final UserRepository userRepository;

    // Explicit constructor injection replacing Lombok
    public FollowUpServiceImpl(FollowUpRepository followUpRepository, CustomerLeadRepository leadRepository,
                               UserRepository userRepository) {
        this.followUpRepository = followUpRepository;
        this.leadRepository = leadRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public FollowUpResponse addFollowUp(FollowUpRequest request) {
        CustomerLead lead = leadRepository.findById(request.getLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
        User user = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FollowUp followUp = new FollowUp();
        followUp.setLead(lead);
        followUp.setFollowupDate(request.getFollowupDate());
        followUp.setDiscussion(request.getDiscussion());
        followUp.setNextFollowup(request.getNextFollowup());
        followUp.setStatus(request.getStatus());
        followUp.setCreatedBy(user);

        lead.setStatus(request.getStatus());
        if (request.getNextFollowup() != null) {
            lead.setNextFollowupDate(request.getNextFollowup());
        }
        lead.setDiscussion(request.getDiscussion());
        leadRepository.save(lead);

        return convertToResponse(followUpRepository.save(followUp));
    }

    @Override
    @Transactional
    public FollowUpResponse updateFollowUp(Long id, FollowUpRequest request) {
        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Follow-up record not found"));
        CustomerLead lead = leadRepository.findById(request.getLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        followUp.setFollowupDate(request.getFollowupDate());
        followUp.setDiscussion(request.getDiscussion());
        followUp.setNextFollowup(request.getNextFollowup());
        followUp.setStatus(request.getStatus());

        // Sync changes with the master tracking lead sheet
        lead.setStatus(request.getStatus());
        if (request.getNextFollowup() != null) {
            lead.setNextFollowupDate(request.getNextFollowup());
        }
        lead.setDiscussion(request.getDiscussion());
        leadRepository.save(lead);

        return convertToResponse(followUpRepository.save(followUp));
    }

    @Override
    public List<FollowUpResponse> getFollowUpsByLeadId(Long leadId) {
        return followUpRepository.findByLeadIdOrderByFollowupDateDesc(leadId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FollowUpResponse getFollowUpById(Long id) {
        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Follow-up record not found"));
        return convertToResponse(followUp);
    }

    @Override
    @Transactional
    public void deleteFollowUp(Long id) {
        if (!followUpRepository.existsById(id)) {
            throw new ResourceNotFoundException("Follow-up record not found");
        }
        followUpRepository.deleteById(id);
    }

    private FollowUpResponse convertToResponse(FollowUp followUp) {
        FollowUpResponse res = new FollowUpResponse();
        res.setId(followUp.getId());
        res.setLeadId(followUp.getLead().getId());
        res.setFollowupDate(followUp.getFollowupDate());
        res.setDiscussion(followUp.getDiscussion());
        res.setNextFollowup(followUp.getNextFollowup());
        res.setStatus(followUp.getStatus());
        res.setCreatedById(followUp.getCreatedBy().getId());
        res.setCreatedByName(followUp.getCreatedBy().getFullName());
        res.setCreatedDate(followUp.getCreatedDate());
        return res;
    }
    @Override
    public List<FollowUpResponse> getAllFollowUps() {
        return followUpRepository.findAll().stream()
                .map(this::convertToResponse) // Utilizes your existing mapping conversion method
                .collect(Collectors.toList());
    }
}