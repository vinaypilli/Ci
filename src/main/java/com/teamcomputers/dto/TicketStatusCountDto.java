package com.teamcomputers.dto;

import com.teamcomputers.model.TicketStatusEnum;

public class TicketStatusCountDto {

	private TicketStatusEnum ticketStatus;
	private long totalCounts;

	public TicketStatusCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketStatusCountDto(TicketStatusEnum ticketStatus, long totalCounts) {
		super();
		this.ticketStatus = ticketStatus;
		this.totalCounts = totalCounts;
	}

	public TicketStatusEnum getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatusEnum ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(long totalCounts) {
		this.totalCounts = totalCounts;
	}

}
