package com.teamcomputers.dto;

public class StatisticsDTO {

	 private String name;
	 private int total;
	public StatisticsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatisticsDTO(String name, int total) {
		super();
		this.name = name;
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	 
	 

}
