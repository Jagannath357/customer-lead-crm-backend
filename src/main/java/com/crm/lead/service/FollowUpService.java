package com.crm.lead.service;

import com.crm.lead.dto.FollowUpDTOs.FollowUpRequest;
import com.crm.lead.dto.FollowUpDTOs.FollowUpResponse;
import java.util.List;

public interface FollowUpService {
    FollowUpResponse addFollowUp(FollowUpRequest request);
    FollowUpResponse updateFollowUp(Long id, FollowUpRequest request);
    List<FollowUpResponse> getFollowUpsByLeadId(Long leadId);
    FollowUpResponse getFollowUpById(Long id);
    void deleteFollowUp(Long id);
    List<FollowUpResponse> getAllFollowUps();
}