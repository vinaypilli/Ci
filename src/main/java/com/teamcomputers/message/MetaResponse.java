package com.teamcomputers.message;

public class MetaResponse {

	private String status;
	private String challenge;
	public MetaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MetaResponse(String status, String challenge) {
		super();
		this.status = status;
		this.challenge = challenge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	
	
	
}
