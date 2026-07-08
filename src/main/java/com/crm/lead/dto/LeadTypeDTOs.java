package com.crm.lead.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class LeadTypeDTOs {

    public static class LeadTypeRequest {
        @NotBlank(message = "Lead type name is required")
        private String name;
        private String description;
        private String status;

        public LeadTypeRequest() {}

        public LeadTypeRequest(String name, String description, String status) {
            this.name = name;
            this.description = description;
            this.status = status;
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class LeadTypeResponse {
        private Long id;
        private String name;
        private String description;
        private String status;
        private LocalDateTime createdDate;

        public LeadTypeResponse() {}

        public LeadTypeResponse(Long id, String name, String description, String status, LocalDateTime createdDate) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.status = status;
            this.createdDate = createdDate;
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public LocalDateTime getCreatedDate() { return createdDate; }
        public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    }
}