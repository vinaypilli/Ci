package com.teamcomputers.dto;

public class ProductCategoryFilterDto {

	private long productCategoryId;

	private String productCategoryName;
	
	private String fileUrl;

	public ProductCategoryFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ProductCategoryFilterDto(long productCategoryId, String productCategoryName, String fileUrl) {
		super();
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.fileUrl = fileUrl;
	}



	public String getFileUrl() {
		return fileUrl;
	}



	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}



	public long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

}
