package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "outgoing_typetext")
public class OutgoingType_text {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String preview_url;

	private String content;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(String preview_url) {
		this.preview_url = preview_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public OutgoingType_text() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutgoingType_text(Long id, String preview_url, String content) {
		super();
		this.id = id;
		this.preview_url = preview_url;
		this.content = content;
	}

}
