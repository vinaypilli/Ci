package com.teamcomputers.dto;

import java.util.List;

public class OutgoingTemplateMessageDto {

	private String recipient_whatsapp;
	private String message_type;
	private String recipient_type;
	private List<OutgoingTypeTemplateDTO> type_template;

	public OutgoingTemplateMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTemplateMessageDto(String recipient_whatsapp, String message_type, String recipient_type,
			List<OutgoingTypeTemplateDTO> type_template) {
		super();
		this.recipient_whatsapp = recipient_whatsapp;
		this.message_type = message_type;
		this.recipient_type = recipient_type;
		this.type_template = type_template;
	}

	public String getRecipient_whatsapp() {
		return recipient_whatsapp;
	}

	public void setRecipient_whatsapp(String recipient_whatsapp) {
		this.recipient_whatsapp = recipient_whatsapp;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public List<OutgoingTypeTemplateDTO> getType_template() {
		return type_template;
	}

	public void setType_template(List<OutgoingTypeTemplateDTO> type_template) {
		this.type_template = type_template;
	}

}
