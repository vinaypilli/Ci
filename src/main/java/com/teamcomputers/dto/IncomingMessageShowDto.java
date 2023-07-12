package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class IncomingMessageShowDto {

	private String msgreplayer;
	private LocalDateTime replayertime;
	private String replayermsg;
	private String type;
	private String number;
	public String getReplayermsg() {
		return replayermsg;
	}
	public void setReplayermsg(String replayermsg) {
		this.replayermsg = replayermsg;
	}
	public LocalDateTime getReplayertime() {
		return replayertime;
	}
	public void setReplayertime(LocalDateTime replayertime) {
		this.replayertime = replayertime;
	}
	public String getMsgreplayer() {
		return msgreplayer;
	}
	public void setMsgreplayer(String msgreplayer) {
		this.msgreplayer = msgreplayer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public IncomingMessageShowDto(String msgreplayer, LocalDateTime replayertime, String replayermsg, String type,
			String number) {
		super();
		this.msgreplayer = msgreplayer;
		this.replayertime = replayertime;
		this.replayermsg = replayermsg;
		this.type = type;
		this.number = number;
	}
	public IncomingMessageShowDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
