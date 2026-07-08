package com.crm.lead.dto;

import java.util.Map;

public class DashboardDTO {
    private long totalLeads;
    private long todayLeads;
    private long todayFollowups;
    private long pendingFollowups;
    private long hotLeads;
    private long closedWon;
    private long closedLost;
    
    private Map<String, Long> statusDistribution;
    private Map<String, Long> priorityDistribution;
    private Map<String, Long> leadTypeDistribution;

    public DashboardDTO() {}

    public DashboardDTO(long totalLeads, long todayLeads, long todayFollowups, long pendingFollowups, long hotLeads, long closedWon, long closedLost, Map<String, Long> statusDistribution, Map<String, Long> priorityDistribution, Map<String, Long> leadTypeDistribution) {
        this.totalLeads = totalLeads;
        this.todayLeads = todayLeads;
        this.todayFollowups = todayFollowups;
        this.pendingFollowups = pendingFollowups;
        this.hotLeads = hotLeads;
        this.closedWon = closedWon;
        this.closedLost = closedLost;
        this.statusDistribution = statusDistribution;
        this.priorityDistribution = priorityDistribution;
        this.leadTypeDistribution = leadTypeDistribution;
    }

    // Getters and Setters
    public long getTotalLeads() { return totalLeads; }
    public void setTotalLeads(long totalLeads) { this.totalLeads = totalLeads; }

    public long getTodayLeads() { return todayLeads; }
    public void setTodayLeads(long todayLeads) { this.todayLeads = todayLeads; }

    public long getTodayFollowups() { return todayFollowups; }
    public void setTodayFollowups(long todayFollowups) { this.todayFollowups = todayFollowups; }

    public long getPendingFollowups() { return pendingFollowups; }
    public void setPendingFollowups(long pendingFollowups) { this.pendingFollowups = pendingFollowups; }

    public long getHotLeads() { return hotLeads; }
    public void setHotLeads(long hotLeads) { this.hotLeads = hotLeads; }

    public long getClosedWon() { return closedWon; }
    public void setClosedWon(long closedWon) { this.closedWon = closedWon; }

    public long getClosedLost() { return closedLost; }
    public void setClosedLost(long closedLost) { this.closedLost = closedLost; }

    public Map<String, Long> getStatusDistribution() { return statusDistribution; }
    public void setStatusDistribution(Map<String, Long> statusDistribution) { this.statusDistribution = statusDistribution; }

    public Map<String, Long> getPriorityDistribution() { return priorityDistribution; }
    public void setPriorityDistribution(Map<String, Long> priorityDistribution) { this.priorityDistribution = priorityDistribution; }

    public Map<String, Long> getLeadTypeDistribution() { return leadTypeDistribution; }
    public void setLeadTypeDistribution(Map<String, Long> leadTypeDistribution) { this.leadTypeDistribution = leadTypeDistribution; }
}