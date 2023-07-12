package com.teamcomputers.dto;

public class OrderProductFilterDto {

	private long opId;

	private String orderProductName;

	public OrderProductFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderProductFilterDto(long opId, String orderProductName) {
		super();
		this.opId = opId;
		this.orderProductName = orderProductName;
	}

	public long getOpId() {
		return opId;
	}

	public void setOpId(long opId) {
		this.opId = opId;
	}

	public String getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

}
