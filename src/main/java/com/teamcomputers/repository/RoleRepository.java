package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	List<RoleEntity> findByStatusOrderByRoleNameAsc(boolean b);
	// List<RoleEntity> findAllByOrderByRoleNameAsc();
}
