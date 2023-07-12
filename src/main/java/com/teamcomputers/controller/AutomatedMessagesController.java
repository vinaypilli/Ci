package com.teamcomputers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.AutomatedMessageDto;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ApiResponseGetAll;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.AutomatedMessages;
import com.teamcomputers.service.AutomatedMessagesService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/automatedMessage")
public class AutomatedMessagesController {

	@Autowired
	private AutomatedMessagesService automatedMessagesService;

	String message = "";
	String status = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody AutomatedMessageDto automatedMessageDto) {
		return this.automatedMessagesService.add(automatedMessageDto);
	}

	@GetMapping("/{automatedMessageId}")
	private ResponseEntity<?> getById(@PathVariable long automatedMessageId) {
		return automatedMessagesService.getById(automatedMessageId);
	}


	@GetMapping
	public ResponseEntity<ApiResponseFormat> getAll() {
		return automatedMessagesService.getAll();
	}

	@PutMapping("/{automatedMessageId}")
	private ResponseEntity<ErrorResponse> update(@PathVariable long automatedMessageId, @RequestBody AutomatedMessageDto automatedMessageDto) {
		automatedMessageDto.setAutomatedMessagesId(automatedMessageId);
		return automatedMessagesService.update(automatedMessageDto);

	}

	@DeleteMapping("/{automatedMessageId}")
	private ResponseEntity<ErrorResponse> delete(@PathVariable long automatedMessageId) throws NotFoundException {

		return automatedMessagesService.deleteById(automatedMessageId);

	}
}
