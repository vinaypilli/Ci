package com.teamcomputers.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class TempleteAdd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String templateName;
	@Enumerated(EnumType.ORDINAL)
	private TemplateCategoryEnum category;
	private String language;
	@Enumerated(EnumType.ORDINAL)
	private TemplateHeaderEnum Header;
	private String text;
	@Lob
	@JsonIgnore
	private byte[] files;	
	private String body;
	private String footer;
	@Enumerated(EnumType.ORDINAL)
	private TemplateButtonsEnum buttons;

	@ElementCollection
	@CollectionTable(name = "user_buttons")
	@MapKeyColumn(name = "button_key")
	@Column(name = "button_value")
	private Map<String, String> buttonData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public byte[] getFiles() {
		return files;
	}

	public void setFiles(byte[] files) {
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

	public TempleteAdd(long id, String templateName, TemplateCategoryEnum category, String language,
			TemplateHeaderEnum header, String text, byte[] files, String body, String footer,
			TemplateButtonsEnum buttons, Map<String, String> buttonData) {
		super();
		this.id = id;
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

	public TempleteAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
