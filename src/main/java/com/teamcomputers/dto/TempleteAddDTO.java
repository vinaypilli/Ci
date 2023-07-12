package com.teamcomputers.dto;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamcomputers.model.TemplateButtonsEnum;
import com.teamcomputers.model.TemplateCategoryEnum;
import com.teamcomputers.model.TemplateHeaderEnum;

public class TempleteAddDTO {
	private String templateName;
	private TemplateCategoryEnum category;
	private String language;
	private TemplateHeaderEnum Header;
	private String text;
	private String files;
	private String body;
	private String footer;
	private TemplateButtonsEnum buttons;
	private Map<String, String> buttonData;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public TemplateCategoryEnum getCategory() {
		return category;
	}
	public void setCategory(TemplateCategoryEnum category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public TemplateHeaderEnum getHeader() {
		return Header;
	}
	public void setHeader(TemplateHeaderEnum header) {
		Header = header;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public TemplateButtonsEnum getButtons() {
		return buttons;
	}
	public void setButtons(TemplateButtonsEnum buttons) {
		this.buttons = buttons;
	}
	public Map<String, String> getButtonData() {
		return buttonData;
	}
	public void setButtonData(Map<String, String> buttonData) {
		this.buttonData = buttonData;
	}
	public TempleteAddDTO(String templateName, TemplateCategoryEnum category, String language,
			TemplateHeaderEnum header, String text, String files, String body, String footer,
			TemplateButtonsEnum buttons, Map<String, String> buttonData) {
		super();
		this.templateName = templateName;
		this.category = category;
		this.language = language;
		Header = header;
		this.text = text;
		this.files = files;
		this.body = body;
		this.footer = footer;
		this.buttons = buttons;
		this.buttonData = buttonData;
	}
	public TempleteAddDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
