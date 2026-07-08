package com.crm.lead.repository;

import com.crm.lead.constant.LeadPriority;
import com.crm.lead.constant.LeadStatus;
import com.crm.lead.entity.CustomerLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerLeadRepository extends JpaRepository<CustomerLead, Long>, JpaSpecificationExecutor<CustomerLead> {

    List<CustomerLead> findByAssignedExecutiveId(Long executiveId);

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE c.status = :status")
    long countByStatus(@Param("status") LeadStatus status);

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE c.priority = :priority")
    long countByPriority(@Param("priority") LeadPriority priority);

    @Query("SELECT c FROM CustomerLead c WHERE c.nextFollowupDate = :date")
    List<CustomerLead> findLeadsWithFollowUpOn(@Param("date") LocalDate date);
    
    @Query("SELECT c FROM CustomerLead c WHERE c.nextFollowupDate < :date AND c.status NOT IN ('CLOSED_WON', 'CLOSED_LOST')")
    List<CustomerLead> findOverdueLeads(@Param("date") LocalDate date);
}