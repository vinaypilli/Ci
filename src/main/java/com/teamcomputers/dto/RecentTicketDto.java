package com.teamcomputers.dto;

public class RecentTicketDto {

	private Long ticketId;
	private String ticketNo;
	private String shortNotes;
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getShortNotes() {
		return shortNotes;
	}
	public void setShortNotes(String shortNotes) {
		this.shortNotes = shortNotes;
	}
	public RecentTicketDto(Long ticketId, String ticketNo, String shortNotes) {
		super();
		this.ticketId = ticketId;
		this.ticketNo = ticketNo;
		this.shortNotes = shortNotes;
	}
	public RecentTicketDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
