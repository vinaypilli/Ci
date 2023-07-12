package com.teamcomputers.dto;

public class ProductFilterDto {

	private long productId;
	private String productName;

	public ProductFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductFilterDto(long productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
