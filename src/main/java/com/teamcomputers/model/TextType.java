package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TextTypeIncoming")
public class TextType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long textId;
	private String text;
	@CreationTimestamp
	private LocalDateTime createdDate;

	public TextType(Long textId, String text, LocalDateTime createdDate) {
		super();
		this.textId = textId;
		this.text = text;
		this.createdDate = createdDate;
	}

	public TextType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTextId() {
		return textId;
	}

	public void setTextId(Long textId) {
		this.textId = textId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
