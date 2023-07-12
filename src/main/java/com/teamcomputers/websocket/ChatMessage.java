package com.teamcomputers.websocket;

public class ChatMessage {
    private String messageType;
    private String recipient;
    private String content;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ChatMessage(String messageType, String recipient, String content) {
		super();
		this.messageType = messageType;
		this.recipient = recipient;
		this.content = content;
	}
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
