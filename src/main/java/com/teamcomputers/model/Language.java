package com.teamcomputers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OutgoingTemplateLaguage")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String locale;
	private String policy;

	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Language(long id, String locale, String policy) {
		super();
		this.id = id;
		this.locale = locale;
		this.policy = policy;
	}

	

	public Language(String locale, String policy) {
		super();
		this.locale = locale;
		this.policy = policy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
