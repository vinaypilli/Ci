package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.WebhookEntity;

public interface WebhookRepository extends JpaRepository<WebhookEntity, Long> {

}
