package com.teamcomputers.webhookMetaModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IncomingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String object;
	private String entryId;
	private String messagingProduct;
	private String displayPhoneNumber;
	private String phoneNumberId;
	private String contactName;
	private String waId;
	private String messageFrom;
	private String messageId;
	private String messageTimestamp;
	private String messageBody;
	private String messageType;
	private String changedField;

	public IncomingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IncomingModel(Long id, String object, String entryId, String messagingProduct, String displayPhoneNumber,
			String phoneNumberId, String contactName, String waId, String messageFrom, String messageId,
			String messageTimestamp, String messageBody, String messageType, String changedField) {
		super();
		this.id = id;
		this.object = object;
		this.entryId = entryId;
		this.messagingProduct = messagingProduct;
		this.displayPhoneNumber = displayPhoneNumber;
		this.phoneNumberId = phoneNumberId;
		this.contactName = contactName;
		this.waId = waId;
		this.messageFrom = messageFrom;
		this.messageId = messageId;
		this.messageTimestamp = messageTimestamp;
		this.messageBody = messageBody;
		this.messageType = messageType;
		this.changedField = changedField;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getMessagingProduct() {
		return messagingProduct;
	}

	public void setMessagingProduct(String messagingProduct) {
		this.messagingProduct = messagingProduct;
	}

	public String getDisplayPhoneNumber() {
		return displayPhoneNumber;
	}

	public void setDisplayPhoneNumber(String displayPhoneNumber) {
		this.displayPhoneNumber = displayPhoneNumber;
	}

	public String getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getWaId() {
		return waId;
	}

	public void setWaId(String waId) {
		this.waId = waId;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageTimestamp() {
		return messageTimestamp;
	}

	public void setMessageTimestamp(String messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getChangedField() {
		return changedField;
	}

	public void setChangedField(String changedField) {
		this.changedField = changedField;
	}

}
