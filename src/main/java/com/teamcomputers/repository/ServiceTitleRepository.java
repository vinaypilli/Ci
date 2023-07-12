package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.model.SubCategory;

public interface ServiceTitleRepository extends JpaRepository<ServiceTitleEntity, Integer>{
	ServiceTitleEntity findByServiceName(String serviceName);

	List<ServiceTitleEntity> findAllByOrderByServiceIdAsc();

	List<ServiceTitleEntity> findByStatus(boolean b); 
	List<ServiceTitleEntity> findBySubCategoryAndStatusTrueOrderByServiceNameAsc(SubCategory subCategory);
	List<ServiceTitleEntity> findPriorityNameByServiceId(int serviceId);
	
}
