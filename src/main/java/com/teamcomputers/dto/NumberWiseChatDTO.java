package com.teamcomputers.dto;

import java.util.List;

public class NumberWiseChatDTO {

	private String number;
    private List<showChatDTO> chats;
	public NumberWiseChatDTO(String number, List<showChatDTO> chats) {
		super();
		this.number = number;
		this.chats = chats;
	}
	public NumberWiseChatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<showChatDTO> getChats() {
		return chats;
	}
	public void setChats(List<showChatDTO> chats) {
		this.chats = chats;
	}
	
    
}

