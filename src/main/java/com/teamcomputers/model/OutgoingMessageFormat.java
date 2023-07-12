package com.teamcomputers.model;

public class OutgoingMessageFormat {
	
	private String recipient_whatsapp;
	
	private String message_type;
	
	private String content;
	
	private long formId;
	
	private String templateName;
	
	private String[] attributes={null};
	
//	private String fullName;
	
	

	public String getRecipient_whatsapp() {
		return recipient_whatsapp;
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public OutgoingMessageFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}

	public OutgoingMessageFormat(String recipient_whatsapp, String message_type, String content, Long formId,
			String templateName, String[] attributes) {
		super();
		this.recipient_whatsapp = recipient_whatsapp;
		this.message_type = message_type;
		this.content = content;
		this.formId = formId;
		this.templateName = templateName;
		this.attributes = attributes;
	}

	public OutgoingMessageFormat(String recipient_whatsapp, String message_type, String content, Long formId) {
		super();
		this.recipient_whatsapp = recipient_whatsapp;
		this.message_type = message_type;
		this.content = content;
		this.formId = formId;
	}

	

	
	

}
