package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.OutgoingActivity;

public interface OutgoingActivityRepository extends JpaRepository<OutgoingActivity, Long>{

	List<OutgoingActivity> findByMobileNo(String mobileNo);
//	List<OutgoingActivity> findByMobileNoAndMessagetype(Object mobileNo, Object messagetype);

//	OutgoingActivity findFirstByMobileNoAndMessageTypeOrderByCreatedAtDesc(String mobileNo, String messageType);

	OutgoingActivity findFirstByMobileNoAndMessagetypeOrderByTimeDesc(String mobileNo,String messageType);

	OutgoingActivity findFirstByMobileNoOrderByTimeDesc(String mobileNo);

	
}
