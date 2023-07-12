package com.teamcomputers.service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.controller.OutgoingMessageResponseController;
import com.teamcomputers.dto.AdditionalCommentsDto;
import com.teamcomputers.dto.AssignedToCountTicketsDto;
import com.teamcomputers.dto.DepartmentWiseCountDto;
import com.teamcomputers.dto.RecentTicketDto;
import com.teamcomputers.dto.SLAOverCountDto;
import com.teamcomputers.dto.StatisticsDTO;
import com.teamcomputers.dto.TicketCommentDto;
import com.teamcomputers.dto.TicketDto;
import com.teamcomputers.dto.commentTicketsDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Category;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.Issue;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.model.SLATimelines;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.model.TicketCreation;
import com.teamcomputers.model.TicketStatusEnum;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.AdditionalCommentsRepository;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.DepartmentRepository;
import com.teamcomputers.repository.IssueRepository;
import com.teamcomputers.repository.SLATimelinesRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.TicketCreationRepository;
//import com.teamcomputers.repository.TicketImpl;
import com.teamcomputers.repository.UserRepository;

@Component
@Service
public class TicketCreationService<T> {
	@Autowired
	private TicketCreationRepository ticketCreationRepository;

	@Autowired
	private OutgoingMessageResponseController<T> outgoingMessageResponseController;

	@Autowired
	private AdditionalCommentsRepository additionalCommentsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ServiceTitleRepository serviceTitleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private SLATimelinesRepository slaTimelinesRepository;

	@Autowired
	private AdditionalCommentsService additionalCommentsService;

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CustomerRepository customerRepository;

	public String UPLOAD_DIR;// =new ClassPathResource("uploads/").getFile().getAbsolutePath();

