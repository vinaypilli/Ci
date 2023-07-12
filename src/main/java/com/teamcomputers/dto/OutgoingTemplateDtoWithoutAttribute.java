package com.teamcomputers.dto;

import java.util.List;

public class OutgoingTemplateDtoWithoutAttribute {

	private List<OutgoingTemplateMessageDtoWithoutTemplate> message;

	public OutgoingTemplateDtoWithoutAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTemplateDtoWithoutAttribute(List<OutgoingTemplateMessageDtoWithoutTemplate> message) {
		super();
		this.message = message;
	}

	public List<OutgoingTemplateMessageDtoWithoutTemplate> getMessage() {
		return message;
	}

	public void setMessage(List<OutgoingTemplateMessageDtoWithoutTemplate> message) {
		this.message = message;
	}
	
	
}
