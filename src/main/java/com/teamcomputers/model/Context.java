package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Context {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contextId;
	private String ncmessage_id;
	private String message_id;

	public Context() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Context(Long contextId, String ncmessage_id, String message_id) {
		super();
		this.contextId = contextId;
		this.ncmessage_id = ncmessage_id;
		this.message_id = message_id;
	}

	public String getNcmessage_id() {
		return ncmessage_id;
	}

	public void setNcmessage_id(String ncmessage_id) {
		this.ncmessage_id = ncmessage_id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public Long getContextId() {
		return contextId;
	}

	public void setContextId(Long contextId) {
		this.contextId = contextId;
	}
}