	public TicketCreation create(String userData, String ticketNo) throws IOException {
		TicketDto tickets = mapper.readValue(userData, TicketDto.class);
		TicketDto ticketDto = new TicketDto(tickets.getTicketId(), tickets.getTicketNo(), tickets.getEmailId(),
				tickets.getCategoryId(), tickets.getSubCategoryId(), tickets.getServiceTitleId(),
				tickets.getAlternativeContactNo(), tickets.getPriority(), tickets.getIssueId(),
				tickets.getDepartmentId(), tickets.getAssignedTo(), tickets.getShortNotes(), tickets.getComment(),
				tickets.getCreatedBy(), tickets.getCreatedDate(), tickets.getUpdatedBy(), tickets.getUpdatedDate(),
				tickets.getResolveBy(), tickets.getCreateForUser(), tickets.getResolveTimeStamp(), tickets.getSla(),
				tickets.isStatus(), tickets.getTicketStatus());

		if (((tickets.getCreateForUser() == 0) && (tickets.getCustomerId() == 0))
				|| ((tickets.getCreateForUser() != 0) && (tickets.getCustomerId() != 0))) {
			throw new ResourceNotFoundException("CreatedBy Id Or Customers Id should not be null");
		}
		TicketCreation ticket = new TicketCreation();

		ticket.setTicketId(ticketDto.getTicketId());
		ticket.setTicketNo(ticketNo);
		ticket.setEmailId(ticketDto.getEmailId());
		Category category = categoryRepository.findById((long) ticketDto.getCategoryId()).orElseThrow(
				() -> new ResourceNotFoundException("Category Id :" + ticketDto.getCategoryId() + " Unavailable"));
		SubCategory subCategory = subCategoryRepository.findById((int) ticketDto.getSubCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"SubCategory Id :" + ticketDto.getSubCategoryId() + " Unavailable"));
		ServiceTitleEntity serviceTitle = serviceTitleRepository.findById((int) ticketDto.getServiceTitleId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"ServiceTitle Id :" + ticketDto.getServiceTitleId() + " Unavailable"));
		SLATimelines slaTimelines = slaTimelinesRepository.findById(ticketDto.getPriority()).orElseThrow(
				() -> new ResourceNotFoundException("Priority Id :" + ticketDto.getPriority() + " Unavailable"));
		Issue issue = issueRepository.findById(ticketDto.getIssueId()).orElseThrow(
				() -> new ResourceNotFoundException("Issue Id :" + ticketDto.getIssueId() + "Unavailable"));
		DepartmentEntity department = departmentRepository.findById((int) ticketDto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department Id :" + ticketDto.getDepartmentId() + " Unavailable"));
		UserDao user = userRepository.findById((int) ticketDto.getAssignedTo()).orElseThrow(
				() -> new ResourceNotFoundException("AssignedTot Id :" + ticketDto.getAssignedTo() + " Unavailable"));
		ticket.setCategory(category);
		ticket.setSubCategory(subCategory);
		ticket.setServiceTitle(serviceTitle);
		ticket.setPriority(slaTimelines);
		ticket.setIssue(issue);
		ticket.setDepartment(department);
		ticket.setAlternativeContactNo(ticketDto.getAlternativeContactNo());
		ticket.setAssignedTo(user);
		ticket.setShortNotes(ticketDto.getShortNotes());

		UserDao createdBy = userRepository.findById((int) ticketDto.getCreatedBy()).orElseThrow(
				() -> new ResourceNotFoundException("CreatedBy Id :" + ticketDto.getCreatedBy() + " Unavailable"));
		UserDao CreateForUser = userRepository.findById((int) ticketDto.getCreateForUser()).orElse(null);
		Customer customers = customerRepository.findById((int) tickets.getCustomerId()).orElse(null);

		UserDao CreateForUser1 = userRepository.findById((int) ticketDto.getCreateForUser()).orElse(null);
		Customer customers1 = customerRepository.findById((int) tickets.getCustomerId()).orElse(null);

		if (((customers1 == null) && (CreateForUser1 == null)) || ((customers1 == null) && (CreateForUser1 == null))) {
			throw new ResourceNotFoundException("CreatedBy Details Or Customers Details Unavailable");
		}
	
			ticket.setCustomer(customers);
		
		ticket.setCreatedBy(createdBy);

		ticket.setCreatedDate(ticketDto.getCreatedDate());
		ticket.setComment(ticketDto.getComment());
		ticket.setStatus(ticketDto.isStatus());
		ticket.setTicketStatus(TicketStatusEnum.New);
		ticket.setCreateForUser(CreateForUser);
		return ticketCreationRepository.save(ticket);

	}

	public TicketCreation update(long ticketId, String userData) throws IOException {
		TicketDto tickets = mapper.readValue(userData, TicketDto.class);
		TicketDto ticketDto = new TicketDto(tickets.getTicketId(), tickets.getTicketNo(), tickets.getEmailId(),
				tickets.getCategoryId(), tickets.getSubCategoryId(), tickets.getServiceTitleId(),
				tickets.getAlternativeContactNo(), tickets.getPriority(), tickets.getIssueId(),
				tickets.getDepartmentId(), tickets.getAssignedTo(), tickets.getShortNotes(), tickets.getComment(),
				tickets.getCreatedBy(), tickets.getCreatedDate(), tickets.getUpdatedBy(), tickets.getUpdatedDate(),
				tickets.getResolveBy(), tickets.getCreateForUser(), tickets.getResolveTimeStamp(), tickets.getSla(),
				tickets.isStatus(), tickets.getTicketStatus());

		TicketCreation ticket = ticketCreationRepository.findByTicketId(ticketId);
//		if (((ticket.getCreateForUser() == null) && (ticket.getCustomer() == null))
//				|| ((tickets.getCreateForUser() != 0) && (ticket.getCustomer().getCustomerId() != 0))) {
//			throw new ResourceNotFoundException("CreatedBy Id Or Customers Id should not be null");
//		}
		Customer customers1=null;
		UserDao CreateForUser1 =null;
		if(ticket.getCreateForUser()!=null) {
			CreateForUser1= userRepository.findById((int) ticketDto.getCreateForUser()).orElse(null);
		}else if (ticket.getCustomer()!=null){
			customers1 = customerRepository.findById((int) ticket.getCustomer().getCustomerId()).orElse(null);
		}
		
//		if (((customers1 == null) && (CreateForUser1 == null)) || ((customers1 == null) && (CreateForUser1 == null))) {
//			throw new ResourceNotFoundException("CreatedBy Details Or Customers Details Unavailable");
//		}

		ticket.setTicketId(ticketId);

		ticket.setEmailId(ticketDto.getEmailId());
		ticket.setComment(ticketDto.getComment());
		ticket.setTicketStatus(ticketDto.getTicketStatus());

		Category category = categoryRepository.findById((long) ticketDto.getCategoryId()).orElseThrow(
				() -> new ResourceNotFoundException("Category Id :" + ticketDto.getCategoryId() + " Unavailable"));
		SubCategory subCategory = subCategoryRepository.findById((int) ticketDto.getSubCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"SubCategory Id :" + ticketDto.getSubCategoryId() + " Unavailable"));
		ServiceTitleEntity serviceTitle = serviceTitleRepository.findById((int) ticketDto.getServiceTitleId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"ServiceTitle Id :" + ticketDto.getServiceTitleId() + " Unavailable"));
		SLATimelines slaTimelines = slaTimelinesRepository.findById(ticketDto.getPriority()).orElseThrow(
				() -> new ResourceNotFoundException("Priority Id :" + ticketDto.getPriority() + " Unavailable"));
		Issue issue = issueRepository.findById(ticketDto.getIssueId()).orElseThrow(
				() -> new ResourceNotFoundException("Issue Id :" + ticketDto.getIssueId() + "Unavailable"));
		DepartmentEntity department = departmentRepository.findById((int) ticketDto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department Id :" + ticketDto.getDepartmentId() + " Unavailable"));
		UserDao user = userRepository.findById((int) ticketDto.getAssignedTo()).orElseThrow(
				() -> new ResourceNotFoundException("AssignedTot Id :" + ticketDto.getAssignedTo() + " Unavailable"));
		ticket.setCategory(category);
		ticket.setSubCategory(subCategory);
		ticket.setServiceTitle(serviceTitle);
		ticket.setPriority(slaTimelines);
		ticket.setIssue(issue);
		ticket.setDepartment(department);
		ticket.setAlternativeContactNo(ticketDto.getAlternativeContactNo());
		ticket.setAssignedTo(user);
		ticket.setShortNotes(ticketDto.getShortNotes());

		if (ticketDto.getTicketStatus() == TicketStatusEnum.Resolved) {

			TicketCreation ticketdate = ticketCreationRepository.findById(ticketId).orElse(null);

			LocalDateTime created = ticketdate.getCreatedDate();
			LocalDateTime updated = LocalDateTime.now();

			if (created != null) {
				Duration duration = Duration.between(created, updated);
				long hours = duration.toHours();
				long minutes = duration.toMinutesPart();
				long seconds = duration.toSecondsPart();
				long totalMinutes = (hours * 60) + minutes;

				String timeElapsed = hours + "h " + minutes + "m " + seconds + "s";
				ticket.setResolveTimeStamp(timeElapsed);

				long slaTimeInHours = ticketdate.getPriority().getSlaTimeInHours();
				long slaTimeInMinutes = (slaTimeInHours * 60);

				if (totalMinutes <= slaTimeInMinutes) {
					ticket.setSla("Under");
				} else if (totalMinutes >= slaTimeInMinutes) {
					ticket.setSla("Over");
				}

			} else {

			}
		}

		ticket.setCreatedBy(userRepository.findById((int) (ticketDto.getCreatedBy())).orElse(null));
		ticket.setCreatedDate(ticketDto.getCreatedDate());
		UserDao updatedBy = userRepository.findById((int) ticketDto.getUpdatedBy()).orElseThrow(
				() -> new ResourceNotFoundException("UpdatedBy Id :" + ticketDto.getUpdatedBy() + " Unavailable"));
		ticket.setUpdatedBy(updatedBy);
		ticket.setUpdatedDate(ticketDto.getUpdatedDate());
		ticket.setStatus(ticketDto.isStatus());

		if (CreateForUser1 != null) {
			UserDao CreateForUser = userRepository.findById((int) ticketDto.getCreateForUser()).orElse(null);
			ticket.setCreateForUser(CreateForUser);

		} else if (customers1 != null) {
			Customer customer = customerRepository.findById((int) ticket.getCustomer().getCustomerId()).orElse(null);
			ticket.setCustomer(customer);
		}
		ticket.setResolveBy(userRepository.findById((int) (ticketDto.getAssignedTo())).orElse(null));

		TicketCreation ticketCreation = ticketCreationRepository.save(ticket);
		if (ticketCreation.getCustomer() != null) {
			OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
			outgoingMessageFormat.setRecipient_whatsapp(ticketCreation.getCustomer().getContact());
			outgoingMessageFormat.setMessage_type("text");
			if (ticket.getComment() != null) {
				outgoingMessageFormat.setContent("Your Ticket No. "+ticket.getTicketNo()+" status : " + ticket.getComment());
			} else {
				outgoingMessageFormat.setContent("Your Ticket No. "+ticket.getTicketNo()+" status updated Please check your email.");
			}
			outgoingMessageFormat.setTemplateName(null);
			outgoingMessageFormat.setFormId((long) 1);

			String[] attribute = { null };
			String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
			outgoingMessageResponseController.createWebhookSentMessage(responseData1);
		}
		return ticketCreation;
	}

