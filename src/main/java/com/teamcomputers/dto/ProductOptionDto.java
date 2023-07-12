package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class ProductOptionDto {

	private long id;
	private String parentCategory;
	private String optionName;
	private LocalDateTime createdDate;
	private long createdBy;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status;

	public ProductOptionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOptionDto(long id, String parentCategory, String optionName, LocalDateTime createdDate,
			long createdBy, long updatedBy, LocalDateTime updatedDate, boolean status) {
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

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
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