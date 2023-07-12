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
@Table(name = "outgoing_MessageResponce")
public class OutgoingMessageResponce {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageid;

	@JsonProperty("recipient_whatsapp")
	private String recipientwhatsapp;

	@JsonProperty("recipient_type")
	private String recipient_type;

	@JsonProperty("message_type")
	private String message_type;

	@JsonProperty("source")
	private String source;

	@JsonProperty("x-apiheader")
	private String x_apiheader;

	@JsonProperty("type_text")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "messageid")
	private List<OutgoingType_text> outgoingType_text;

	@JsonProperty("responseStatus")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "messageid")
	private List<OutgoingResponseStatus> outgoingResponseStatus;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	// @JsonProperty("responseStatus")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "messageid")
	private List<OutgoingTypeTemplate> outgoingTypeTemplate;
	
	
	

	public OutgoingMessageResponce(long messageid, String recipientwhatsapp, String recipient_type, String message_type,
			String source, String x_apiheader, List<OutgoingType_text> outgoingType_text,
			List<OutgoingResponseStatus> outgoingResponseStatus, LocalDateTime createdDate,
			List<OutgoingTypeTemplate> outgoingTypeTemplate) {
		super();
		this.messageid = messageid;
		this.recipientwhatsapp = recipientwhatsapp;
		this.recipient_type = recipient_type;
		this.message_type = message_type;
		this.source = source;
		this.x_apiheader = x_apiheader;
		this.outgoingType_text = outgoingType_text;
		this.outgoingResponseStatus = outgoingResponseStatus;
		this.createdDate = createdDate;
		this.outgoingTypeTemplate = outgoingTypeTemplate;
	}

	public long getMessageid() {
		return messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

//	public String getRecipient_whatsapp() {
//		return recipient_whatsapp;
//	}
//
//	public void setRecipient_whatsapp(String recipient_whatsapp) {
//		this.recipient_whatsapp = recipient_whatsapp;
//	}

	
	public String getRecipient_type() {
		return recipient_type;
	}

	public String getRecipientwhatsapp() {
		return recipientwhatsapp;
	}

	public void setRecipientwhatsapp(String recipientwhatsapp) {
		this.recipientwhatsapp = recipientwhatsapp;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getX_apiheader() {
		return x_apiheader;
	}

	public void setX_apiheader(String x_apiheader) {
		this.x_apiheader = x_apiheader;
	}

	public List<OutgoingType_text> getType_text() {
		return outgoingType_text;
	}

	public void setType_text(List<OutgoingType_text> outgoingType_text) {
		this.outgoingType_text = outgoingType_text;
	}

	public List<OutgoingResponseStatus> getResponseStatus() {
		return outgoingResponseStatus;
	}

	public void setResponseStatus(List<OutgoingResponseStatus> outgoingResponseStatus) {
		this.outgoingResponseStatus = outgoingResponseStatus;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<OutgoingTypeTemplate> getOutgoingTypeTemplate() {
		return outgoingTypeTemplate;
	}

	public void setOutgoingTypeTemplate(List<OutgoingTypeTemplate> outgoingTypeTemplate) {
		this.outgoingTypeTemplate = outgoingTypeTemplate;
	}

	public OutgoingMessageResponce() {
		super();
		// TODO Auto-generated constructor stub
	}

}
