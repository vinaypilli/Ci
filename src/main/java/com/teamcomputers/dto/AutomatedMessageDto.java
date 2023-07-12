package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class AutomatedMessageDto {

	private long automatedMessagesId;
	private String receivedMessage;
	private String responseMessage;
	private long workflowId;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status = true;

	public AutomatedMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AutomatedMessageDto(long automatedMessagesId, String receivedMessage, String responseMessage,
			long workflowId, long createdBy, LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate,
			boolean status) {
		super();
		this.automatedMessagesId = automatedMessagesId;
		this.receivedMessage = receivedMessage;
		this.responseMessage = responseMessage;
		this.workflowId = workflowId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}

	public long getAutomatedMessagesId() {
		return automatedMessagesId;
	}

	public void setAutomatedMessagesId(long automatedMessagesId) {
		this.automatedMessagesId = automatedMessagesId;
	}

	public String getReceivedMessage() {
		return receivedMessage;
	}

	public void setReceivedMessage(String receivedMessage) {
		this.receivedMessage = receivedMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(long workflowId) {
		this.workflowId = workflowId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
