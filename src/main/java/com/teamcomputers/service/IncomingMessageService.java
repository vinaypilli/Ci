package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.repository.IncomingMessageRepository;

@Service
public class IncomingMessageService {

	@Autowired
	private IncomingMessageRepository incomingMessageRepository;

	public IncomingMessage saveTypeText(IncomingMessage context) {

		return incomingMessageRepository.save(context);
	}
	public List<IncomingMessage> getAll(){
		return incomingMessageRepository.findAll();
	}
}
