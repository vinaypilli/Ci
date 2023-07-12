package com.teamcomputers.dto;

import java.time.LocalDateTime;

import com.teamcomputers.model.Customer;
import com.teamcomputers.model.TicketStatusEnum;

public class TicketDto {
	private Long ticketId;
	private String ticketNo;
	private String emailId;
	private long categoryId;
	private long subCategoryId;
	private long serviceTitleId;
	private long alternativeContactNo;
	private long priority;
	private long issueId;
	private long departmentId;
	private long assignedTo;
	private String shortNotes;
	private String comment;
//	private long comment;
//	private byte[] attach_file;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private long resolveBy;
	private long createForUser;
	private String resolveTimeStamp;
	private String sla;
	private boolean status = true;
	private TicketStatusEnum ticketStatus;
	private long customerId;

	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public TicketStatusEnum getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatusEnum ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public long getCreateForUser() {
		return createForUser;
	}

	public void setCreateForUser(long createForUser) {
		this.createForUser = createForUser;
	}

	public long getResolveBy() {
		return resolveBy;
	}

	public void setResolveBy(long resolveBy) {
		this.resolveBy = resolveBy;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public long getServiceTitleId() {
		return serviceTitleId;
	}

	public void setServiceTitleId(long serviceTitleId) {
		this.serviceTitleId = serviceTitleId;
	}

	public long getAlternativeContactNo() {
		return alternativeContactNo;
	}

	public void setAlternativeContactNo(long alternativeContactNo) {
		this.alternativeContactNo = alternativeContactNo;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public long getIssueId() {
		return issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getShortNotes() {
		return shortNotes;
	}

	public void setShortNotes(String shortNotes) {
		this.shortNotes = shortNotes;
	}

//	public byte[] getAttach_file() {
//		return attach_file;
//	}
//
//	public void setAttach_file(byte[] attach_file) {
//		this.attach_file = attach_file;
//	}

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

	public String getResolveTimeStamp() {
		return resolveTimeStamp;
	}

	public void setResolveTimeStamp(String resolveTimeStamp) {
		this.resolveTimeStamp = resolveTimeStamp;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

	public TicketDto(Long ticketId, String ticketNo, String emailId, long categoryId, long subCategoryId,
			long serviceTitleId, long alternativeContactNo, long priority, long issueId, long departmentId,
			long assignedTo, String shortNotes, String comment, long createdBy, LocalDateTime createdDate,
			long updatedBy, LocalDateTime updatedDate, long resolveBy, long createForUser, String resolveTimeStamp,
			String sla, boolean status, TicketStatusEnum ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.ticketNo = ticketNo;
		this.emailId = emailId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.serviceTitleId = serviceTitleId;
		this.alternativeContactNo = alternativeContactNo;
		this.priority = priority;
		this.issueId = issueId;
		this.departmentId = departmentId;
		this.assignedTo = assignedTo;
		this.shortNotes = shortNotes;
		this.comment = comment;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.resolveBy = resolveBy;
		this.createForUser = createForUser;
		this.resolveTimeStamp = resolveTimeStamp;
		this.sla = sla;
		this.status = status;
		this.ticketStatus = ticketStatus;
	}
	
	

	public TicketDto(Long ticketId, String ticketNo, String emailId, long categoryId, long subCategoryId,
			long serviceTitleId, long alternativeContactNo, long priority, long issueId, long departmentId,
			long assignedTo, String shortNotes, String comment, long createdBy, LocalDateTime createdDate,
			long updatedBy, LocalDateTime updatedDate, long resolveBy, long createForUser, String resolveTimeStamp,
			String sla, boolean status, TicketStatusEnum ticketStatus, long customerId) {
		super();
		this.ticketId = ticketId;
		this.ticketNo = ticketNo;
		this.emailId = emailId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.serviceTitleId = serviceTitleId;
		this.alternativeContactNo = alternativeContactNo;
		this.priority = priority;
		this.issueId = issueId;
		this.departmentId = departmentId;
		this.assignedTo = assignedTo;
		this.shortNotes = shortNotes;
		this.comment = comment;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.resolveBy = resolveBy;
		this.createForUser = createForUser;
		this.resolveTimeStamp = resolveTimeStamp;
		this.sla = sla;
		this.status = status;
		this.ticketStatus = ticketStatus;
		this.customerId = customerId;
	}

	public TicketDto() {
		// TODO Auto-generated constructor stub
	}
	

}
