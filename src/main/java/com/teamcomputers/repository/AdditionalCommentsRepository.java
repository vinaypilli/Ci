package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.AdditionalComments;
import com.teamcomputers.model.TicketCreation;

public interface AdditionalCommentsRepository extends JpaRepository<AdditionalComments, Long> {

	List<AdditionalComments> findByTicketCreation(TicketCreation ticket);

	AdditionalComments findByFilename(String filename);

}
