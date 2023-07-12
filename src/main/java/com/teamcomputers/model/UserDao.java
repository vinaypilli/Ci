package com.teamcomputers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class UserDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(nullable = false,unique = true)
	private String username;
	@Column
	@JsonIgnore
	private String password;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false,unique = true)
	private long contact;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String city;
	private int postcode;
	@Column(nullable = false,updatable = false)
	private String createdBy;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false,updatable = false)
	private Date createdDate;
	private String updatedBy;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	private boolean status;
	private String token;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	@JsonIgnoreProperties({"departmentCode", "departmentHead", "description", "createdBy", "createdDate", "updatedBy", "updatedDate", "status"})
	private DepartmentEntity department;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleEntity_id")
	@JsonIgnoreProperties({"roleDescription", "createdBy", "createdDate", "updatedBy", "updatedDate", "status"})
	private RoleEntity role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private Category category;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id", referencedColumnName = "subCategoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category" })
	private SubCategory subCategory;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Service_id", referencedColumnName = "serviceId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "defaultPriority",
			"category", "subCategory" })
	private ServiceTitleEntity serviceTitle;
	
	
	

	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}

	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}

	public UserDao(int userId, String username, String password, String firstName, String lastName, String email,
			long contact, String address, String state, String city, int postcode, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, boolean status, String token, LocalDateTime tokenCreationDate,
			DepartmentEntity department, RoleEntity role, Category category, SubCategory subCategory,
			ServiceTitleEntity serviceTitle) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
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
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.department = department;
		this.role = role;
		this.category = category;
		this.subCategory = subCategory;
		this.serviceTitle = serviceTitle;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public ServiceTitleEntity getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(ServiceTitleEntity serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
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

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDao(int userId, String username, String password, String firstName, String lastName, String email,
			long contact, String address, String state, String city, int postcode, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, boolean status, DepartmentEntity department, RoleEntity role,
			Category category, SubCategory subCategory, ServiceTitleEntity serviceTitle) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
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
		this.department = department;
		this.role = role;
		this.category = category;
		this.subCategory = subCategory;
		this.serviceTitle = serviceTitle;
	}

}
