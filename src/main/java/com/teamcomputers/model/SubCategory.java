package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int subCategoryId;
	
	@Column(nullable = false,unique = true)
	private String subCategoryName;
	
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;
	
	@Column(nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	@Column
	private boolean status;
	
	
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cat_id")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status"})
	private Category category;
	
	
	@OneToMany(mappedBy = "subCategory")
	private List<ServiceTitleEntity> serviceTitleEntity;
	
	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategory(int subCategoryId, String subCategoryName, UserDao createdBy, LocalDateTime createdDate,
			UserDao updatedBy, LocalDateTime updatedDate, boolean status, Category category,
			List<ServiceTitleEntity> serviceTitleEntity) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.category = category;
		this.serviceTitleEntity = serviceTitleEntity;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
