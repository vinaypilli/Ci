package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class ServiceTitleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	@Column(nullable = false, unique = true)
	private String serviceName;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UpdatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao updatedBy;

	@UpdateTimestamp
	private LocalDateTime updatedDate;
	@Column
	private boolean status;
//	private String defaultPriority;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PriorityId", referencedColumnName = "id")
	@JsonIgnoreProperties({ "slaTimeInHours", "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SLATimelines defaultPriority;

	// @JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subCategoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SubCategory subCategory;

	public SLATimelines getDefaultPriority() {
		return defaultPriority;
	}

	public void setDefaultPriority(SLATimelines defaultPriority) {
		this.defaultPriority = defaultPriority;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public ServiceTitleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceTitleEntity(int serviceId, String serviceName, LocalDateTime createdDate, UserDao createdBy, UserDao updatedBy,
			LocalDateTime updatedDate, boolean status, SLATimelines defaultPriority, SubCategory subCategory) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.defaultPriority = defaultPriority;
		this.subCategory = subCategory;
	}

	
}