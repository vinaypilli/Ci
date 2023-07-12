package com.teamcomputers.webhookIncomingRequestDto;

import java.util.List;

public class WebhookEntry {

	private String id;
	private List<WebhookChange> changes;

	public WebhookEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookEntry(String id, List<WebhookChange> changes) {
		super();
		this.id = id;
		this.changes = changes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<WebhookChange> getChanges() {
		return changes;
	}

	public void setChanges(List<WebhookChange> changes) {
		this.changes = changes;
	}

}
