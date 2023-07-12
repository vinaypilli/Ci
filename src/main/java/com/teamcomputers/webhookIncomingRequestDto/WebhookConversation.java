package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookConversation {

	 private String id;
	    private WebhookOrigin origin;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public WebhookOrigin getOrigin() {
			return origin;
		}
		public void setOrigin(WebhookOrigin origin) {
			this.origin = origin;
		}
		public WebhookConversation(String id, WebhookOrigin origin) {
			super();
			this.id = id;
			this.origin = origin;
		}
		public WebhookConversation() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	
}
