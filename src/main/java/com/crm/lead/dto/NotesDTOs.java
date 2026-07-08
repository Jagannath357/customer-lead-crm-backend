package com.crm.lead.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NotesDTOs {

    public static class NoteRequest {
        @NotNull(message = "Lead ID is required")
        private Long leadId;
        @NotBlank(message = "Note content cannot be empty")
        private String note;
        @NotNull(message = "User ID is required")
        private Long createdById;

        public NoteRequest() {}

        public NoteRequest(Long leadId, String note, Long createdById) {
            this.leadId = leadId;
            this.note = note;
            this.createdById = createdById;
        }

        // Getters and Setters
        public Long getLeadId() { return leadId; }
        public void setLeadId(Long leadId) { this.leadId = leadId; }

        public String getNote() { return note; }
        public void setNote(String note) { this.note = note; }

        public Long getCreatedById() { return createdById; }
        public void setCreatedById(Long createdById) { this.createdById = createdById; }
    }

    public static class NoteResponse {
        private Long id;
        private Long leadId;
        private String note;
        private Long createdById;
        private String createdByName;
        private LocalDateTime createdDate;

        public NoteResponse() {}

        public NoteResponse(Long id, Long leadId, String note, Long createdById, String createdByName, LocalDateTime createdDate) {
            this.id = id;
            this.leadId = leadId;
            this.note = note;
            this.createdById = createdById;
            this.createdByName = createdByName;
            this.createdDate = createdDate;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getLeadId() { return leadId; }
        public void setLeadId(Long leadId) { this.leadId = leadId; }

        public String getNote() { return note; }
        public void setNote(String note) { this.note = note; }

        public Long getCreatedById() { return createdById; }
        public void setCreatedById(Long createdById) { this.createdById = createdById; }

        public String getCreatedByName() { return createdByName; }
        public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }

        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    }
}