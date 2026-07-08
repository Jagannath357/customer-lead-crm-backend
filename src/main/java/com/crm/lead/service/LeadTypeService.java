package com.crm.lead.service;

import com.crm.lead.dto.LeadTypeDTOs.LeadTypeRequest;
import com.crm.lead.dto.LeadTypeDTOs.LeadTypeResponse;
import java.util.List;

public interface LeadTypeService {
    LeadTypeResponse createLeadType(LeadTypeRequest request);
    List<LeadTypeResponse> getAllLeadTypes();
}