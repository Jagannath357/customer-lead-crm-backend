package com.crm.lead.dto;

import com.crm.lead.constant.LeadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FollowUpDTOs {

    public static class FollowUpRequest {
        @NotNull(message = "Lead ID is required")
        private Long leadId;
        @NotNull(message = "Followup date is required")
        private LocalDate followupDate;
        @NotBlank(message = "Discussion notes are required")
        private String discussion;
        private LocalDate nextFollowup;
        @NotNull(message = "Status is required")
        private LeadStatus status;
        @NotNull(message = "Created by User ID is required")
        private Long createdById;

        public FollowUpRequest() {}

        public FollowUpRequest(Long leadId, LocalDate followupDate, String discussion, LocalDate nextFollowup, LeadStatus status, Long createdById) {
            this.leadId = leadId;
            this.followupDate = followupDate;
            this.discussion = discussion;
            this.nextFollowup = nextFollowup;
            this.status = status;
            this.createdById = createdById;
        }

        // Getters and Setters
        public Long getLeadId() { return leadId; }
        public void setLeadId(Long leadId) { this.leadId = leadId; }

        public LocalDate getFollowupDate() { return followupDate; }
        public void setFollowupDate(LocalDate followupDate) { this.followupDate = followupDate; }

        public String getDiscussion() { return discussion; }
        public void setDiscussion(String discussion) { this.discussion = discussion; }

        public LocalDate getNextFollowup() { return nextFollowup; }
        public void setNextFollowup(LocalDate nextFollowup) { this.nextFollowup = nextFollowup; }

        public LeadStatus getStatus() { return status; }
        public void setStatus(LeadStatus status) { this.status = status; }

        public Long getCreatedById() { return createdById; }
        public void setCreatedById(Long createdById) { this.createdById = createdById; }
    }

    public static class FollowUpResponse {
        private Long id;
        private Long leadId;
        private LocalDate followupDate;
        private String discussion;
        private LocalDate nextFollowup;
        private LeadStatus status;
        private Long createdById;
        private String createdByName;
        private LocalDateTime createdDate;

        public FollowUpResponse() {}

        public FollowUpResponse(Long id, Long leadId, LocalDate followupDate, String discussion, LocalDate nextFollowup, LeadStatus status, Long createdById, String createdByName, LocalDateTime createdDate) {
            this.id = id;
            this.leadId = leadId;
            this.followupDate = followupDate;
            this.discussion = discussion;
            this.nextFollowup = nextFollowup;
            this.status = status;
            this.createdById = createdById;
            this.createdByName = createdByName;
            this.createdDate = createdDate;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getLeadId() { return leadId; }
        public void setLeadId(Long leadId) { this.leadId = leadId; }

        public LocalDate getFollowupDate() { return followupDate; }
        public void setFollowupDate(LocalDate followupDate) { this.followupDate = followupDate; }

        public String getDiscussion() { return discussion; }
        public void setDiscussion(String discussion) { this.discussion = discussion; }

        public LocalDate getNextFollowup() { return nextFollowup; }
        public void setNextFollowup(LocalDate nextFollowup) { this.nextFollowup = nextFollowup; }

        public LeadStatus getStatus() { return status; }
        public void setStatus(LeadStatus status) { this.status = status; }

        public Long getCreatedById() { return createdById; }
        public void setCreatedById(Long createdById) { this.createdById = createdById; }

        public String getCreatedByName() { return createdByName; }
        public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }

        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    }
}