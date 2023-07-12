package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ImageType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imgId;
	private String sha256;
	private String mime_type;
	private String id;
	@CreationTimestamp
	private LocalDateTime createdDate;

	public ImageType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageType(Long imgId, String sha256, String mime_type, String id, LocalDateTime createdDate) {
		super();
		this.imgId = imgId;
		this.sha256 = sha256;
		this.mime_type = mime_type;
		this.id = id;
		this.createdDate = createdDate;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public String getSha256() {
		return sha256;
	}

	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
