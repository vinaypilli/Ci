package com.teamcomputers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="webhook_incoming")
public class WebhookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long webhookId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "webhookId")
	private List<IncomingMessage> incoming_message;

	public long getWebhookId() {
		return webhookId;
	}

	public void setWebhookId(long webhookId) {
		this.webhookId = webhookId;
	}

	public List<IncomingMessage> getIncoming_message() {
		return incoming_message;
	}

	public void setIncoming_message(List<IncomingMessage> incoming_message) {
		this.incoming_message = incoming_message;
	}

	public WebhookEntity(long webhookId, List<IncomingMessage> incoming_message) {
		super();
		this.webhookId = webhookId;
		this.incoming_message = incoming_message;
	}

	public WebhookEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