//	public byte[] downloadImageFromFileSystem(long ticketId) throws IOException {
//		Optional<TicketCreation> fileDetails = ticketCreationRepository.findById(ticketId);
//		String filePath = fileDetails.get().getFilePath();
//		byte[] images = Files.readAllBytes(new File(filePath).toPath());
//		return images;
//	}

	public List<TicketCreation> getAll() {
		return ticketCreationRepository.findAllByOrderByCreatedDateDesc();
	}

	public commentTicketsDto getById(Long ticketId) {
		TicketCreation tc = ticketCreationRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

		List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

		commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
		ticket.setTicketId(tc.getTicketId());
		ticket.setTicketNo(tc.getTicketNo());
		ticket.setEmailId(tc.getEmailId());
		ticket.setCategory(tc.getCategory());

		ticket.setSubCategory(tc.getSubCategory());
		ticket.setServiceTitle(tc.getServiceTitle());
		ticket.setPriority(tc.getPriority());
		ticket.setIssue(tc.getIssue());
		ticket.setDepartment(tc.getDepartment());
		ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
		ticket.setAssignedTo(tc.getAssignedTo());
		ticket.setShortNotes(tc.getShortNotes());
		ticket.setCreatedBy(tc.getCreatedBy());
		ticket.setCreatedDate(tc.getCreatedDate());
		ticket.setUpdatedBy(tc.getUpdatedBy());
		ticket.setUpdatedDate(tc.getUpdatedDate());
		ticket.setStatus(tc.isStatus());
		ticket.setTicketStatus(tc.getTicketStatus());
		ticket.setCreateForUser(tc.getCreateForUser());
		ticket.setAdditionalComments(commentss);
		ticket.setSla(tc.getSla());
		ticket.setResolveTimeStamp(tc.getResolveTimeStamp());

		return ticket;

	}

	public TicketCommentDto getByticketcommentsId(Long ticketId) {

		return null;

	}

	public boolean deleteById(long ticketId) throws NotFoundException {
		TicketCreation ticket = ticketCreationRepository.findById(ticketId).orElseThrow(
				() -> new ResourceNotFoundException("ticket Id : " + ticketId + " is Not Present in DataBase"));
		ticket.setStatus(false); // Update status to false
		ticketCreationRepository.save(ticket);
		return true;
	}

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	public TicketCreationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

