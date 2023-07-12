package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookContact {

	private WebhookProfile profile;
	private String wa_id;

	public WebhookContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebhookContact(WebhookProfile profile, String wa_id) {
		super();
		this.profile = profile;
		this.wa_id = wa_id;
	}

	public WebhookProfile getProfile() {
		return profile;
	}

	public void setProfile(WebhookProfile profile) {
		this.profile = profile;
	}

	public String getWa_id() {
		return wa_id;
	}

	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}

}
