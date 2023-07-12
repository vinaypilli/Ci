package com.teamcomputers.dto;

public class OrderFilterDto {

	private long orderId;

	private String orderDescription;

	public OrderFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderFilterDto(long orderId, String orderDescription) {
		super();
		this.orderId = orderId;
		this.orderDescription = orderDescription;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

}
