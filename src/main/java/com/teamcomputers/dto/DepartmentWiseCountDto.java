package com.teamcomputers.dto;

public class DepartmentWiseCountDto {

	private int departmentId ;
	private String departmentName;
	private long count;
	public DepartmentWiseCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DepartmentWiseCountDto(int departmentId, String departmentName, long count) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.count = count;
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}
