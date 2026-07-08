package com.crm.lead.service.impl;

import com.crm.lead.dto.LeadTypeDTOs.LeadTypeRequest;
import com.crm.lead.dto.LeadTypeDTOs.LeadTypeResponse;
import com.crm.lead.entity.LeadType;
import com.crm.lead.exception.BadRequestException;
import com.crm.lead.repository.LeadTypeRepository;
import com.crm.lead.service.LeadTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadTypeServiceImpl implements LeadTypeService {

    private final LeadTypeRepository leadTypeRepository;
    private final ModelMapper modelMapper;

    public LeadTypeServiceImpl(LeadTypeRepository leadTypeRepository, ModelMapper modelMapper) {
        this.leadTypeRepository = leadTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LeadTypeResponse createLeadType(LeadTypeRequest request) {
        if (leadTypeRepository.existsByName(request.getName())) {
            throw new BadRequestException("Lead type already exists");
        }
        LeadType leadType = modelMapper.map(request, LeadType.class);
        return modelMapper.map(leadTypeRepository.save(leadType), LeadTypeResponse.class);
    }

    @Override
    public List<LeadTypeResponse> getAllLeadTypes() {
        return leadTypeRepository.findAll().stream()
                .map(type -> modelMapper.map(type, LeadTypeResponse.class))
                .collect(Collectors.toList());
    }
}