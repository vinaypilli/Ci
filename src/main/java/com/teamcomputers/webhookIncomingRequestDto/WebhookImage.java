package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookImage {

	private String mime_type;
    private String sha256;
    private String id;
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public String getSha256() {
		return sha256;
	}
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public WebhookImage(String mime_type, String sha256, String id) {
		super();
		this.mime_type = mime_type;
		this.sha256 = sha256;
		this.id = id;
	}
	public WebhookImage() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
