package com.teamcomputers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamcomputers.model.RoleEntity;

public class UserFilterDto {

	private int userId;
	
	private String firstName;
	private String lastName;
	private String username;
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status","roleDescription"})
	private RoleEntity role;
	
	public UserFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	
	
	
}
