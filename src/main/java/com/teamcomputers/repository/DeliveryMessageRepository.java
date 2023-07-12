package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.DeliveryMessageEntity;

public interface DeliveryMessageRepository extends JpaRepository<DeliveryMessageEntity, String>{

}
