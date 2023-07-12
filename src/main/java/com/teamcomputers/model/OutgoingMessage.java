package com.teamcomputers.model;

import java.util.List;

public class OutgoingMessage {

	private long id;

	private List<OutgoingMessageData> outgoingMessageData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OutgoingMessageData> getMessage() {
		return outgoingMessageData;
	}

	public void setMessage(List<OutgoingMessageData> outgoingMessageData) {
		this.outgoingMessageData = outgoingMessageData;
	}

}