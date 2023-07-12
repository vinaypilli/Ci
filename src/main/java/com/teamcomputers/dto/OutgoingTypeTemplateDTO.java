package com.teamcomputers.dto;

public class OutgoingTypeTemplateDTO {

	private String name;
	private String[] attributes;
	private OutgoingTemplateLanguageDTO language;

	public OutgoingTypeTemplateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTypeTemplateDTO(String name, String[] attributes, OutgoingTemplateLanguageDTO language) {
		super();
		this.name = name;
		this.attributes = attributes;
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}

	public OutgoingTemplateLanguageDTO getLanguage() {
		return language;
	}

	public void setLanguage(OutgoingTemplateLanguageDTO language) {
		this.language = language;
	}

}
