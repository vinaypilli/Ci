package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class LabelDto {
	private long labelId;

	private String labelName;

	private int createdBy;

	private LocalDateTime createdDate;

	private int updatedBy;

	private LocalDateTime updatedDate;

	private boolean status = true;

	public LabelDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
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

	public LabelDto(long labelId, String labelName, int createdBy, LocalDateTime createdDate, int updatedBy,
			LocalDateTime updatedDate, boolean status) {
		super();
		this.labelId = labelId;
		this.labelName = labelName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}

}
