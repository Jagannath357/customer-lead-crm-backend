package com.crm.lead.repository;

import com.crm.lead.entity.LeadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadTypeRepository extends JpaRepository<LeadType, Long> {
    boolean existsByName(String name);
}