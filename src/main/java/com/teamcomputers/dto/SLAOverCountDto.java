package com.teamcomputers.dto;

public class SLAOverCountDto {

	private int userId;
    private String username;
    private String firstName;
    private String lastName;
	private long totalOverCount;

	public SLAOverCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SLAOverCountDto(int userId, String username, String firstName, String lastName, long totalOverCount) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalOverCount = totalOverCount;
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

	public long getTotalOverCount() {
		return totalOverCount;
	}

	public void setTotalOverCount(long totalOverCount) {
		this.totalOverCount = totalOverCount;
	}



}