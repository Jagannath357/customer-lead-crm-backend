package com.crm.lead.repository;

import com.crm.lead.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByLeadIdOrderByCreatedDateDesc(Long leadId);
}