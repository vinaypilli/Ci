package com.teamcomputers.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OutgoingTypeTemplate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	private String[] attributes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "laguageID")
	private Language language;

	public OutgoingTypeTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public OutgoingTypeTemplate(long id, String name, String[] attributes, Language language) {
		super();
		this.id = id;
		this.name = name;
		this.attributes = attributes;
		this.language = language;
	}

}
