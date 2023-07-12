package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class SLACategoryWiseDto {

	private long sId;
	private long categoryId;
	private long subCategoryId;
	private long serviceTitleId;
	private long priority;
	private int timeline;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status = true;

	public SLACategoryWiseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLACategoryWiseDto(long sId, long categoryId, long subCategoryId, long serviceTitleId, long priority,
			int timeline, long createdBy, LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate,
			boolean status) {
		super();
		this.sId = sId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.serviceTitleId = serviceTitleId;
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

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public int getTimeline() {
		return timeline;
	}

	public void setTimeline(int timeline) {
		this.timeline = timeline;
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

}
