package com.teamcomputers.dto;

public class SLAFilterDto {

	private long id;
	private String priorityName;

	public SLAFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLAFilterDto(long id, String priorityName) {
		super();
		this.id = id;
		this.priorityName = priorityName;
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

}
