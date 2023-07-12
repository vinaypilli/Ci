package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Context;

public interface ContextRepository extends JpaRepository<Context, Long> {

}
