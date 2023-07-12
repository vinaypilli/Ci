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
public class OutgoingActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@JsonIgnore
	private long id;

	private String mobileNo;

	private String type;

	private long fromId;

	private long assignedto;

	@Column(nullable = false)
	private String messagetype;

	@CreationTimestamp
	private LocalDateTime time;

	private String message;
	
	@Column(name = "groupout")
	private String group;
	
	private boolean isopen;

	
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OutgoingActivity(long id, String mobileNo, String type, long fromId, long assignedto, String messagetype,
			LocalDateTime time, String message, String group, boolean isopen) {
		super();
		this.id = id;
		this.mobileNo = mobileNo;
		this.type = type;
		this.fromId = fromId;
		this.assignedto = assignedto;
		this.messagetype = messagetype;
		this.time = time;
		this.message = message;
		this.group = group;
		this.isopen = isopen;
	}

	public boolean getIsopen() {
		return isopen;
	}

	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}

	

	public OutgoingActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