//	public void sendEmail(TicketCreation use) throws IOException {
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(use.getEmailId());
//		mail.setSubject("Testing Mail API");
//		mail.setText("Dear Sir/madam ,\r\n" + "\r\n"
//				+ "Ticket has been created and please find the below ticket id :\r\n" + "\r\n" + use.getTicketId());
//		javaMailSender.send(mail);
//	}
	public void sendEmail(TicketCreation ticketDetails, String ticketNo) throws IOException, MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// TicketCreation t = ticketCreationRepository.findByTicketId(107);
		try {
			helper.setTo(ticketDetails.getEmailId());
			helper.setSubject(ticketNo + " support team");

			StringBuilder emailContent = new StringBuilder();
			emailContent.append("<html><body>");
			// emailContent.append(ticketDetails.getTicketNo()).append(" <h2> support
			// team</h2>");
			if (ticketDetails.getCreateForUser() != null) {
				emailContent.append("<p>Dear ").append(ticketDetails.getCreateForUser().getFirstName()).append(",</p>");
			} else if (ticketDetails.getCategory() != null) {
				emailContent.append("<p>Dear ").append(ticketDetails.getCustomer().getFirstName()).append(",</p>");
			} else {
				emailContent.append("<p>Dear ").append(ticketDetails.getEmailId()).append(",</p>");
			}
			emailContent.append("<p>Greeting ! Thank you for contacting TCPL Support Team</p>");
			emailContent.append("<p>Ticket has been logged successfully. Please find the below details.</p>");

			// Add ticket details to the table with a border
			emailContent.append("<table style='border-collapse: collapse; border: 1px solid blue;'>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Ticket ID</b></td><td style='border: 1px solid blue;'>")
					.append(ticketNo).append("</td></tr>");
			if (ticketDetails.getCreateForUser() != null) {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(ticketDetails.getCreateForUser().getFirstName()).append("</td></tr>");
			} else if (ticketDetails.getCategory() != null) {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(ticketDetails.getCustomer().getFirstName()).append("</td></tr>");
			} else {
				emailContent.append("<p>Dear ").append(ticketDetails.getEmailId()).append(",</p>");
			}
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submitter Email Address</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getEmailId()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submit Date and Time</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCreatedDate()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Current Status</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getTicketStatus()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Category</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCategory().getCategoryName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Current Status</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getTicketStatus()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Priority</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getPriority().getPriorityName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>AssignedTo</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getAssignedTo().getFirstName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Issue Description</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getShortNotes()).append("</td></tr>");
			// Add other fields as needed
			emailContent.append("</table>");

			emailContent.append("<p>Thank you for your attention.</p>");
			emailContent.append("</body></html>");

			helper.setText(emailContent.toString(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// Handle exception
		}
	}

	public void sendEmailToCustomer(TicketCreation ticketDetails, Customer customer, String ticketNo)
			throws IOException, MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// TicketCreation t = ticketCreationRepository.findByTicketId(107);
		customer.getFirstName();
		try {
			helper.setTo(ticketDetails.getEmailId());
			helper.setSubject(ticketNo + " support team");

			StringBuilder emailContent = new StringBuilder();
			emailContent.append("<html><body>");
			// emailContent.append(ticketDetails.getTicketNo()).append(" <h2> support
			// team</h2>");
			emailContent.append("<p>Dear ").append(customer.getFirstName() + " ").append(customer.getLastName())
					.append(",</p>");
			emailContent.append("<p>Greeting ! Thank you for contacting TCPL Support Team</p>");
			emailContent.append("<p>Ticket has been logged successfully. Please find the below details.</p>");

			// Add ticket details to the table with a border
			emailContent.append("<table style='border-collapse: collapse; border: 1px solid blue;'>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Ticket ID</b></td><td style='border: 1px solid blue;'>")
					.append(ticketNo).append("</td></tr>");
			if (customer.getFirstName() != null) {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(customer.getFirstName()).append("</td></tr>");
			} else {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(customer.getContact()).append("</td></tr>");
			}
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submitter Email Address</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getEmailId()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submit Date and Time</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCreatedDate()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Current Status</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getTicketStatus()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Category</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCategory().getCategoryName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Priority</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getPriority().getPriorityName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>AssignedTo</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getAssignedTo().getFirstName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Issue Description</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getShortNotes()).append("</td></tr>");

			// Add other fields as needed
			emailContent.append("</table>");

			emailContent.append("<p>Thank you for your attention.</p>");
			emailContent.append("</body></html>");

			helper.setText(emailContent.toString(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// Handle exception
		}
	}

	public void updateSendEmail(long id, TicketCreation ticketDetails) throws IOException, MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		try {
			helper.setTo(ticketDetails.getEmailId());
			helper.setSubject("T-20230711023452" + " support team");

			StringBuilder emailContent = new StringBuilder();
			emailContent.append("<html><body>");
			// emailContent.append(ticketDetails.getTicketNo()).append(" <h2> support
			// team</h2>");
			if (ticketDetails.getCreateForUser() != null) {
				emailContent.append("<p>Dear ").append(ticketDetails.getCreateForUser().getFirstName()).append(",</p>");
			} else if (ticketDetails.getCategory() != null) {
				emailContent.append("<p>Dear ").append(ticketDetails.getCustomer().getFirstName()).append(",</p>");
			} else {
				emailContent.append("<p>Dear ").append(ticketDetails.getEmailId()).append(",</p>");
			}
			emailContent.append("<p>Greeting ! Thank you for contacting TCPL Support Team</p>");
			emailContent.append("<p>Please find the udpate on your ticket</p>");

			// Add ticket details to the table with a border
			emailContent.append("<table style='border-collapse: collapse; border: 1px solid blue;'>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Ticket ID</b></td><td style='border: 1px solid blue;'>")
					.append("T-20230711023452").append("</td></tr>");
			if (ticketDetails.getCreateForUser() != null) {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(ticketDetails.getCreateForUser().getFirstName()).append("</td></tr>");
			} else if (ticketDetails.getCategory() != null) {
				emailContent.append(
						"<tr><td style='border: 1px solid blue;'><b>Submitter Name </b></td><td style='border: 1px solid blue;'>")
						.append(ticketDetails.getCustomer().getFirstName()).append("</td></tr>");
			} else {
				emailContent.append("<p>Dear ").append(ticketDetails.getEmailId()).append(",</p>");
			}
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submitter Email Address</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getEmailId()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Submit Date and Time</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCreatedDate()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Category</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getCategory().getCategoryName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Current Status</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getTicketStatus()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Priority</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getPriority().getPriorityName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>AssignedTo</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getAssignedTo().getFirstName()).append("</td></tr>");
			emailContent.append(
					"<tr><td style='border: 1px solid blue;'><b>Issue Description</b></td><td style='border: 1px solid blue;'>")
					.append(ticketDetails.getShortNotes()).append("</td></tr>");
			// Add other fields as needed
			emailContent.append("</table>");

			emailContent.append("<p>Thank you for your attention.</p>");
			emailContent.append("</body></html>");

			helper.setText(emailContent.toString(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// Handle exception
		}
	}

	public List<commentTicketsDto> getTicketsByCreatedByAndTicketStatus(int createdBy, TicketStatusEnum ticketStatus) { // Changed

		UserDao userDao = new UserDao();
		userDao.setUserId(createdBy);
		List<TicketCreation> tickets = ticketCreationRepository.findByCreatedByAndTicketStatus(userDao, ticketStatus);

		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;

	}

	public List<commentTicketsDto> getTicketsByCreatedBy(int createdBy) { // Changed from "userId"

		UserDao userDao = new UserDao();
		userDao.setUserId(createdBy);
		List<TicketCreation> tickets = ticketCreationRepository.findByCreatedBy(userDao);

		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> getByCreatedByAndDepartment(int createdBy, int departmentId) { // Changed

		UserDao userDao = new UserDao();
		userDao.setUserId(createdBy);

		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentId(departmentId);

		List<TicketCreation> tickets = ticketCreationRepository.findByCreatedByAndDepartment(userDao, department);

		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;

	}

	public List<commentTicketsDto> findAllByOrderByCreatedDateDesc() { // Changed
		Pageable pageable = PageRequest.of(0, 6); // Fetches only 6 records

		List<RecentTicketDto> rc = new ArrayList<>();

		List<TicketCreation> tickets = ticketCreationRepository.findAllByOrderByCreatedDateDesc(pageable);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> findLatestTickets(int createdBy) {
		Pageable pageable = PageRequest.of(0, 6); // Fetches only 6 records

		UserDao userDao = new UserDao();
		userDao.setUserId(createdBy);

		List<RecentTicketDto> rc = new ArrayList<>();

		List<TicketCreation> tickets = ticketCreationRepository.findByCreatedByOrderByCreatedDateDesc(userDao,
				pageable);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> findLatestByAssignedTo(int AssignedTo) {
		Pageable pageable = PageRequest.of(0, 6); // Fetches only 6 records

		UserDao userDao = new UserDao();
		userDao.setUserId(AssignedTo);

		List<RecentTicketDto> rc = new ArrayList<>();

		List<TicketCreation> tickets = ticketCreationRepository.findByAssignedToOrderByCreatedDateDesc(userDao,
				pageable);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> getByDepartment(int departmentId) { // Changed
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentId(departmentId);

		List<TicketCreation> tickets = ticketCreationRepository.findByDepartment(department);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> getByDepartmentAndTicketStatus(int departmentId, TicketStatusEnum ticketStatus) {
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentId(departmentId);

		List<TicketCreation> tickets = ticketCreationRepository.findByDepartmentAndTicketStatus(department,
				ticketStatus);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> getByCreatedByAndDepartmentAndTicketStatus(int createdBy, int departmentId,
			TicketStatusEnum ticketStatus) {

		UserDao userDao = new UserDao();
		userDao.setUserId(createdBy);

		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentId(departmentId);

		List<TicketCreation> tickets = ticketCreationRepository.findByCreatedByAndDepartmentAndTicketStatus(userDao,
				department, ticketStatus);

		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;

	}

	public List<commentTicketsDto> getTicketsByAssignedTo(int AssignedTo) { // Changed from "userId"

		UserDao userDao = new UserDao();
		userDao.setUserId(AssignedTo);

		List<TicketCreation> tickets = ticketCreationRepository.findByAssignedTo(userDao);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<commentTicketsDto> getTicketsByAssignedToAndTicketStatus(int AssignedTo,
			TicketStatusEnum ticketStatus) { // Changed

		UserDao userDao = new UserDao();
		userDao.setUserId(AssignedTo);
		List<TicketCreation> tickets = ticketCreationRepository.findByAssignedToAndTicketStatus(userDao, ticketStatus);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;

	}

	public List<commentTicketsDto> getTicketsByTicketSatus(TicketStatusEnum ticketStatus) { // Changed from "userId"
		List<TicketCreation> tickets = ticketCreationRepository.findByTicketStatus(ticketStatus);
		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);

			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public List<StatisticsDTO> getStatistics() {
		List<StatisticsDTO> statistics = new ArrayList<>();

		StatisticsDTO categoryStatistics = new StatisticsDTO();
		categoryStatistics.setName("Category");
		categoryStatistics.setTotal(categoryRepository.findAll().size());
		statistics.add(categoryStatistics);

		StatisticsDTO subcategoryStatistics = new StatisticsDTO();
		subcategoryStatistics.setName("Subcategory");
		subcategoryStatistics.setTotal(subCategoryRepository.findAll().size());
		statistics.add(subcategoryStatistics);

		StatisticsDTO serviceTitleStatistics = new StatisticsDTO();
		serviceTitleStatistics.setName("Service Title");
		serviceTitleStatistics.setTotal(serviceTitleRepository.findAll().size());
		statistics.add(serviceTitleStatistics);

		StatisticsDTO departmentStatistics = new StatisticsDTO();
		departmentStatistics.setName("Department");
		departmentStatistics.setTotal(departmentRepository.findAll().size());
		statistics.add(departmentStatistics);
// departmentStatistics.setTotal(this.getTicketsByAssignedTo(1).size());
		// departmentStatistics.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.New).size());
		return statistics;
	}

	public List<StatisticsDTO> getStatisticsForUserAssignToMe(int asssignedTo) {
		List<StatisticsDTO> statistics = new ArrayList<>();

		StatisticsDTO assignTome = new StatisticsDTO();
		assignTome.setName("All Tickets ");
		assignTome.setTotal(this.getTicketsByAssignedTo(asssignedTo).size());
		statistics.add(assignTome);

		StatisticsDTO assignTomeNew = new StatisticsDTO();
		assignTomeNew.setName("New Tickets");
		assignTomeNew.setTotal(this.getTicketsByAssignedToAndTicketStatus(asssignedTo, TicketStatusEnum.New).size());
		statistics.add(assignTomeNew);

		StatisticsDTO assignTomeInProgress = new StatisticsDTO();
		assignTomeInProgress.setName("In Progres Tickets");
		assignTomeInProgress
				.setTotal(this.getTicketsByAssignedToAndTicketStatus(asssignedTo, TicketStatusEnum.In_Progress).size());
		statistics.add(assignTomeInProgress);

		StatisticsDTO assignTomePendingClient = new StatisticsDTO();
		assignTomePendingClient.setName("PendingClient Tickets");
		assignTomePendingClient.setTotal(
				this.getTicketsByAssignedToAndTicketStatus(asssignedTo, TicketStatusEnum.Pending_Client).size());
		statistics.add(assignTomePendingClient);

		StatisticsDTO assignTomeResolved = new StatisticsDTO();
		assignTomeResolved.setName("Resolved Tickets");
		assignTomeResolved
				.setTotal(this.getTicketsByAssignedToAndTicketStatus(asssignedTo, TicketStatusEnum.Resolved).size());
		statistics.add(assignTomeResolved);

		StatisticsDTO assignTomeClosed = new StatisticsDTO();
		assignTomeClosed.setName("Closed Tickets");
		assignTomeClosed
				.setTotal(this.getTicketsByAssignedToAndTicketStatus(asssignedTo, TicketStatusEnum.Closed).size());
		statistics.add(assignTomeClosed);

		return statistics;
	}
	// groupBy get all user ticket status wise +2

	public List<StatisticsDTO> getStatisticsForUserCreatedBy(int createdBy) {
		List<StatisticsDTO> statistics = new ArrayList<>();

		StatisticsDTO assignTome = new StatisticsDTO();
		assignTome.setName("All Tickets ");
		assignTome.setTotal(this.getTicketsByCreatedBy(createdBy).size());
		statistics.add(assignTome);

		StatisticsDTO assignTomeNew = new StatisticsDTO();
		assignTomeNew.setName("New Tickets");
		assignTomeNew.setTotal(this.getTicketsByCreatedByAndTicketStatus(createdBy, TicketStatusEnum.New).size());
		statistics.add(assignTomeNew);

		StatisticsDTO assignTomeInProgress = new StatisticsDTO();
		assignTomeInProgress.setName("In Progres Tickets");
		assignTomeInProgress
				.setTotal(this.getTicketsByCreatedByAndTicketStatus(createdBy, TicketStatusEnum.In_Progress).size());
		statistics.add(assignTomeInProgress);

		StatisticsDTO assignTomePendingClient = new StatisticsDTO();
		assignTomePendingClient.setName("PendingClient Tickets");
		assignTomePendingClient
				.setTotal(this.getTicketsByCreatedByAndTicketStatus(createdBy, TicketStatusEnum.Pending_Client).size());
		statistics.add(assignTomePendingClient);

		StatisticsDTO assignTomeResolved = new StatisticsDTO();
		assignTomeResolved.setName("Resolved Tickets");
		assignTomeResolved
				.setTotal(this.getTicketsByCreatedByAndTicketStatus(createdBy, TicketStatusEnum.Resolved).size());
		statistics.add(assignTomeResolved);

		StatisticsDTO assignTomeClosed = new StatisticsDTO();
		assignTomeClosed.setName("Closed Tickets");
		assignTomeClosed.setTotal(this.getTicketsByCreatedByAndTicketStatus(createdBy, TicketStatusEnum.Closed).size());
		statistics.add(assignTomeClosed);

		return statistics;
	}

	// groupBy Ticket Status For ADMIN
	public List<StatisticsDTO> getTicketStatusCounts() {
		List<StatisticsDTO> statistics = new ArrayList<>();

		StatisticsDTO allTickets = new StatisticsDTO();
		allTickets.setName("All Tickets ");
		allTickets.setTotal(ticketCreationRepository.findAll().size());
		statistics.add(allTickets);

		StatisticsDTO statusWiseNew = new StatisticsDTO();
		statusWiseNew.setName("New Tickets");
		statusWiseNew.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.New).size());
		statistics.add(statusWiseNew);

		StatisticsDTO statusWiseInProgress = new StatisticsDTO();
		statusWiseInProgress.setName("In Progres Tickets");
		statusWiseInProgress.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.In_Progress).size());
		statistics.add(statusWiseInProgress);

		StatisticsDTO statusWisePendingClient = new StatisticsDTO();
		statusWisePendingClient.setName("PendingClient Tickets");
		statusWisePendingClient
				.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.Pending_Client).size());
		statistics.add(statusWisePendingClient);

		StatisticsDTO statusWiseResolved = new StatisticsDTO();
		statusWiseResolved.setName("Resolved Tickets");
		statusWiseResolved.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.Resolved).size());
		statistics.add(statusWiseResolved);

		StatisticsDTO statusWiseClosed = new StatisticsDTO();
		statusWiseClosed.setName("Closed Tickets");
		statusWiseClosed.setTotal(ticketCreationRepository.findByTicketStatus(TicketStatusEnum.Closed).size());
		statistics.add(statusWiseClosed);

		return statistics;
	}

	// groupBy AssignTo For ADMIN
	public List<AssignedToCountTicketsDto> getTicketAssignToUsers() {

		List<Object[]> ob = ticketCreationRepository.getTicketStatusCountByAssignedTo();
		List<AssignedToCountTicketsDto> assignedUsers = new ArrayList<>();
		for (Object[] obj : ob) {
			AssignedToCountTicketsDto assigned = new AssignedToCountTicketsDto();
			assigned.setUserId((int) obj[0]);
			assigned.setUsername((String) obj[1]);
			assigned.setFirstName((String) obj[2]);
			assigned.setLastName((String) obj[3]);
			assigned.setTotalTicketAssigned((Long) obj[4]);

			assignedUsers.add(assigned);
		}

		return assignedUsers;
	}

	// groupBy SLA over For ADMIN
	public List<SLAOverCountDto> countBySLA() {

		List<Object[]> ob = ticketCreationRepository.countByAssignedTo();
		List<SLAOverCountDto> countOverSla = new ArrayList<>();
		for (Object[] obj : ob) {
			SLAOverCountDto count = new SLAOverCountDto();
			count.setUserId((int) obj[0]);
			count.setUsername((String) obj[1]);
			count.setFirstName((String) obj[2]);
			count.setLastName((String) obj[3]);
			count.setTotalOverCount((long) obj[4]);

			countOverSla.add(count);
		}
		Collections.sort(countOverSla, Comparator.comparingLong(SLAOverCountDto::getTotalOverCount).reversed());

		// Fetch only the first 15 elements
		int limit = Math.min(15, countOverSla.size());
		countOverSla = countOverSla.subList(0, limit);
		return countOverSla;
	}

	// groupBy department wise ticket For user
	public List<DepartmentWiseCountDto> getDepartmentTicketStatusCount(int createdBy, int department) {

		List<Object[]> ob = ticketCreationRepository.getDepartmentTicketStatusCount(createdBy, department);
		List<DepartmentWiseCountDto> counDeptWise = new ArrayList<>();
		for (Object[] obj : ob) {
			DepartmentWiseCountDto count = new DepartmentWiseCountDto();
			count.setDepartmentId((int) (obj[0]));
			count.setDepartmentName((String) obj[1]);
			count.setCount((long) obj[2]);

			counDeptWise.add(count);
		}

		return counDeptWise;
	}

	// RESOLVER------

	// groupBy assigned to user in my department For Resolver UI
	public List<SLAOverCountDto> getAssignedToCountDepartmentWise(int departmentId) {

		List<Object[]> ob = ticketCreationRepository.getAssignedToCount(departmentId);
		List<SLAOverCountDto> countDepartmentWise = new ArrayList<>();
		for (Object[] obj : ob) {
			SLAOverCountDto count = new SLAOverCountDto();
			count.setUserId((int) obj[0]);
			count.setUsername((String) obj[1]);
			count.setFirstName((String) obj[2]);
			count.setLastName((String) obj[3]);
			count.setTotalOverCount((long) obj[4]);

			countDepartmentWise.add(count);
		}

		Collections.sort(countDepartmentWise, Comparator.comparingLong(SLAOverCountDto::getTotalOverCount).reversed());

		// Fetch only the first 15 elements
		int limit = Math.min(15, countDepartmentWise.size());
		countDepartmentWise = countDepartmentWise.subList(0, limit);
		return countDepartmentWise;
	}

	// Get all ticket of my department status wise for resolver
	public List<StatisticsDTO> getStatisticsDepartmentWiseTicketStatus(int departmentId) {
		List<StatisticsDTO> statistics = new ArrayList<>();

		StatisticsDTO allticketsDepartmentstatus = new StatisticsDTO();
		allticketsDepartmentstatus.setName("All Tickets ");
		allticketsDepartmentstatus.setTotal(this.getByDepartment(departmentId).size());
		statistics.add(allticketsDepartmentstatus);

		StatisticsDTO DepartmentstatusNew = new StatisticsDTO();
		DepartmentstatusNew.setName("New Tickets");
		DepartmentstatusNew.setTotal(this.getByDepartmentAndTicketStatus(departmentId, TicketStatusEnum.New).size());
		statistics.add(DepartmentstatusNew);

		StatisticsDTO DepartmentstatusInProgress = new StatisticsDTO();
		DepartmentstatusInProgress.setName("In Progres Tickets");
		DepartmentstatusInProgress
				.setTotal(this.getByDepartmentAndTicketStatus(departmentId, TicketStatusEnum.In_Progress).size());
		statistics.add(DepartmentstatusInProgress);

		StatisticsDTO DepartmentstatusPendingClient = new StatisticsDTO();
		DepartmentstatusPendingClient.setName("PendingClient Tickets");
		DepartmentstatusPendingClient
				.setTotal(this.getByDepartmentAndTicketStatus(departmentId, TicketStatusEnum.Pending_Client).size());
		statistics.add(DepartmentstatusPendingClient);

		StatisticsDTO DepartmentstatusResolved = new StatisticsDTO();
		DepartmentstatusResolved.setName("Resolved Tickets");
		DepartmentstatusResolved
				.setTotal(this.getByDepartmentAndTicketStatus(departmentId, TicketStatusEnum.Resolved).size());
		statistics.add(DepartmentstatusResolved);

		StatisticsDTO DepartmentstatusClosed = new StatisticsDTO();
		DepartmentstatusClosed.setName("Closed Tickets");
		DepartmentstatusClosed
				.setTotal(this.getByDepartmentAndTicketStatus(departmentId, TicketStatusEnum.Closed).size());
		statistics.add(DepartmentstatusClosed);

		return statistics;
	}

