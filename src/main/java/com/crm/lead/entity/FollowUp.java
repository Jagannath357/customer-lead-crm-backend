package com.crm.lead.entity;

import com.crm.lead.constant.LeadStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow_up")
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private CustomerLead lead;

    @Column(name = "followup_date", nullable = false)
    private LocalDate followupDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String discussion;

    @Column(name = "next_followup")
    private LocalDate nextFollowup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LeadStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    public FollowUp() {}

    public FollowUp(Long id, CustomerLead lead, LocalDate followupDate, String discussion, LocalDate nextFollowup, LeadStatus status, User createdBy, LocalDateTime createdDate) {
        this.id = id;
        this.lead = lead;
        this.followupDate = followupDate;
        this.discussion = discussion;
        this.nextFollowup = nextFollowup;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerLead getLead() { return lead; }
    public void setLead(CustomerLead lead) { this.lead = lead; }

    public LocalDate getFollowupDate() { return followupDate; }
    public void setFollowupDate(LocalDate followupDate) { this.followupDate = followupDate; }

    public String getDiscussion() { return discussion; }
    public void setDiscussion(String discussion) { this.discussion = discussion; }

    public LocalDate getNextFollowup() { return nextFollowup; }
    public void setNextFollowup(LocalDate nextFollowup) { this.nextFollowup = nextFollowup; }

    public LeadStatus getStatus() { return status; }
    public void setStatus(LeadStatus status) { this.status = status; }

    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}