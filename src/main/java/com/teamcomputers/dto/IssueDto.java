package com.teamcomputers.dto;

import java.time.LocalDateTime;



public class IssueDto {
	
	private long issueId;
	private String issueName;
	private String calculationSLA;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status=true;
	public long getIssueId() {
		return issueId;
	}
	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getCalculationSLA() {
		return calculationSLA;
	}
	public void setCalculationSLA(String calculationSLA) {
		this.calculationSLA = calculationSLA;
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
	public IssueDto(long issueId, String issueName, String calculationSLA, long createdBy, LocalDateTime createdDate,
			long updatedBy, LocalDateTime updatedDate) {
		super();
		this.issueId = issueId;
		this.issueName = issueName;
		this.calculationSLA = calculationSLA;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}
	public IssueDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
