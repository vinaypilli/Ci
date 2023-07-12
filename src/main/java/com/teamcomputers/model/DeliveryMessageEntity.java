package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "deliveryMessage")
public class DeliveryMessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long deliverymessageId;
	private String ncmessage_id;
	private long recipient;
	private String status;
	private String status_remark;
	private String received_at;
	private String source;
	@CreationTimestamp
	private LocalDateTime createdDate;

	public DeliveryMessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryMessageEntity(long deliverymessageId, String ncmessage_id, long recipient, String status,
			String status_remark, String received_at, String source, LocalDateTime createdDate) {
		super();
		this.deliverymessageId = deliverymessageId;
		this.ncmessage_id = ncmessage_id;
		this.recipient = recipient;
		this.status = status;
		this.status_remark = status_remark;
		this.received_at = received_at;
		this.source = source;
		this.createdDate = createdDate;
	}

	public long getDeliverymessageId() {
		return deliverymessageId;
	}

	public void setDeliverymessageId(long deliverymessageId) {
		this.deliverymessageId = deliverymessageId;
	}

	public String getNcmessage_id() {
		return ncmessage_id;
	}

	public void setNcmessage_id(String ncmessage_id) {
		this.ncmessage_id = ncmessage_id;
	}

	public long getRecipient() {
		return recipient;
	}

	public void setRecipient(long recipient) {
		this.recipient = recipient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getReceived_at() {
		return received_at;
	}

	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
