package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.OutgoingMessageShow;

public interface OutgoingMessageShowRepository extends JpaRepository<OutgoingMessageShow, Long>{

	List<OutgoingMessageShow> findByMobileNo(String mobileNo);
	
	OutgoingMessageShow findFirstByMobileNoOrderByTimeDesc(String mobileNo);
	
	
	List<OutgoingMessageShow> findByMobileNoAndAssignedto(String mobileNo, long assignedto);
}
