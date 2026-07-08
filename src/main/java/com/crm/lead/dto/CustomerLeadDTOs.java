package com.crm.lead.dto;

import com.crm.lead.constant.LeadPriority;
import com.crm.lead.constant.LeadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerLeadDTOs {

    public static class LeadRequest {
        @NotBlank(message = "Customer name is required")
        private String customerName;
        @NotBlank(message = "Mobile number is required")
        private String mobile;
        private String alternateMobile;
        private String email;
        @NotNull(message = "Lead Type ID is required")
        private Long leadTypeId;
        private String city;
        private String address;
        private String requirement;
        private String leadSource;
        private Long assignedExecutiveId;
        private String discussion;
        private LocalDate visitDate;
        private LocalDate nextFollowupDate;
        @NotNull(message = "Status is required")
        private LeadStatus status;
        @NotNull(message = "Priority is required")
        private LeadPriority priority;

        public LeadRequest() {}

        // Getters and Setters
        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }

        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }

        public String getAlternateMobile() { return alternateMobile; }
        public void setAlternateMobile(String alternateMobile) { this.alternateMobile = alternateMobile; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Long getLeadTypeId() { return leadTypeId; }
        public void setLeadTypeId(Long leadTypeId) { this.leadTypeId = leadTypeId; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getRequirement() { return requirement; }
        public void setRequirement(String requirement) { this.requirement = requirement; }

        public String getLeadSource() { return leadSource; }
        public void setLeadSource(String leadSource) { this.leadSource = leadSource; }

        public Long getAssignedExecutiveId() { return assignedExecutiveId; }
        public void setAssignedExecutiveId(Long assignedExecutiveId) { this.assignedExecutiveId = assignedExecutiveId; }

        public String getDiscussion() { return discussion; }
        public void setDiscussion(String discussion) { this.discussion = discussion; }

        public LocalDate getVisitDate() { return visitDate; }
        public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

        public LocalDate getNextFollowupDate() { return nextFollowupDate; }
        public void setNextFollowupDate(LocalDate nextFollowupDate) { this.nextFollowupDate = nextFollowupDate; }

        public LeadStatus getStatus() { return status; }
        public void setStatus(LeadStatus status) { this.status = status; }

        public LeadPriority getPriority() { return priority; }
        public void setPriority(LeadPriority priority) { this.priority = priority; }
    }

    public static class LeadResponse {
        private Long id;
        private String customerName;
        private String mobile;
        private String alternateMobile;
        private String email;
        private Long leadTypeId;
        private String leadTypeName;
        private String city;
        private String address;
        private String requirement;
        private String leadSource;
        private Long assignedExecutiveId;
        private String assignedExecutiveName;
        private String discussion;
        private LocalDate visitDate;
        private LocalDate nextFollowupDate;
        private LeadStatus status;
        private LeadPriority priority;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;

        public LeadResponse() {}

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }

        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }

        public String getAlternateMobile() { return alternateMobile; }
        public void setAlternateMobile(String alternateMobile) { this.alternateMobile = alternateMobile; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public Long getLeadTypeId() { return leadTypeId; }
        public void setLeadTypeId(Long leadTypeId) { this.leadTypeId = leadTypeId; }

        public String getLeadTypeName() { return leadTypeName; }
        public void setLeadTypeName(String leadTypeName) { this.leadTypeName = leadTypeName; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getRequirement() { return requirement; }
        public void setRequirement(String requirement) { this.requirement = requirement; }

        public String getLeadSource() { return leadSource; }
        public void setLeadSource(String leadSource) { this.leadSource = leadSource; }

        public Long getAssignedExecutiveId() { return assignedExecutiveId; }
        public void setAssignedExecutiveId(Long assignedExecutiveId) { this.assignedExecutiveId = assignedExecutiveId; }

        public String getAssignedExecutiveName() { return assignedExecutiveName; }
        public void setAssignedExecutiveName(String assignedExecutiveName) { this.assignedExecutiveName = assignedExecutiveName; }

        public String getDiscussion() { return discussion; }
        public void setDiscussion(String discussion) { this.discussion = discussion; }

        public LocalDate getVisitDate() { return visitDate; }
        public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

        public LocalDate getNextFollowupDate() { return nextFollowupDate; }
        public void setNextFollowupDate(LocalDate nextFollowupDate) { this.nextFollowupDate = nextFollowupDate; }

        public LeadStatus getStatus() { return status; }
        public void setStatus(LeadStatus status) { this.status = status; }

        public LeadPriority getPriority() { return priority; }
        public void setPriority(LeadPriority priority) { this.priority = priority; }

        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

        public LocalDateTime getUpdatedDate() { return updatedDate; }
        public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
    }
}