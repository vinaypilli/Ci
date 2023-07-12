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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ProductOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true)
	private String parentCategory;
	@Column(nullable = false, unique = true)
	private String optionName;
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
	private boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
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

	public ProductOption(long id, String parentCategory, String optionName, LocalDateTime createdDate,
			UserDao createdBy, UserDao updatedBy, LocalDateTime updatedDate, boolean status) {
		super();
		this.id = id;
		this.parentCategory = parentCategory;
		this.optionName = optionName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}

	public ProductOption() {
		super();
		// TODO Auto-generated constructor stub
	}

}
