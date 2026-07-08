package com.crm.lead.constant;

public enum LeadPriority {
    HOT,
    WARM,
    MEDIUM,
    COLD,
    HIGH, // Added to resolve the "HIGH" deserialization crash
    LOW,  // Added to safely catch low priority variations
    NOT_CUSTOMER,
    URGENT
}