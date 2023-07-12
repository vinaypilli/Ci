package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.OutgoingData;

public interface OutgoingDatasRepository extends JpaRepository<OutgoingData, Long> {

}
