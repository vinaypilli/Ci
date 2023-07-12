package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.TempleteAdd;


@Repository
public interface TempleteAddRepository extends JpaRepository<TempleteAdd, Long> {
}

