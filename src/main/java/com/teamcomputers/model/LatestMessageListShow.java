package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class LatestMessageListShow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	private String mobileNo;
	private String fullName;
	private String lastMessage;
	private LocalDateTime time;	
	private boolean isopen;	
	private long count;
	private long fromId;
	private String messagetype;
	private long assignedto;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public boolean isIsopen() {
		return isopen;
	}
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}	
	public LatestMessageListShow(Long id, String mobileNo, String fullName, String lastMessage, LocalDateTime time,
			boolean isopen, long count, long fromId, long assignedto) {
		super();
		this.id = id;
		this.mobileNo = mobileNo;
		this.fullName = fullName;
		this.lastMessage = lastMessage;
		this.time = time;
		this.isopen = isopen;
		this.count = count;
		this.fromId = fromId;
		this.assignedto = assignedto;
	}
	
	
	
	public LatestMessageListShow(Long id, String mobileNo, String fullName, String lastMessage, LocalDateTime time,
			boolean isopen, long count, long fromId, String messagetype, long assignedto) {
		super();
		this.id = id;
		this.mobileNo = mobileNo;
		this.fullName = fullName;
		this.lastMessage = lastMessage;
		this.time = time;
		this.isopen = isopen;
		this.count = count;
		this.fromId = fromId;
		this.messagetype = messagetype;
		this.assignedto = assignedto;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
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
	
	public LatestMessageListShow() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
