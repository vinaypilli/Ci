package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ticketCreation")
public class TicketCreation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;

	@Column(nullable = false, updatable = false)
	private String ticketNo;

	@Column(nullable = false)
	private String emailId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private Category category;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id", referencedColumnName = "subCategoryId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category" })
	private SubCategory subCategory;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Service_id", referencedColumnName = "serviceId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "defaultPriority",
			"category", "subCategory" })
	private ServiceTitleEntity serviceTitle;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "departmentId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "departmentCode",
			"departmentHead", "description" })
	private DepartmentEntity department;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assignedTo", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao assignedTo;

	private long alternativeContactNo;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PriorityId", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties({ "slaTimeInHours", "createdBy", "createdDate", "updatedBy", "updatedDate", "status" })
	private SLATimelines priority;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "issue_id", referencedColumnName = "issueId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "calculationSLA" })
	private Issue issue;

	@Column(nullable = false)
	private String shortNotes;

//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "commentsId", referencedColumnName = "commentsId")
//	//@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "status", "calculationSLA" })
//	private AdditionalComments comment;

	private String comment;

	@OneToMany(mappedBy = "ticketCreation")
	private List<AdditionalComments> additionalComments;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketCreation", fetch = FetchType.LAZY)
//    private List<AdditionalComments> additionalComments;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" ,"token","tokenCreationDate"})
	private UserDao createdBy;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UpdatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle","token","tokenCreationDate" })
	private UserDao updatedBy;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resolveBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle","token","tokenCreationDate" })
	private UserDao resolveBy;

//	private long resolveBy;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createForUser", referencedColumnName = "userId",updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle","token","tokenCreationDate" })
	private UserDao createForUser;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer", referencedColumnName = "customerId",  updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status","flag"})
	private Customer customer;
	
	
	
	

//	@CreationTimestamp
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date resolveBy;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	private boolean status;

	@Lob
	@JsonIgnore
	private byte[] attach_file;

	private String resolveTimeStamp;
	private String sla;

	@Enumerated(EnumType.ORDINAL)
	private TicketStatusEnum ticketStatus;

	public TicketStatusEnum getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatusEnum ticketStatus) {
		this.ticketStatus = ticketStatus;
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

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getAlternativeContactNo() {
		return alternativeContactNo;
	}

	@JsonManagedReference
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

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public UserDao getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDao updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public byte[] getAttach_file() {
		return attach_file;
	}

	public void setAttach_file(byte[] attach_file) {
		this.attach_file = attach_file;
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

	public TicketCreation() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public TicketCreation(Long ticketId, String ticketNo, String emailId, Category category, SubCategory subCategory,
			ServiceTitleEntity serviceTitle, DepartmentEntity department, UserDao assignedTo, long alternativeContactNo,
			SLATimelines priority, Issue issue, String shortNotes, String comment,
			List<AdditionalComments> additionalComments, UserDao createdBy, LocalDateTime createdDate,
			UserDao updatedBy, UserDao resolveBy, UserDao createForUser, LocalDateTime updatedDate, boolean status,
			byte[] attach_file, String resolveTimeStamp, String sla, TicketStatusEnum ticketStatus) {
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
		this.comment = comment;
		this.additionalComments = additionalComments;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.resolveBy = resolveBy;
		this.createForUser = createForUser;
		this.updatedDate = updatedDate;
		this.status = status;
		this.attach_file = attach_file;
		this.resolveTimeStamp = resolveTimeStamp;
		this.sla = sla;
		this.ticketStatus = ticketStatus;
	}

}
