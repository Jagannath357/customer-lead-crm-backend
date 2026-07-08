package com.crm.lead.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.crm.lead.constant.LeadPriority;
import com.crm.lead.constant.LeadStatus;
import com.crm.lead.dto.CustomerLeadDTOs.LeadRequest;
import com.crm.lead.dto.CustomerLeadDTOs.LeadResponse;
import com.crm.lead.dto.DashboardDTO;
import com.crm.lead.entity.CustomerLead;
import com.crm.lead.entity.LeadType;
import com.crm.lead.entity.User;
import com.crm.lead.exception.ResourceNotFoundException;
import com.crm.lead.repository.CustomerLeadRepository;
import com.crm.lead.repository.LeadTypeRepository;
import com.crm.lead.repository.UserRepository;
import com.crm.lead.service.CustomerLeadService;

@Service
public class CustomerLeadServiceImpl implements CustomerLeadService {

	private final CustomerLeadRepository leadRepository;
	private final LeadTypeRepository leadTypeRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	// Explicit constructor injection replacing Lombok's @RequiredArgsConstructor
	public CustomerLeadServiceImpl(CustomerLeadRepository leadRepository, LeadTypeRepository leadTypeRepository,
			UserRepository userRepository, ModelMapper modelMapper) {
		this.leadRepository = leadRepository;
		this.leadTypeRepository = leadTypeRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public LeadResponse createLead(LeadRequest request) {
		LeadType type = leadTypeRepository.findById(request.getLeadTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Lead Type not found"));

		User executive = null;
		if (request.getAssignedExecutiveId() != null) {
			executive = userRepository.findById(request.getAssignedExecutiveId())
					.orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
		}

		CustomerLead lead = modelMapper.map(request, CustomerLead.class);
		lead.setLeadType(type);
		lead.setAssignedExecutive(executive);

		return convertToResponse(leadRepository.save(lead));
	}

	@Override
	public LeadResponse updateLead(Long id, LeadRequest request) {
		CustomerLead lead = leadRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

		LeadType type = leadTypeRepository.findById(request.getLeadTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Lead Type not found"));

		if (request.getAssignedExecutiveId() != null) {
			User exec = userRepository.findById(request.getAssignedExecutiveId())
					.orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
			lead.setAssignedExecutive(exec);
		} else {
			lead.setAssignedExecutive(null);
		}

		lead.setCustomerName(request.getCustomerName());
		lead.setMobile(request.getMobile());
		lead.setAlternateMobile(request.getAlternateMobile());
		lead.setEmail(request.getEmail());
		lead.setLeadType(type);
		lead.setCity(request.getCity());
		lead.setAddress(request.getAddress());
		lead.setRequirement(request.getRequirement());
		lead.setLeadSource(request.getLeadSource());
		lead.setDiscussion(request.getDiscussion());
		lead.setVisitDate(request.getVisitDate());
		lead.setNextFollowupDate(request.getNextFollowupDate());
		lead.setStatus(request.getStatus());
		lead.setPriority(request.getPriority());

		return convertToResponse(leadRepository.save(lead));
	}

	@Override
	public LeadResponse getLeadById(Long id) {
		CustomerLead lead = leadRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
		return convertToResponse(lead);
	}

	@Override
	public List<LeadResponse> getAllLeads() {
		return leadRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	@Override
	public List<LeadResponse> getLeadsByExecutive(Long executiveId) {
		return leadRepository.findByAssignedExecutiveId(executiveId).stream().map(this::convertToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteLead(Long id) {
		if (!leadRepository.existsById(id)) {
			throw new ResourceNotFoundException("Lead not found");
		}
		leadRepository.deleteById(id);
	}

	@Override
	public DashboardDTO getDashboardData() {
		long total = leadRepository.count();
		long hot = leadRepository.countByPriority(LeadPriority.HOT);
		long won = leadRepository.countByStatus(LeadStatus.CLOSED_WON);
		long lost = leadRepository.countByStatus(LeadStatus.CLOSED_LOST);
		long todayFollowups = leadRepository.findLeadsWithFollowUpOn(LocalDate.now()).size();
		long pendingFollowups = leadRepository.findOverdueLeads(LocalDate.now()).size();

		Map<String, Long> statuses = Arrays.stream(LeadStatus.values())
				.collect(Collectors.toMap(Enum::name, leadRepository::countByStatus));

		Map<String, Long> priorities = Arrays.stream(LeadPriority.values())
				.collect(Collectors.toMap(Enum::name, leadRepository::countByPriority));

		// Creating an empty placeholder map for lead type distribution to fulfill the explicit constructor parameters
		Map<String, Long> leadTypes = new HashMap<>();

		// Using explicit constructor call instead of Lombok's .builder() setup
		return new DashboardDTO(
				total,             // totalLeads
				0L,                // todayLeads (can be added via repository date query if needed)
				todayFollowups,    // todayFollowups
				pendingFollowups,  // pendingFollowups
				hot,               // hotLeads
				won,               // closedWon
				lost,              // closedLost
				statuses,          // statusDistribution
				priorities,        // priorityDistribution
				leadTypes          // leadTypeDistribution
		);
	}

	private LeadResponse convertToResponse(CustomerLead lead) {
		LeadResponse res = modelMapper.map(lead, LeadResponse.class);
		if (lead.getLeadType() != null) {
			res.setLeadTypeId(lead.getLeadType().getId());
			res.setLeadTypeName(lead.getLeadType().getName());
		}
		if (lead.getAssignedExecutive() != null) {
			res.setAssignedExecutiveId(lead.getAssignedExecutive().getId());
			res.setAssignedExecutiveName(lead.getAssignedExecutive().getFullName());
		}
		return res;
	}
}