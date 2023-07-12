package com.teamcomputers.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.teamcomputers.model.Customer;
import com.teamcomputers.model.OrderDetails;
import com.teamcomputers.model.PaymentEnum;
import com.teamcomputers.model.UserDao;

public class OrderDto {

	private long orderId;
    private List<OrderDetails> orderDetails;
    private String customerMob;
    private long createdBy;
    private long updatedBy;    
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private double discount;
    private double subtotal;
    private double cgst;
    private double sgst;
    private double service;
    private double grand;
    private double totaltax;
    private PaymentEnum paymentType;
    private boolean status=true;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getCustomerMob() {
		return customerMob;
	}
	public void setCustomerMob(String customerMob) {
		this.customerMob = customerMob;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getCgst() {
		return cgst;
	}
	public void setCgst(double cgst) {
		this.cgst = cgst;
	}
	public double getSgst() {
		return sgst;
	}
	public void setSgst(double sgst) {
		this.sgst = sgst;
	}
	public double getService() {
		return service;
	}
	public void setService(double service) {
		this.service = service;
	}
	public double getGrand() {
		return grand;
	}
	public void setGrand(double grand) {
		this.grand = grand;
	}
	public double getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public PaymentEnum getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentEnum paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public OrderDto(long orderId, List<OrderDetails> orderDetails, String customerMob, long createdBy, long updatedBy,
			LocalDateTime createdDate, LocalDateTime updatedDate, double discount, double subtotal, double cgst,
			double sgst, double service, double grand, double totaltax, PaymentEnum paymentType, boolean status) {
		super();
		this.orderId = orderId;
		this.orderDetails = orderDetails;
		this.customerMob = customerMob;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.discount = discount;
		this.subtotal = subtotal;
		this.cgst = cgst;
		this.sgst = sgst;
		this.service = service;
		this.grand = grand;
		this.totaltax = totaltax;
		this.paymentType = paymentType;
		this.status = status;
	}
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
}
