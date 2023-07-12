package com.teamcomputers.dto;

import java.util.List;

public class OutgoingTemplateDto {
	private List<OutgoingTemplateMessageDto> message;

	public List<OutgoingTemplateMessageDto> getMessage() {
		return message;
	}

	public void setMessage(List<OutgoingTemplateMessageDto> message) {
		this.message = message;
	}

	public OutgoingTemplateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTemplateDto(List<OutgoingTemplateMessageDto> message) {
		super();
		this.message = message;
	}
	
	
}
