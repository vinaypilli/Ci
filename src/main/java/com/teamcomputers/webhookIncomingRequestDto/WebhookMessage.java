package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookMessage {

	private String from;
	private String id;
	private String timestamp;
	private WebhookText text;
	private WebhookLocation location;
    private WebhookImage image;
    private WebhookVideo video;
	private String type;

	public WebhookMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public WebhookMessage(String from, String id, String timestamp, WebhookText text, WebhookLocation location,
			WebhookImage image, WebhookVideo video, String type) {
		super();
		this.from = from;
		this.id = id;
		this.timestamp = timestamp;
		this.text = text;
		this.location = location;
		this.image = image;
		this.video = video;
		this.type = type;
	}



	public WebhookLocation getLocation() {
		return location;
	}



	public void setLocation(WebhookLocation location) {
		this.location = location;
	}



	public WebhookImage getImage() {
		return image;
	}



	public void setImage(WebhookImage image) {
		this.image = image;
	}



	public WebhookVideo getVideo() {
		return video;
	}



	public void setVideo(WebhookVideo video) {
		this.video = video;
	}



	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public WebhookText getText() {
		return text;
	}

	public void setText(WebhookText text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
