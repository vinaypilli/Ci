package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookStatus {

	private String id;
    private String status;
    private String timestamp;
    private String recipient_id;
    private WebhookConversation conversation;
    private WebhookPricing pricing;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public WebhookConversation getConversation() {
		return conversation;
	}
	public void setConversation(WebhookConversation conversation) {
		this.conversation = conversation;
	}
	public WebhookPricing getPricing() {
		return pricing;
	}
	public void setPricing(WebhookPricing pricing) {
		this.pricing = pricing;
	}
	public WebhookStatus(String id, String status, String timestamp, String recipient_id,
			WebhookConversation conversation, WebhookPricing pricing) {
		super();
		this.id = id;
		this.status = status;
		this.timestamp = timestamp;
		this.recipient_id = recipient_id;
		this.conversation = conversation;
		this.pricing = pricing;
	}
	public WebhookStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
