package com.teamcomputers.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
//	@Column(nullable = false)
	private String firstName;
//	@Column(nullable = false)
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column(nullable = false,unique = true)
	private String contact;
//	@Column(nullable = false)
	private String address;
//	@Column(nullable = false)
	private String state;
//	@Column(nullable = false)
	private String city;
//	@Column(nullable = false)
	private long postcode;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle","token","tokenCreationDate" })
	private UserDao createdBy;
	@Column(nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle","token","tokenCreationDate" })
	private UserDao updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	//@Value("${some.key:false}")
	
	private boolean flag;
	private boolean status;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int customerId, String firstName, String lastName, String email, String contact, String address,
			String state, String city, long postcode, UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy,
			LocalDateTime updatedDate, boolean status) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.state = state;
		this.city = city;
		this.postcode = postcode;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
	}
	
	
	public Customer(int customerId, String firstName, String lastName, String email, String contact, String address,
			String state, String city, long postcode, UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy,
			LocalDateTime updatedDate, boolean flag, boolean status) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.state = state;
		this.city = city;
		this.postcode = postcode;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.flag = flag;
		this.status = status;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPostcode() {
		return postcode;
	}
	public void setPostcode(long postcode) {
		this.postcode = postcode;
	}
	public UserDao getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public UserDao getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserDao updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
}
