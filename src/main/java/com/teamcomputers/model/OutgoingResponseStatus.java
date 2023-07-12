package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "outgoing_responseStatus")
public class OutgoingResponseStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long responsed;

	private String status;

	private String message;

	@JsonProperty("data")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "responsed")
	private List<OutgoingData> outgoingData;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	public Long getResponsed() {
		return responsed;
	}

	public void setResponsed(Long responsed) {
		this.responsed = responsed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getResponceId() {
//		return responceId;
//	}
//
//	public void setResponceId(String responceId) {
//		this.responceId = responceId;
//	}

	public List<OutgoingData> getData() {
		return outgoingData;
	}

	public void setData(List<OutgoingData> outgoingData) {
		this.outgoingData = outgoingData;
	}

	public OutgoingResponseStatus(Long responsed, String status, String message, List<OutgoingData> outgoingData) {
		super();
		this.responsed = responsed;
		this.status = status;
		this.message = message;
		this.outgoingData = outgoingData;
	}

	public OutgoingResponseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

}
