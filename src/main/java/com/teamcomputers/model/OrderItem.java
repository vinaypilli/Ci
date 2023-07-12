//package com.teamcomputers.model;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "order_item")
//public class OrderItem {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long orderItemId;
//	@Column(nullable = false)
//	private int quantity;
//	@Column(nullable = false)
//	private String specialInstruction;
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
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "order_details")
//	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate" })
//	private Order order_details;
//
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "product_details", referencedColumnName = "productId")
//	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "productDescription" })
//	private Product productdetails;
//
//	public OrderItem() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public OrderItem(long orderItemId, int quantity, String specialInstruction, UserDao createdBy,
//			LocalDateTime createdDate, UserDao updatedBy, LocalDateTime updatedDate, Order order_details,
//			Product productdetails) {
//		super();
//		this.orderItemId = orderItemId;
//		this.quantity = quantity;
//		this.specialInstruction = specialInstruction;
//		this.createdBy = createdBy;
//		this.createdDate = createdDate;
//		this.updatedBy = updatedBy;
//		this.updatedDate = updatedDate;
//		this.order_details = order_details;
//		this.productdetails = productdetails;
//	}
//
//	public long getOrderItemId() {
//		return orderItemId;
//	}
//
//	public void setOrderItemId(long orderItemId) {
//		this.orderItemId = orderItemId;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public String getSpecialInstruction() {
//		return specialInstruction;
//	}
//
//	public void setSpecialInstruction(String specialInstruction) {
//		this.specialInstruction = specialInstruction;
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
//	public Order getOrder_details() {
//		return order_details;
//	}
//
//	public void setOrder_details(Order order_details) {
//		this.order_details = order_details;
//	}
//
//	public Product getProductdetails() {
//		return productdetails;
//	}
//
//	public void setProductdetails(Product productdetails) {
//		this.productdetails = productdetails;
//	}
//
//}
