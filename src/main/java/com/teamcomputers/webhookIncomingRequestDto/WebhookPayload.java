package com.teamcomputers.webhookIncomingRequestDto;

import java.util.List;

public class WebhookPayload {
	private String object;
	private List<WebhookEntry> entry;

	public WebhookPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookPayload(String object, List<WebhookEntry> entry) {
		super();
		this.object = object;
		this.entry = entry;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<WebhookEntry> getEntry() {
		return entry;
	}

	public void setEntry(List<WebhookEntry> entry) {
		this.entry = entry;
	}

}
