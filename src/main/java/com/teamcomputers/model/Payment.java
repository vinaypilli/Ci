//package com.teamcomputers.model;
//
//import java.time.LocalDateTime;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//public class Payment {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long paymentId;
//	@Column(nullable = false)
//	private int paymentAmount;
//	@Column(nullable = false)
//	private String paymentMethod;
//	@Column(nullable = false, updatable = false)
//	@CreationTimestamp
//	private LocalDateTime timestamp;
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
//	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
//			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
//			"serviceTitle" })
//	private UserDao createdBy;
//	@Column(nullable = false, updatable = false)
//	@CreationTimestamp
//	private LocalDateTime createdDate;
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
//	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
//			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
//			"serviceTitle" })
//	private UserDao updatedBy;
//	@UpdateTimestamp
//	private LocalDateTime updatedDate;
//
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "order_id", referencedColumnName = "orderId")
//	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "tableNumber",
//			"orderDate" })
//	private Order orderId;
//
//	public Payment() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Payment(long paymentId, int paymentAmount, String paymentMethod, LocalDateTime timestamp, UserDao createdBy,
//			LocalDateTime createdDate, UserDao updatedBy, LocalDateTime updatedDate, Order orderId) {
//		super();
//		this.paymentId = paymentId;
//		this.paymentAmount = paymentAmount;
//		this.paymentMethod = paymentMethod;
//		this.timestamp = timestamp;
//		this.createdBy = createdBy;
//		this.createdDate = createdDate;
//		this.updatedBy = updatedBy;
//		this.updatedDate = updatedDate;
//		this.orderId = orderId;
//	}
//
//	public long getPaymentId() {
//		return paymentId;
//	}
//
//	public void setPaymentId(long paymentId) {
//		this.paymentId = paymentId;
//	}
//
//	public int getPaymentAmount() {
//		return paymentAmount;
//	}
//
//	public void setPaymentAmount(int paymentAmount) {
//		this.paymentAmount = paymentAmount;
//	}
//
//	public String getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(String paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
//
//	public LocalDateTime getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(LocalDateTime timestamp) {
//		this.timestamp = timestamp;
//	}
//
//	public UserDao getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(UserDao createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public UserDao getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(UserDao updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public LocalDateTime getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(LocalDateTime updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	public Order getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(Order orderId) {
//		this.orderId = orderId;
//	}
//
//}
