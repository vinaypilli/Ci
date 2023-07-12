package com.teamcomputers.dto;

public class LabelFilterDto {

	private long id;
	private String name;

	public LabelFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabelFilterDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
