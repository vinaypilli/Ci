package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.LatestMessageListShow;

public interface LatestMessageListShowRepository extends JpaRepository<LatestMessageListShow, Long>{

	
	LatestMessageListShow findByMobileNo(String mobileNo);
//	List<LatestMessageListShow> findByIsOpen(boolean isOpen);
	
//	List<LatestMessageListShow> findByIsopen(boolean isopen);
	List<LatestMessageListShow> findByIsopenOrderByTimeDesc(boolean isOpen);

	List<LatestMessageListShow> findByIsopenAndAssignedtoOrderByTimeDesc(boolean isOpen, long assignedto);
	
}
