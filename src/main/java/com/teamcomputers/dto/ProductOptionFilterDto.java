package com.teamcomputers.dto;

public class ProductOptionFilterDto {

	private long id;
	private String parentCategory;
	private String optionName;
	public ProductOptionFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductOptionFilterDto(long id, String parentCategory, String optionName) {
		super();
		this.id = id;
		this.parentCategory = parentCategory;
		this.optionName = optionName;
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
	
	
}
