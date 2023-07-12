package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.WebhookDelivery;

@Service
public interface WebhookDeliveryRepository extends JpaRepository<WebhookDelivery, Long> {

}
