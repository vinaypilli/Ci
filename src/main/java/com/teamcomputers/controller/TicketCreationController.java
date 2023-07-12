package com.teamcomputers.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamcomputers.dto.AdditionalCommentsDto;
import com.teamcomputers.dto.AssignedToCountTicketsDto;
import com.teamcomputers.dto.DepartmentWiseCountDto;
import com.teamcomputers.dto.SLAOverCountDto;
import com.teamcomputers.dto.StatisticsDTO;
import com.teamcomputers.dto.commentTicketsDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.AdditionalComments;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.TicketCreation;
import com.teamcomputers.model.TicketStatusEnum;
import com.teamcomputers.service.AdditionalCommentsService;
import com.teamcomputers.service.TicketCreationService;

@RestController
@RequestMapping("/ticket")
public class TicketCreationController {
	@Autowired
	private TicketCreationService ticketCreationService;

	@Autowired
	private AdditionalCommentsService additionalCommentsService;

	String message;

	@PostMapping
	public ResponseEntity<?> uploadFile(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("userData") String userData) throws IOException, MessagingException {
//		, @RequestParam("comment") String comment
		String message = "";

		if (userData.isEmpty()) {
			message = "User data parameter is empty.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String currentDateTimeString = currentDateTime.format(formatter);
		String ticketNo = "T-" + currentDateTimeString;
		TicketCreation ticketDetails = ticketCreationService.create(userData,ticketNo);

		if (file == null || file.isEmpty()) {
			if (ticketDetails != null) {
				try {

					additionalCommentsService.add(ticketDetails.getTicketId(), userData, ticketDetails.getCreatedBy());
					ticketCreationService.sendEmail(ticketDetails,ticketNo);

				} catch (MailException mailException) {
					// System.out.println(mailException);
					message = message + "";
				}
				message = "Ticket has been Created successfully: ";
			} else {
				message = "Could not Ticket Created ";
			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} else {

			if (ticketDetails != null) {
				try {

					additionalCommentsService.add(ticketDetails.getTicketId(), userData, ticketDetails.getCreatedBy(),
							file);
					ticketCreationService.sendEmail(ticketDetails,ticketNo);

				} catch (MailException mailException) {
					// System.out.println(mailException);
					message = message + "";
				}
				message = "Ticket has been Created successfully: ";
			} else {
				message = "Could not Ticket Created ";
			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/{ticketId}")
	private ResponseEntity<ResponseMessage> uploadFile(@PathVariable long ticketId,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("userData") String userData) throws IOException, MessagingException {

		String message = "";

		if (userData.isEmpty()) {
			message = "User data parameter is empty.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}

		TicketCreation ticketDetails = ticketCreationService.update(ticketId, userData);

		if (file == null || file.isEmpty()) {
			if (ticketDetails != null) {
				try {

					additionalCommentsService.update(ticketDetails.getTicketId(), userData,
							ticketDetails.getUpdatedBy());
					ticketCreationService.updateSendEmail(ticketId, ticketDetails);

				} catch (MailException mailException) {
					// System.out.println(mailException);
					message = message + "";
				}
				message = "Ticket has been Updated successfully: ";
			} else {
				message = "Could not Ticket Created ";
			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} else {

			if (ticketDetails != null) {
				try {

					additionalCommentsService.add(ticketDetails.getTicketId(), userData, ticketDetails.getUpdatedBy(),
							file);
					ticketCreationService.updateSendEmail(ticketId,ticketDetails);

				} catch (MailException mailException) {
					// System.out.println(mailException);
					message = message + "";
				}
				message = "Ticket has been Updated successfully: ";
			} else {
				message = "Could not Ticket Created ";
			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}
	}

//	Comment

	@GetMapping("/comments/{ticketId}")
	public List<AdditionalCommentsDto> getAllActiveSubcategoriesByCategoryId(@PathVariable Long ticketId) {
		return additionalCommentsService.getByTicketId(ticketId);
	}

//	@GetMapping("/ticketscomments/{ticketId}")
//	public TicketCommentDto getAll(@PathVariable Long ticketId) {
//
//		TicketCreation ti = ticketCreationService.getById(ticketId);
//		List<AdditionalCommentsDto> co = additionalCommentsService.getByTicketId(ticketId);
//		TicketCommentDto tico = new TicketCommentDto(ti, co);
//		return tico;
//	}

	@GetMapping("/comments")
	public List<AdditionalComments> getAllComments() {
		return additionalCommentsService.getAll();
	}

	@GetMapping
	private List<TicketCreation> getAll() {
		return ticketCreationService.getAll();
	}

	@GetMapping("/{ticketId}")
	private commentTicketsDto getById(@PathVariable Long ticketId) {
		return ticketCreationService.getById(ticketId);
	}

	@GetMapping("/ticketscomments")
	public List<commentTicketsDto> getAllWithComment() {
		return ticketCreationService.getAllWithComments();
	}

//	For Download

//	@GetMapping
//	public List<TicketCommentDto> getAllWithComments() {
//		List<TicketCreation> tickets = ticketCreationService.getAll();
//		List<TicketCommentDto> ticketComments = new ArrayList<>();
//
//		for (TicketCreation ticket : tickets) {
//			Long ticketId = ticket.getTicketId();
//			List<AdditionalCommentsDto> comments = additionalCommentsService.getByTicketId(ticketId);
//			TicketCommentDto ticketComment = new TicketCommentDto(ticket, comments);
//			ticketComments.add(ticketComment);
//		}

//		return ticketComments;
//	}

//	@GetMapping("/file/{ticketId}")
//	public ResponseEntity<?> downloadImage(@PathVariable Long ticketId) throws IOException {
//		byte[] imageData = ticketCreationService.downloadImageFromFileSystem(ticketId);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
//	}

//	@GetMapping("/file/{ticketId}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable Long ticketId) throws IOException {
//		byte[] fileData = ticketCreationService.downloadImageFromFileSystem(ticketId);
//		ByteArrayResource resource = new ByteArrayResource(fileData);
//
//		// Determine the file's content type based on the file extension or use a
//		// default content type
//		String contentType = "application/octet-stream"; // Default content type
//		// You can use a library like Apache Tika to detect the content type based on
//		// the file data or extension
//		// Example: String contentType = Tika.detect(resource.getInputStream());
//
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file_" + ticketId + "\"")
//				.contentType(MediaType.parseMediaType(contentType)).contentLength(fileData.length).body(resource);
//	}

//	For createdBy

	@GetMapping("/createdBy/{createdBy}")
	public List<commentTicketsDto> getTicketsByCreatedBy(@PathVariable int createdBy) { // Changed from "userId"
		return ticketCreationService.getTicketsByCreatedBy(createdBy);
	}

	@GetMapping("/createdBy/{createdBy}/ticketStatus/{ticketStatus}")
	public List<commentTicketsDto> getTicketsByCreatedBy(@PathVariable int createdBy,
			@PathVariable TicketStatusEnum ticketStatus) { // Changed from "userId"
		return ticketCreationService.getTicketsByCreatedByAndTicketStatus(createdBy, ticketStatus);
	}

	@GetMapping("/createdBy/{createdBy}/departmentId/{department}/ticketStatus/{ticketStatus}")
	public List<commentTicketsDto> getCreatedByAndDepartmentAndTicketStatus(@PathVariable int createdBy,
			@PathVariable int department, @PathVariable TicketStatusEnum ticketStatus) { // Changed from "userId"
		return ticketCreationService.getByCreatedByAndDepartmentAndTicketStatus(createdBy, department, ticketStatus);
	}

	@GetMapping("/createdBy/{createdBy}/departmentId/{department}")
	public List<commentTicketsDto> getCreatedByAndDepartment(@PathVariable int createdBy,
			@PathVariable int department) { 
		return ticketCreationService.getByCreatedByAndDepartment(createdBy, department);
	}

	

//	For AssignedTo

	@GetMapping("/AssignedTo/{assignedTo}")
	public List<commentTicketsDto> getTicketsByAssignedTo(@PathVariable int assignedTo) { // Changed from "userId"
		return ticketCreationService.getTicketsByAssignedTo(assignedTo);
	}

	@GetMapping("/AssignedTo/{assignedTo}/ticketStatus/{ticketStatus}")
	public List<commentTicketsDto> getTicketsByAssignedTo(@PathVariable int assignedTo,
			@PathVariable TicketStatusEnum ticketStatus) { // Changed from "userId"
		return ticketCreationService.getTicketsByAssignedToAndTicketStatus(assignedTo, ticketStatus);
	}

	

//	For Department

	@GetMapping("departmentId/{department}")
	public List<commentTicketsDto> getByDepartment(@PathVariable int department) { // Changed from "userId"
		return ticketCreationService.getByDepartment(department);
	}

	@GetMapping("departmentId/{department}/ticketStatus/{ticketStatus}")
	public List<commentTicketsDto> getDepartmentAndTicketStatus(@PathVariable int department,
			@PathVariable TicketStatusEnum ticketStatus) { // Changed from "userId"
		return ticketCreationService.getByDepartmentAndTicketStatus(department, ticketStatus);
	}

//	For ticketsStatus

	@GetMapping("/ticketStatus/{ticketStatus}")
	public List<commentTicketsDto> getTicketsByTicketSatus(@PathVariable TicketStatusEnum ticketStatus) { // Changed from
																										// "userId"
		return ticketCreationService.getTicketsByTicketSatus(ticketStatus);
	}

//	For Recent tickets

	@GetMapping("/resentAllTickets")
	public List<commentTicketsDto> getTicketsByTicketSatus() { // Changed from // "userId"
		return ticketCreationService.findAllByOrderByCreatedDateDesc();
	}
	
	@GetMapping("/resentTicketsByAssignedTo/{AssignedTo}")
	public List<commentTicketsDto> findLatestByAssignedTo(@PathVariable int AssignedTo) { // Changed from // "userId"
		return ticketCreationService.findLatestByAssignedTo(AssignedTo);
	}
	
	@GetMapping("/resentTicketsBycreatedBy/{createdBy}")
	public List<commentTicketsDto> getTicketsByTicketSatus(@PathVariable int createdBy) { // Changed from // "userId"
		return ticketCreationService.findLatestTickets(createdBy);
	}
	

	@DeleteMapping("/{ticketId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long ticketId) {
		try {
			ticketCreationService.deleteById(ticketId);
			message = "Ticket Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Ticket details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping("/admin/statistics")
	public ResponseEntity<List<StatisticsDTO>> getStatistics() {
		List<StatisticsDTO> statistics = ticketCreationService.getStatistics();
		return ResponseEntity.ok(statistics);
	}

	@GetMapping("/resolver-assigned/statistics/{assignedTo}")
	public ResponseEntity<List<StatisticsDTO>> getStatisticsForUserAssignToMe(@PathVariable int assignedTo) {
		List<StatisticsDTO> statistics = ticketCreationService.getStatisticsForUserAssignToMe(assignedTo);
		return ResponseEntity.ok(statistics);
	}

	// for user createdBy status and statistics api's
	@GetMapping("/createdBy/statistics/{createdBy}")
	public ResponseEntity<List<StatisticsDTO>> getStatisticsForUserCreatedBy(@PathVariable int createdBy) {
		List<StatisticsDTO> statistics = ticketCreationService.getStatisticsForUserCreatedBy(createdBy);
		return ResponseEntity.ok(statistics);
	}

	// groupBy Ticket Status for ADMIN
	@GetMapping("/status-count")
	public ResponseEntity<List<StatisticsDTO>> getTicketStatusCounts() {
		List<StatisticsDTO> statistics = ticketCreationService.getTicketStatusCounts();
		return ResponseEntity.ok(statistics);
	}

	// groupBy Ticket Status assigned
	@GetMapping("/assignto-counts")
	public List<AssignedToCountTicketsDto> getTicketAssignToUsers() {
		return ticketCreationService.getTicketAssignToUsers();
	}

	// groupBy SLA over For ADMIN
	@GetMapping("/SlaOver-counts")
	public List<SLAOverCountDto> countBySLA() {
		return ticketCreationService.countBySLA();
	}

	// groupBy department wise ticket For user
	@GetMapping("/department-wise/{department}/user/{createdBy}")
	public List<DepartmentWiseCountDto> getDepartmentTicketStatusCount(@PathVariable int createdBy,@PathVariable int department) {
		return ticketCreationService.getDepartmentTicketStatusCount(createdBy,department);
	}
	
	// groupBy assigned to user in my department  For Resolver UI
	@GetMapping("/assigned-mydepartment/{department}")
		public List<SLAOverCountDto> getAssignedToCountDepartmentWise(@PathVariable int department) {
			return ticketCreationService.getAssignedToCountDepartmentWise(department);
		}
	// Get all ticket of my department status wise for resolver
	@GetMapping("/resolver-departmentWiseTicket/{department}")
	public ResponseEntity<List<StatisticsDTO>> getStatisticsDepartmentWiseTicketStatus(@PathVariable int department) {
		List<StatisticsDTO> statistics = ticketCreationService.getStatisticsDepartmentWiseTicketStatus(department);
		return ResponseEntity.ok(statistics);
	}
	//resolver gett all ticket assigned to me department wise
	@GetMapping("/department-wise/{department}/resolver/{assignedTo}")
	public List<DepartmentWiseCountDto> getTicketStatusByDepartmentId(@PathVariable int assignedTo, @PathVariable int department) {
		return ticketCreationService.getTicketStatusByDepartmentId(assignedTo,department);
	}
	
	@GetMapping("/resolver-assignedTo-wise-slaTicket/{assignedTo}")
	public ResponseEntity<List<StatisticsDTO>> countByAssignedToAndSla(@PathVariable int assignedTo) {
		List<StatisticsDTO> statistics = ticketCreationService.countByAssignedToAndSla(assignedTo);
		return ResponseEntity.ok(statistics);
	}
	@GetMapping("/resolver-department-wise-slaTicket/{department}")
	public ResponseEntity<List<StatisticsDTO>> countByDepartmentAndSla(@PathVariable int department) {
		List<StatisticsDTO> statistics = ticketCreationService.countByDepartmentAndSla(department);
		return ResponseEntity.ok(statistics);
		
		
	}
	
	public String TicketCreationForCustomer(
			 String userData,Customer customer,String ticketNo) throws IOException, MessagingException {
//		, @RequestParam("comment") String comment
		String message = "";

		if (userData.isEmpty()) {
			message = "empty_data";
			return message;
		}
		
		TicketCreation ticketDetails = ticketCreationService.create(userData,ticketNo);

		
			if (ticketDetails != null) {
				try {

					additionalCommentsService.add(ticketDetails.getTicketId(), userData, ticketDetails.getCreatedBy());
					ticketCreationService.sendEmailToCustomer(ticketDetails, customer,ticketNo);

				} catch (MailException mailException) {
					// System.out.println(mailException);
					message = message + "";
				}
				message = "successfully";
			} else {
				message = "bad_request";
			}

			return message;
		
		}
	}

