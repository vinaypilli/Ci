package com.teamcomputers.dto;

import java.util.List;

import com.teamcomputers.model.TicketCreation;

public class TicketCommentDto {
	
	private TicketCreation tickets;
	private List<AdditionalCommentsDto> additionalcomments;
	public TicketCreation getTickets() {
		return tickets;
	}
	public void setTickets(TicketCreation tickets) {
		this.tickets = tickets;
	}
	public List<AdditionalCommentsDto> getAdditionalcomments() {
		return additionalcomments;
	}
	public void setAdditionalcomments(List<AdditionalCommentsDto> additionalcomments) {
		this.additionalcomments = additionalcomments;
	}
	public TicketCommentDto(TicketCreation tickets, List<AdditionalCommentsDto> additionalcomments) {
		super();
		this.tickets = tickets;
		this.additionalcomments = additionalcomments;
	}
	
   // getters and setters
}
