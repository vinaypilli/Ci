package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.MessageNotes;

public interface MessageNotesRepository extends JpaRepository<MessageNotes, Long>{
	
	List<MessageNotes> findByMobileNo(String mobileNo);

}
