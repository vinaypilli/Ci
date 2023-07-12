package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.AutomatedMessages;

@Repository
public interface AutomatedMessagesRepository extends JpaRepository<AutomatedMessages, Long>{

}
