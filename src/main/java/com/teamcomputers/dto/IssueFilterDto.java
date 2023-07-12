package com.teamcomputers.dto;

public class IssueFilterDto {
	private long issueId;
	private String issueName;
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
	public IssueFilterDto(long issueId, String issueName) {
		super();
		this.issueId = issueId;
		this.issueName = issueName;
	}
	public IssueFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
