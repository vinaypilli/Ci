package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookChange {

	private WebhookValue value;
	private String field;

	public WebhookChange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookChange(WebhookValue value, String field) {
		super();
		this.value = value;
		this.field = field;
	}

	public WebhookValue getValue() {
		return value;
	}

	public void setValue(WebhookValue value) {
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
