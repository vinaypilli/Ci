package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class MessageNotes {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	private String type;
	private String messagetype;
	private String message;
	@CreationTimestamp
	private LocalDateTime time;	
	private String name;
	private boolean isopen;
	private String mobileNo;
	@Column(name = "groupin")
	private String group;
	
	private long fromId;

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
	private long assignedto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public boolean getIsopen() {
		return isopen;
	}
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public MessageNotes(Long id, String type, String messagetype, String message, LocalDateTime time, String name,
			boolean isopen, String mobileNo, String group, long fromId, long assignedto) {
		super();
		this.id = id;
		this.type = type;
		this.messagetype = messagetype;
		this.message = message;
		this.time = time;
		this.name = name;
		this.isopen = isopen;
		this.mobileNo = mobileNo;
		this.group = group;
		this.fromId = fromId;
		this.assignedto = assignedto;
	}
	public MessageNotes() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
	
	
}
