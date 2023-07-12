package com.teamcomputers.webhookIncomingRequestDto;

import java.util.List;

public class WebhookValue {

	private String messaging_product;
	private WebhookMetadata metadata;
	private List<WebhookContact> contacts;
	private List<WebhookMessage> messages;
	private List<WebhookStatus> statuses;

	public WebhookValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookValue(String messaging_product, WebhookMetadata metadata, List<WebhookContact> contacts,
			List<WebhookMessage> messages, List<WebhookStatus> statuses) {
		super();
		this.messaging_product = messaging_product;
		this.metadata = metadata;
		this.contacts = contacts;
		this.messages = messages;
		this.statuses = statuses;
	}

	public List<WebhookStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<WebhookStatus> statuses) {
		this.statuses = statuses;
	}

	public String getMessaging_product() {
		return messaging_product;
	}

	public void setMessaging_product(String messaging_product) {
		this.messaging_product = messaging_product;
	}

	public WebhookMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(WebhookMetadata metadata) {
		this.metadata = metadata;
	}

	public List<WebhookContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<WebhookContact> contacts) {
		this.contacts = contacts;
	}

	public List<WebhookMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<WebhookMessage> messages) {
		this.messages = messages;
	}

}
