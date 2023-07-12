package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.NumberWiseChatDTO;
import com.teamcomputers.dto.showChatDTO;
import com.teamcomputers.service.ShowChatService;

@RestController
@RequestMapping("/showchats")
public class ChatController {
	

	private ShowChatService showChatService; 
	@Autowired
	 public ChatController(ShowChatService chatService) {
	        this.showChatService = chatService;
	    }

	 @GetMapping
	    public List<NumberWiseChatDTO> getNumberWiseChats() {
	        return showChatService.getNumberWiseChats();
	    }
	 
	 @GetMapping("/{from}")
	    public List<showChatDTO> getNumberWiseChatss(@PathVariable String from) {
	        return showChatService.getChatMessagesByNumber(from);
	    }
	 
	 
}
