package com.teamcomputers.dto;

public class SubCategoryFilterDto {
	
	private int subCategoryId;
	private String subCategoryName;
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
	public SubCategoryFilterDto(int subCategoryId, String subCategoryName) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
	}
	public SubCategoryFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