//resolver gett all ticket assigned to me department wise
	public List<DepartmentWiseCountDto> getTicketStatusByDepartmentId(int assignedTo, int department) {

		List<Object[]> ob = ticketCreationRepository.getTicketStatusByDepartmentId(assignedTo, department);
		List<DepartmentWiseCountDto> countDeptWise = new ArrayList<>();
		for (Object[] obj : ob) {
			DepartmentWiseCountDto count = new DepartmentWiseCountDto();
			count.setDepartmentId((int) (obj[0]));
			count.setDepartmentName((String) obj[1]);
			count.setCount((long) obj[2]);

			countDeptWise.add(count);
		}

		return countDeptWise;
	}

	// resolver Ticket SLA assigned
	public List<StatisticsDTO> countByAssignedToAndSla(int assignToId) {
		List<StatisticsDTO> response = new ArrayList<>();
		UserDao userDao = new UserDao();
		userDao.setUserId(assignToId);

		StatisticsDTO slaOver = new StatisticsDTO();
		slaOver.setName("Over the SLA");
		slaOver.setTotal(ticketCreationRepository.countByAssignedToAndSla(userDao, "Over"));
		response.add(slaOver);

		StatisticsDTO slaUnder = new StatisticsDTO();
		slaUnder.setName("Under the SLA");
		slaUnder.setTotal(ticketCreationRepository.countByAssignedToAndSla(userDao, "Under"));
		response.add(slaUnder);

		StatisticsDTO totalAssigned = new StatisticsDTO();
		totalAssigned.setName("Total Tickets");
		totalAssigned.setTotal(ticketCreationRepository.findByAssignedTo(userDao).size());
		response.add(totalAssigned);

		return response;
	}

	// resolver Department ticket SLA
	public List<StatisticsDTO> countByDepartmentAndSla(int departmentId) {
		List<StatisticsDTO> response = new ArrayList<>();
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartmentId(departmentId);

		StatisticsDTO slaOver = new StatisticsDTO();
		slaOver.setName("Over the SLA");
		slaOver.setTotal(ticketCreationRepository.countByDepartmentAndSla(department, "Over"));
		response.add(slaOver);

		StatisticsDTO slaUnder = new StatisticsDTO();
		slaUnder.setName("Under the SLA");
		slaUnder.setTotal(ticketCreationRepository.countByDepartmentAndSla(department, "Under"));
		response.add(slaUnder);

		StatisticsDTO totalTickets = new StatisticsDTO();
		totalTickets.setName("Total Tickets");
		totalTickets.setTotal(ticketCreationRepository.findByDepartment(department).size());
		response.add(totalTickets);

		return response;
	}

	public List<commentTicketsDto> getAllWithComments() {
		List<TicketCreation> tickets = this.getAll();

		List<commentTicketsDto> commentTickets = new ArrayList<>();

		for (TicketCreation comments : tickets) {
			Long ticketId = comments.getTicketId();
			TicketCreation tc = ticketCreationRepository.findById(ticketId)
					.orElseThrow(() -> new ResourceNotFoundException("Ticket Id :" + ticketId + "Unavailable"));

			commentTicketsDto ticket = new commentTicketsDto();
//			List<commentTicketsDto> comments = additionalCommentsService.getByTicketId(ticketId);
			ticket.setTicketId(tc.getTicketId());
			ticket.setTicketNo(tc.getTicketNo());
			ticket.setEmailId(tc.getEmailId());
			ticket.setCategory(tc.getCategory());

			ticket.setSubCategory(tc.getSubCategory());
			ticket.setServiceTitle(tc.getServiceTitle());
			ticket.setPriority(tc.getPriority());
			ticket.setIssue(tc.getIssue());
			ticket.setDepartment(tc.getDepartment());
			ticket.setAlternativeContactNo(tc.getAlternativeContactNo());
			ticket.setAssignedTo(tc.getAssignedTo());
			ticket.setShortNotes(tc.getShortNotes());
			ticket.setCreatedBy(tc.getCreatedBy());
			ticket.setCreatedDate(tc.getCreatedDate());
			ticket.setUpdatedBy(tc.getUpdatedBy());
			ticket.setUpdatedDate(tc.getUpdatedDate());
			ticket.setStatus(tc.isStatus());
			ticket.setTicketStatus(tc.getTicketStatus());
			ticket.setCreateForUser(tc.getCreateForUser());
			TicketCreation ticketCreation = new TicketCreation();
			ticketCreation.setTicketId(ticketId);
			List<AdditionalCommentsDto> commentss = additionalCommentsService.getByTicketId(ticketId);
			ticket.setSla(tc.getSla());
			ticket.setResolveTimeStamp(tc.getResolveTimeStamp());
			ticket.setAdditionalComments(commentss);
			commentTickets.add(ticket);
		}

		return commentTickets;
	}

	public TicketCreationService() throws IOException {
	}
}
