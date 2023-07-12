package com.teamcomputers.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.ImageType;
import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.model.LocationType;
import com.teamcomputers.model.TextType;
import com.teamcomputers.model.WebhookEntity;
import com.teamcomputers.service.IncomingMessageService;
import com.teamcomputers.service.WebhookIncomingService;
import com.teamcomputers.websocket.ChatWebSocketHandler;

@RestController
//@RequestMapping("/webhook")
public class WebhookController {

	@Autowired
    private ChatWebSocketHandler chatWebSocketHandler;
    
    @Autowired
    private ObjectMapper mapper;
	@Autowired
	private WebhookIncomingService webhookIncomingService;

	@PostMapping
	public ApiResponse add(@RequestBody WebhookEntity webhookEntity) throws IOException, MessagingException {
		
//		ApiResponse as=webhookIncomingService.save(webhookEntity);
//        if(as.getStatus().equalsIgnoreCase("success")) {
//	    String responseData = mapper.writeValueAsString(webhookEntity); 
//	    String str= chatWebSocketHandler.sendMessageToAllSessions(responseData);
//        }
		return webhookIncomingService.save(webhookEntity);
	}

	@GetMapping
	public List<WebhookEntity> getAllUsers() {
		return webhookIncomingService.getAll();
	}

}
