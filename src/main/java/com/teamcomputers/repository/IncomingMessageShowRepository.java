package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.IncomingMessageShow;
import com.teamcomputers.model.OutgoingMessageShow;

public interface IncomingMessageShowRepository extends JpaRepository<IncomingMessageShow, Long>{

	List<IncomingMessageShow> findByMobileNo(String mobileNo);
	
	List<IncomingMessageShow> findByMobileNoAndAssignedto(String mobileNo, long assignedto);
	
	IncomingMessageShow findFirstByMobileNoOrderByTimeDesc(String mobileNo);
}
