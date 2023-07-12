package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long issueId;
	private String issueName;
	private String calculationSLA;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId",nullable = false,updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory", "serviceTitle"})
	private UserDao createdBy;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UpdatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory", "serviceTitle"})
	private UserDao updatedBy;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	private boolean status;
	
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
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public UserDao getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}
	public UserDao getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserDao updatedBy) {
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
	
	public Issue(long issueId, String issueName, String calculationSLA, LocalDateTime createdDate, UserDao createdBy,
			UserDao updatedBy, LocalDateTime updatedDate, boolean status) {
		super();
		this.issueId = issueId;
		this.issueName = issueName;
		this.calculationSLA = calculationSLA;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
