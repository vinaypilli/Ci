package com.teamcomputers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class AdditionalComments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentsId;

	private String additionalMessage;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao userDao;

	@ManyToOne
	@JoinColumn(name = "ticketId")
	@JsonIgnoreProperties({ "ticketNo", "emailId", "category", "subCategory", "serviceTitle", "department",
			"assignedTo", "alternativeContactNo", "priority", "issue", "shortNotes", "comment", "additionalComments",
			"createdBy", "createdDate", "updatedBy", "resolveBy", "createForUser", "updatedDate", "status",
			"attach_file", "fileUrl", "filePath", "filename", "resolveTimeStamp", "sLA", "ticketStatus" })
	private TicketCreation ticketCreation;

	@Lob
	@JsonIgnore
	private byte[] attach_file;

//	@JsonIgnore
	private String fileUrl;
	private String filePath;
	private String filename;

	@JsonBackReference
	public Long getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String list) {
		this.additionalMessage = list;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public TicketCreation getTicketCreation() {
		return ticketCreation;
	}

	public void setTicketCreation(TicketCreation ticketCreation) {
		this.ticketCreation = ticketCreation;
	}

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

	public AdditionalComments(Long commentsId, String additionalMessage, LocalDateTime createdDate, UserDao userDao,
			TicketCreation ticketCreation, byte[] attach_file, String fileUrl, String filePath, String filename) {
		super();
		this.commentsId = commentsId;
		this.additionalMessage = additionalMessage;
		this.createdDate = createdDate;
		this.userDao = userDao;
		this.ticketCreation = ticketCreation;
		this.attach_file = attach_file;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
	}

	public AdditionalComments() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
