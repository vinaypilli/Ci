package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {

}
