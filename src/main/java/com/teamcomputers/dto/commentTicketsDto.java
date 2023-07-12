package com.teamcomputers.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamcomputers.model.AdditionalComments;
import com.teamcomputers.model.Category;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.Issue;
import com.teamcomputers.model.SLATimelines;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.model.TicketStatusEnum;
import com.teamcomputers.model.UserDao;

public class commentTicketsDto {

	private Long ticketId;
	private String ticketNo;
	private String emailId;

	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private Category category;
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category" })
	private SubCategory subCategory;

	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "defaultPriority",
			"category", "subCategory" })
	private ServiceTitleEntity serviceTitle;

	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "departmentCode",
			"departmentHead", "description" })
	private DepartmentEntity department;

	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao assignedTo;

	private long alternativeContactNo;

	@JsonIgnoreProperties({ "slaTimeInHours", "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SLATimelines priority;

	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "calculationSLA" })
	private Issue issue;

	private String shortNotes;

	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;

	private LocalDateTime createdDate;

	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao updatedBy;

	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao resolveBy;

	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createForUser;

	private LocalDateTime updatedDate;

	private boolean status;

	private String resolveTimeStamp;
	private String sla;

	private TicketStatusEnum ticketStatus;

	private List<AdditionalCommentsDto> additionalComments;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public ServiceTitleEntity getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(ServiceTitleEntity serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public UserDao getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserDao assignedTo) {
		this.assignedTo = assignedTo;
	}

	public long getAlternativeContactNo() {
		return alternativeContactNo;
	}

	public void setAlternativeContactNo(long alternativeContactNo) {
		this.alternativeContactNo = alternativeContactNo;
	}

	public SLATimelines getPriority() {
		return priority;
	}

	public void setPriority(SLATimelines priority) {
		this.priority = priority;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String getShortNotes() {
		return shortNotes;
	}

	public void setShortNotes(String shortNotes) {
		this.shortNotes = shortNotes;
	}
	
	

	public List<AdditionalCommentsDto> getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(List<AdditionalCommentsDto> additionalComments) {
		this.additionalComments = additionalComments;
	}

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserDao getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDao updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserDao getResolveBy() {
		return resolveBy;
	}

	public void setResolveBy(UserDao resolveBy) {
		this.resolveBy = resolveBy;
	}

	public UserDao getCreateForUser() {
		return createForUser;
	}

	public void setCreateForUser(UserDao createForUser) {
		this.createForUser = createForUser;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getResolveTimeStamp() {
		return resolveTimeStamp;
	}

	public void setResolveTimeStamp(String resolveTimeStamp) {
		this.resolveTimeStamp = resolveTimeStamp;
	}

	
	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public TicketStatusEnum getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatusEnum ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public commentTicketsDto(Long ticketId, String ticketNo, String emailId, Category category, SubCategory subCategory,
			ServiceTitleEntity serviceTitle, DepartmentEntity department, UserDao assignedTo, long alternativeContactNo,
			SLATimelines priority, Issue issue, String shortNotes, UserDao createdBy, LocalDateTime createdDate,
			UserDao updatedBy, UserDao resolveBy, UserDao createForUser, LocalDateTime updatedDate, boolean status,
			String resolveTimeStamp, String sla, TicketStatusEnum ticketStatus,
			List<AdditionalCommentsDto> additionalComments) {
		super();
		this.ticketId = ticketId;
		this.ticketNo = ticketNo;
		this.emailId = emailId;
		this.category = category;
		this.subCategory = subCategory;
		this.serviceTitle = serviceTitle;
		this.department = department;
		this.assignedTo = assignedTo;
		this.alternativeContactNo = alternativeContactNo;
		this.priority = priority;
		this.issue = issue;
		this.shortNotes = shortNotes;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.resolveBy = resolveBy;
		this.createForUser = createForUser;
		this.updatedDate = updatedDate;
		this.status = status;
		this.resolveTimeStamp = resolveTimeStamp;
		this.sla = sla;
		this.ticketStatus = ticketStatus;
		this.additionalComments = additionalComments;
	}

	public commentTicketsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
