package com.teamcomputers.dto;

public class OutgoingTypeTemplateWithoutAttributeDTO {

	private String name;
	private OutgoingTemplateLanguageDTO language;

	public OutgoingTypeTemplateWithoutAttributeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingTypeTemplateWithoutAttributeDTO(String name, OutgoingTemplateLanguageDTO language) {
		super();
		this.name = name;
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OutgoingTemplateLanguageDTO getLanguage() {
		return language;
	}

	public void setLanguage(OutgoingTemplateLanguageDTO language) {
		this.language = language;
	}

}
