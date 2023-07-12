package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

	List<DepartmentEntity> findByStatusOrderByDepartmentNameAsc(boolean b);
	// List<DepartmentEntity> findAllByOrderByDepartmentNameAsc();

}
