package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;

//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<OrderDetails> orderDetails;
	
	
	@JsonProperty("orderDetails")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderd")
	private List<OrderDetails> orderDetails;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "email", "contact", "address",
			"state", "city", "postcode", "status" })
	private Customer customer;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" ,"token","tokenCreationDate"})
	private UserDao createdBy;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" ,"token","tokenCreationDate"})
	private UserDao updatedBy;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	private double subtotal;
	private double cgst;
	private double sgst;
	private double service;
	private double discount;
	private double totaltax;
	private double grand;


	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private PaymentEnum paymentType;

	private boolean status;

//	@OneToMany(mappedBy = "orders")
//	@JsonIgnoreProperties({ "orderProduct" })
//	private List<Order_Product> orderProduct;

	
	
	
	public long getOrderId() {
		return orderId;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public UserDao getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDao updatedBy) {
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

//	public double getSubtotal() {
//		return subtotal;
//	}
//
//	public void setSubtotal(double subtotal) {
//		this.subtotal = subtotal;
//	}

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

//	public double getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(double totalAmount) {
//		this.totalAmount = totalAmount;
//	}

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

	public Order(long orderId, List<OrderDetails> orderDetails, Customer customer, UserDao createdBy, UserDao updatedBy,
			LocalDateTime createdDate, LocalDateTime updatedDate, double cgst, double sgst, double service,
			double discount, double totaltax, double grand, PaymentEnum paymentType, boolean status) {
		super();
		this.orderId = orderId;
		this.orderDetails = orderDetails;
		this.customer = customer;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.cgst = cgst;
		this.sgst = sgst;
		this.service = service;
		this.discount = discount;
		this.totaltax = totaltax;
		this.grand = grand;
		this.paymentType = paymentType;
		this.status = status;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
