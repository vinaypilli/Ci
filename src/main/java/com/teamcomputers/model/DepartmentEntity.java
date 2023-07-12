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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class DepartmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;
	@Column(nullable = false,unique = true)
	private String departmentName;
	@Column(nullable = false,unique = true)
	private String departmentCode;
	@Column(nullable = false)
	private String departmentHead;
	private String description;
	
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
	@Column(nullable = false)
	private boolean status =true;

	@OneToMany(mappedBy = "department")
	private List<UserDao> users;

	public DepartmentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public DepartmentEntity(int departmentId, String departmentName, String departmentCode, String departmentHead,
			String description, UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy,
			LocalDateTime updatedDate, boolean status, List<UserDao> users) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
		this.departmentHead = departmentHead;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
		this.users = users;
	}



	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
