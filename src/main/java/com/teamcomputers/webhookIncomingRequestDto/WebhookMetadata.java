package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookMetadata {

	private String display_phone_number;
	private String phone_number_id;

	public WebhookMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookMetadata(String display_phone_number, String phone_number_id) {
		super();
		this.display_phone_number = display_phone_number;
		this.phone_number_id = phone_number_id;
	}

	public String getDisplay_phone_number() {
		return display_phone_number;
	}

	public void setDisplay_phone_number(String display_phone_number) {
		this.display_phone_number = display_phone_number;
	}

	public String getPhone_number_id() {
		return phone_number_id;
	}

	public void setPhone_number_id(String phone_number_id) {
		this.phone_number_id = phone_number_id;
	}

}
