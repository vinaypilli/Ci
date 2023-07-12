package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ordId;
	private String productName;
	private double price;
	private int quantity;
	private String unitofmeasure;
	private double total;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "order_id")
//	@JsonIgnoreProperties("orderDetails")
//	private Order order;
	
	
	
	public String getProductName() {
		return productName;
	}
	public long getOrdId() {
		return ordId;
	}
	public void setOrdId(long ordId) {
		this.ordId = ordId;
	}
	public String getUnitofmeasure() {
		return unitofmeasure;
	}
	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
//	public double getRatemrp() {
//		return ratemrp;
//	}
//	public void setRatemrp(double ratemrp) {
//		this.ratemrp = ratemrp;
//	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
