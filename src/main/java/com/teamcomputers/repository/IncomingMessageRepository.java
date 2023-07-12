package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.IncomingMessage;
@Repository
public interface IncomingMessageRepository extends JpaRepository<IncomingMessage, Long> {

	List<IncomingMessage> findByFrom(String Number);
	 @Query("SELECT DISTINCT om.from FROM IncomingMessage om")
	    List<String> findAllFrom();
}
