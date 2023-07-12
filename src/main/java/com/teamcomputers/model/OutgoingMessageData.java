package com.teamcomputers.model;

import java.util.List;

public class OutgoingMessageData {

	private long mid;

	private String recipient_whatsapp;

	private String recipient_type;

	private String message_type;

	private String source;

	private String x_apiheader;

	private List<OutgoingType_text> outgoingType_text;

	public String getRecipient_whatsapp() {
		return recipient_whatsapp;
	}

	public void setRecipient_whatsapp(String recipient_whatsapp) {
		this.recipient_whatsapp = recipient_whatsapp;
	}

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getX_apiheader() {
		return x_apiheader;
	}

	public void setX_apiheader(String x_apiheader) {
		this.x_apiheader = x_apiheader;
	}

	public List<OutgoingType_text> getType_text() {
		return outgoingType_text;
	}

	public void setType_text(List<OutgoingType_text> outgoingType_text) {
		this.outgoingType_text = outgoingType_text;
	}

	public long getmId() {
		return mid;
	}

	public void setmId(long mid) {
		this.mid = mid;
	}

	public OutgoingMessageData(long mid, String recipient_whatsapp, String recipient_type, String message_type, String source,
			String x_apiheader, List<OutgoingType_text> outgoingType_text) {
		super();
		this.mid = mid;
		this.recipient_whatsapp = recipient_whatsapp;
		this.recipient_type = recipient_type;
		this.message_type = message_type;
		this.source = source;
		this.x_apiheader = x_apiheader;
		this.outgoingType_text = outgoingType_text;
	}

	public OutgoingMessageData() {
		super();
		// TODO Auto-generated constructor stub
	}

}
