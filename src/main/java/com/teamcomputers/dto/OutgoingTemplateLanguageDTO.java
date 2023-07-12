package com.teamcomputers.dto;

public class OutgoingTemplateLanguageDTO {

	private String locale;
	private String policy;

	public OutgoingTemplateLanguageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTemplateLanguageDTO(String locale, String policy) {
		super();
		this.locale = locale;
		this.policy = policy;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

}
