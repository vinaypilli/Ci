package com.teamcomputers.dto;

public class AssignedToCountTicketsDto {

	private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private Long totalTicketAssigned;
    
	public AssignedToCountTicketsDto(int userId, String username, String firstName, String lastName, Long totalTicketAssigned) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalTicketAssigned = totalTicketAssigned;
	}

	public AssignedToCountTicketsDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public Long getTotalTicketAssigned() {
		return totalTicketAssigned;
	}

	public void setTotalTicketAssigned(Long totalTicketAssigned) {
		this.totalTicketAssigned = totalTicketAssigned;
	}

    
}
