package com.crm.lead.service;

import com.crm.lead.dto.CustomerLeadDTOs.*;
import com.crm.lead.dto.DashboardDTO;
import java.util.List;

public interface CustomerLeadService {
    LeadResponse createLead(LeadRequest request);
    LeadResponse updateLead(Long id, LeadRequest request);
    LeadResponse getLeadById(Long id);
    List<LeadResponse> getAllLeads();
    List<LeadResponse> getLeadsByExecutive(Long executiveId);
    void deleteLead(Long id);
    DashboardDTO getDashboardData();
}