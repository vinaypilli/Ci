package com.teamcomputers.webhookIncomingRequestDto;

public class WebhookLocation {

	private double latitude;
	private double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public WebhookLocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public WebhookLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
