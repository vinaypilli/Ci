package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teamcomputers.model.OutgoingMessageResponce;

public interface OutgoingMessageResponceRepository extends JpaRepository<OutgoingMessageResponce, Long>{

	List<OutgoingMessageResponce> findByRecipientwhatsapp(String recipientwhatsapp);
	 @Query("SELECT DISTINCT om.recipientwhatsapp FROM OutgoingMessageResponce om")
	    List<String> findAllRecipientwhatsapp();
}
