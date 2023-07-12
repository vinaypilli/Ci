package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class MessageHistory {
	
	private String type;
	private String messagetype;
	private String message;
	private LocalDateTime time;	
	private String name;
	private boolean isopen;
	private String group;
	private String mobileNo;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIsopen() {
		return isopen;
	}
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public MessageHistory(String type, String messagetype, String message, LocalDateTime time, String name,
			boolean isopen, String group, String mobileNo) {
		super();
		this.type = type;
		this.messagetype = messagetype;
		this.message = message;
		this.time = time;
		this.name = name;
		this.isopen = isopen;
		this.group = group;
		this.mobileNo = mobileNo;
	}
	public MessageHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
