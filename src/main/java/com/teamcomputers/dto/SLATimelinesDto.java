package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class SLATimelinesDto {

	private long id;
	private String priorityName;
	private int slaTimeInHours;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;
	private boolean status = true;

	public SLATimelinesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLATimelinesDto(long id, String priorityName, int slaTimeInHours, long createdBy, LocalDateTime createdDate,
			long updatedBy, LocalDateTime updatedDate, boolean status) {
		super();
		this.id = id;
		this.priorityName = priorityName;
		this.slaTimeInHours = slaTimeInHours;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
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

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public int getSlaTimeInHours() {
		return slaTimeInHours;
	}

	public void setSlaTimeInHours(int slaTimeInHours) {
		this.slaTimeInHours = slaTimeInHours;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

}
