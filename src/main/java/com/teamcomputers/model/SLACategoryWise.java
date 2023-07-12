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
@Table(name = "sla_category_wise")
public class SLACategoryWise {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private Category category;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id", referencedColumnName = "subCategoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category" })
	private SubCategory subCategory;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Service_id", referencedColumnName = "serviceId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "defaultPriority",
			"category", "subCategory" })
	private ServiceTitleEntity serviceTitle;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PriorityId", referencedColumnName = "id")
	@JsonIgnoreProperties({ "slaTimeInHours", "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SLATimelines priority;

	private int timeline;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

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

	public SLACategoryWise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLACategoryWise(long sId, Category category, SubCategory subCategory, ServiceTitleEntity serviceTitle,
			SLATimelines priority, int timeline, UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy,
			LocalDateTime updatedDate, boolean status) {
		super();
		this.sId = sId;
		this.category = category;
		this.subCategory = subCategory;
		this.serviceTitle = serviceTitle;
		this.priority = priority;
		this.timeline = timeline;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}

	public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public ServiceTitleEntity getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(ServiceTitleEntity serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public SLATimelines getPriority() {
		return priority;
	}

	public void setPriority(SLATimelines priority) {
		this.priority = priority;
	}

	public int getTimeline() {
		return timeline;
	}

	public void setTimeline(int timeline) {
		this.timeline = timeline;
	}

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

}
