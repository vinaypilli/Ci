package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OutgoingMessageShow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;
	private String type;
	private String messagetype;
	private String message;
	private LocalDateTime time;	
	private String name;
	private boolean isopen;
	@Column(name = "groupout")
	private String group;
	private String mobileNo;
	private long fromId;
	private long assignedto;
	
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
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
	public OutgoingMessageShow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getFromId() {
		return fromId;
	}
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}
	public long getAssignedto() {
		return assignedto;
	}
	public void setAssignedto(long assignedto) {
		this.assignedto = assignedto;
	}
	public OutgoingMessageShow(long id, String type, String messagetype, String message, LocalDateTime time,
			String name, boolean isopen, String group, String mobileNo, long fromId, long assignedto) {
		super();
		this.id = id;
		this.type = type;
		this.messagetype = messagetype;
		this.message = message;
		this.time = time;
		this.name = name;
		this.isopen = isopen;
		this.group = group;
		this.mobileNo = mobileNo;
		this.fromId = fromId;
		this.assignedto = assignedto;
	}
	
	
	
	

}
