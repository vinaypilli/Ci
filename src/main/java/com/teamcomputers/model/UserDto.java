package com.teamcomputers.model;

import java.util.Date;

public class UserDto {
	private int userId;
	private String username;
    private String password;
    private String firstName;
	private String lastName;
	private String email;
	private long contact;
	private String address;
	private String state;
	private String city;
	private int postcode;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private boolean status=true;
	private int roleId;
	private long departmentId;
	private long categoryId;
	private long subCategoryId;
	private long serviceTitleId;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserDto(int userId, String username, String password, String firstName, String lastName, String email,
			long contact, String address, String state, String city, int postcode, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, boolean status, int roleId, long departmentId, long categoryId,
			long subCategoryId, long serviceTitleId) {
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
		this.roleId = roleId;
		this.departmentId = departmentId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.serviceTitleId = serviceTitleId;
	}






	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public long getServiceTitleId() {
		return serviceTitleId;
	}
	public void setServiceTitleId(long serviceTitleId) {
		this.serviceTitleId = serviceTitleId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	
	
   
}
