package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table
public class OutgoingStatus {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private String message;
    
    private String responceId;   
    
//    @ManyToOne
//	@JoinColumn(name = "mId")
//	@JsonIgnoreProperties({ "recipient_whatsapp", "recipient_type","message_type","source","x-apiheader","type_text" })
//    private MessageData messageData;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponceId() {
		return responceId;
	}

	public void setResponceId(String responceId) {
		this.responceId = responceId;
	}

//	public MessageData getMessageData() {
//		return messageData;
//	}
//
//	public void setMessageData(MessageData messageData) {
//		this.messageData = messageData;
//	}

	public OutgoingStatus(Long id, String status, String message, String responceId) {
		super();
		this.id = id;
		this.status = status;
		this.message = message;
		this.responceId = responceId;
		
	}

	public OutgoingStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
        

}
