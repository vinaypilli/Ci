package com.teamcomputers.dto;

import java.time.LocalDateTime;

import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdditionalCommentsDto {

	private Long commentsId;
	private String additionalMessage;
	private LocalDateTime createdDate;
	private long ticketId;
	private int userId;
	@Lob
	@JsonIgnore
	private byte[] attach_file;

//	@JsonIgnore
	private String fileUrl;
	private String filePath;
	private String filename;

	
	
	public byte[] getAttach_file() {
		return attach_file;
	}

	public void setAttach_file(byte[] attach_file) {
		this.attach_file = attach_file;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	

	public AdditionalCommentsDto(Long commentsId, String additionalMessage, LocalDateTime createdDate, long ticketId,
			int userId, byte[] attach_file, String fileUrl, String filePath, String filename) {
		super();
		this.commentsId = commentsId;
		this.additionalMessage = additionalMessage;
		this.createdDate = createdDate;
		this.ticketId = ticketId;
		this.userId = userId;
		this.attach_file = attach_file;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
	}

	public AdditionalCommentsDto(Long commentsId, String additionalMessage, LocalDateTime createdDate, long ticketId,
			int userId) {
		super();
		this.commentsId = commentsId;
		this.additionalMessage = additionalMessage;
		this.createdDate = createdDate;
		this.ticketId = ticketId;
		this.userId = userId;
	}

	public AdditionalCommentsDto() {
		
	}

}
