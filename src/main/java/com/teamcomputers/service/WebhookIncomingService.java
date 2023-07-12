package com.teamcomputers.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.teamcomputers.controller.OutgoingMessageResponseController;
import com.teamcomputers.controller.TicketCreationController;
import com.teamcomputers.dto.TicketDto;
import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.ImageType;
import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.model.IncomingMessageShow;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.model.LocationType;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.model.OutgoingMessageShow;
import com.teamcomputers.model.TextType;
import com.teamcomputers.model.WebhookEntity;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.IncomingMessageShowRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;
import com.teamcomputers.repository.OutgoingMessageShowRepository;
import com.teamcomputers.repository.WebhookRepository;
import com.teamcomputers.websocket.ChatWebSocketComponent;

@Service
public class WebhookIncomingService<T> {

	@Autowired
	private WebhookRepository webhookRepository;
	@Autowired
	private OutgoingMessageResponseController<T> outgoingMessageResponseController;

	@Autowired
	private ChatWebSocketComponent chatWebSocketHandler;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;

	@Autowired
	private OutgoingActivityService outgoingActivityService;

	@Autowired
	private OutgoingMessageShowRepository outgoingMessageShowRepository;

	@Autowired
	private IncomingMessageShowRepository incomingMessageShowRepository;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private TicketCreationController ticketCreationController;

	@Autowired
	private TicketCreationController ticketCreationService;

	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@SuppressWarnings("unused")
	public ApiResponse save(WebhookEntity webhookEntity) throws IOException, MessagingException {

		ApiResponse response = new ApiResponse();
		List<IncomingMessage> incomingMessages = webhookEntity.getIncoming_message();
		if (incomingMessages != null && !incomingMessages.isEmpty()) {
			IncomingMessage incomingMessage = incomingMessages.get(0); // Assuming you only want to retrieve the first
			// incoming message
			String messageType = incomingMessage.getMessage_type();
			TextType textType = incomingMessage.getText_type();
			LocationType location_type = incomingMessage.getLocation_type();
			// String text = textType.getText();
			ImageType imageType = incomingMessage.getImage_type();
		//	System.out.println(textType.getText());
			boolean status = false;

			OutgoingActivity outgoingActivity2 = outgoingActivityRepository
					.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());

			Customer customers = customerRepository.findByContact(incomingMessage.getFrom());
			OutgoingMessageShow outgoingMessageShow = outgoingMessageShowRepository
					.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());
			IncomingMessageShow incomingMessageShows = incomingMessageShowRepository
					.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());
			List<IncomingMessageShow> incomingMessageShow1 = incomingMessageShowRepository
					.findByMobileNo(incomingMessage.getFrom());
			String message = null;

			if (messageType.equalsIgnoreCase("text")) {
				if (textType != null) {
					if (textType.getText() != null) {

//						Customer customer = customerRepository.findByContact(incomingMessage.getFrom());
//						OutgoingActivity outgoingActivity = outgoingActivityRepository
//								.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());
						Customer customer = customerRepository.findByContact(incomingMessage.getFrom());
						OutgoingActivity outgoingActivity = outgoingActivityRepository
								.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());

//						OutgoingActivity outgoingAssigned2 = new OutgoingActivity();
//						if (outgoingActivity != null) {
//							if (outgoingActivity.getIsopen() == false) {
//								outgoingAssigned2.setAssignedto(1);
//								outgoingAssigned2.setFromId(1);
//								outgoingAssigned2.setGroup("You");
//								outgoingAssigned2.setMessage(null);
//								outgoingAssigned2.setMessagetype("incomingopen");
//								outgoingAssigned2.setMobileNo(incomingMessage.getFrom());
//								outgoingAssigned2.setIsopen(true);
//								outgoingAssigned2.setTime(LocalDateTime.now());
//								outgoingAssigned2.setType("Receiver");
//								outgoingActivityService.saveIncomingOpen(outgoingAssigned2);
//							}
//
//						} else {
//							outgoingAssigned2.setAssignedto(1);
//							outgoingAssigned2.setFromId(1);
//							outgoingAssigned2.setGroup("You");
//							outgoingAssigned2.setMessage(null);
//							outgoingAssigned2.setMessagetype("incomingopen");
//							outgoingAssigned2.setMobileNo(incomingMessage.getFrom());
//							outgoingAssigned2.setIsopen(true);
//							outgoingAssigned2.setTime(LocalDateTime.now());
//							outgoingAssigned2.setType("Receiver");
//							outgoingActivityService.saveIncomingOpen(outgoingAssigned2);
//						}

						OutgoingActivity outgoingAssigned2 = new OutgoingActivity();
						if (outgoingActivity != null) {
							if (outgoingActivity.getIsopen() != false) {
								outgoingAssigned2.setId(outgoingActivity.getId());
								outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
								outgoingAssigned2.setFromId(outgoingActivity.getFromId());
								outgoingAssigned2.setGroup("You");
								outgoingAssigned2.setMessage(outgoingActivity.getMessage());
								outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
								outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
								outgoingAssigned2.setIsopen(true);
								outgoingAssigned2.setTime(LocalDateTime.now());
								outgoingAssigned2.setType("Receiver");
								outgoingActivityService.saveIncomingOpen(outgoingAssigned2);
							} else {
								outgoingAssigned2.setAssignedto(1);
								outgoingAssigned2.setFromId(1);
								outgoingAssigned2.setGroup("You");
								outgoingAssigned2.setMessage(null);
								outgoingAssigned2.setMessagetype("incomingopen");
								outgoingAssigned2.setMobileNo(incomingMessage.getFrom());
								outgoingAssigned2.setIsopen(true);
								outgoingAssigned2.setTime(LocalDateTime.now());
								outgoingAssigned2.setType("Receiver");
								outgoingActivityService.saveIncomingOpen(outgoingAssigned2);
							}

						} else {
							outgoingAssigned2.setAssignedto(1);
							outgoingAssigned2.setFromId(1);
							outgoingAssigned2.setGroup("You");
							outgoingAssigned2.setMessage(null);
							outgoingAssigned2.setMessagetype("incomingopen");
							outgoingAssigned2.setMobileNo(incomingMessage.getFrom());
							outgoingAssigned2.setIsopen(true);
							outgoingAssigned2.setTime(LocalDateTime.now());
							outgoingAssigned2.setType("Receiver");
							outgoingActivityService.saveIncomingOpen(outgoingAssigned2);
						}

						String customerName = null;
						if (customer == null) {
							// Create a new customer
							customer = new Customer();
							customer.setContact(incomingMessage.getFrom());
							customer.setStatus(true);
							customerRepository.save(customer);
						} else if (customer.getFirstName() != null) {
							// Customer already exists, retrieve their name
							// customerName = customer.getFirstName() +" "+ customer.getLastName();

							if (customer.getFirstName() != null && customer.getLastName() != null) {
								customerName = customer.getFirstName() + " " + customer.getLastName();
							} else {
								customerName = customer.getFirstName();
							}

							// Set the customer name in the webhook entity
						}

						OutgoingActivity outgoingActivity3 = outgoingActivityRepository
								.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());

						LatestMessageListShow latestMessageListShow1 = latestMessageListShowService
								.findbymobilenumber(incomingMessage.getFrom());

						webhookRepository.save(webhookEntity);
						response.setStatus("success");
						response.setMessage("Text processed and saved successfully");
						IncomingMessageShow incomingMessageShow = new IncomingMessageShow();
						incomingMessageShow.setMessage(textType.getText());

						incomingMessageShow.setTime(incomingMessage.getCreatedDate());
						incomingMessageShow.setMobileNo(incomingMessage.getFrom());
						incomingMessageShow.setType("Receiver");
						incomingMessageShow.setMessagetype("Text");
						incomingMessageShow.setGroup("Reception");
						incomingMessageShow.setName(customerName);

						incomingMessageShow.setFromId(outgoingAssigned2.getFromId());
						incomingMessageShow.setAssignedto(outgoingAssigned2.getAssignedto());
						incomingMessageShow.setIsopen(true);
						incomingMessageShowRepository.save(incomingMessageShow);
						String responseData = mapper.writeValueAsString(incomingMessageShow);

						if (latestMessageListShow1 == null || (outgoingActivity2.getIsopen() == false
								&& outgoingActivity3.getMessagetype().equalsIgnoreCase("incomingopen"))) {

							OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
							outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
							outgoingMessageFormat.setMessage_type("template");
							outgoingMessageFormat.setContent("first template");
							outgoingMessageFormat.setTemplateName("options");
							String[] attribute = { null };
							outgoingMessageFormat.setAttributes(attribute);
							String responseData3 = mapper.writeValueAsString(outgoingMessageFormat);
							outgoingMessageResponseController.createWebhookSentMessage(responseData3);

						}

						chatWebSocketHandler.sendMessageToAllSessions(responseData);

						if (textType.getText().equals("Service Request") && outgoingActivity2.getIsopen() != false) {
							OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
							outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
							outgoingMessageFormat.setMessage_type("text");
							outgoingMessageFormat.setContent("please share your issue or feedback");
							outgoingMessageFormat.setTemplateName(null);
							outgoingMessageFormat.setFormId((long) 1);

							String[] attribute = { null };
							String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
							outgoingMessageResponseController.createWebhookSentMessage(responseData1);
						}
						if (textType.getText().equals("Queries") && outgoingActivity2.getIsopen() != false) {
							customer.setFlag(true);
							customerRepository.save(customer);
							OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
							outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
							outgoingMessageFormat.setMessage_type("text");
							outgoingMessageFormat.setContent("Please mention the query");
							outgoingMessageFormat.setTemplateName(null);
							outgoingMessageFormat.setFormId((long) 1);

							String[] attribute = { null };
							String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
							outgoingMessageResponseController.createWebhookSentMessage(responseData1);
						}
						IncomingMessageShow incomingMessageShows1 = incomingMessageShowRepository
								.findFirstByMobileNoOrderByTimeDesc(incomingMessage.getFrom());
						//sales enquiry
						if (incomingMessageShows != null) {
							message = incomingMessageShows1.getMessage();
							String removedSpacesMessage = message.replaceAll("\\s", "");
							if (removedSpacesMessage.equalsIgnoreCase("SalesEnquiry") && outgoingActivity2.getIsopen() != false) {
								OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
								outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
								outgoingMessageFormat.setMessage_type("text");
								String url="http://13.127.147.41/admin/orders/"+incomingMessage.getFrom();
								
								outgoingMessageFormat.setContent(url);
								outgoingMessageFormat.setTemplateName(null);
								outgoingMessageFormat.setFormId((long) 1);

								String[] attribute = { null };
								String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
								outgoingMessageResponseController.createWebhookSentMessage(responseData1);
							}
						}
						Customer customersUpdateFlag = customerRepository.findByContact(incomingMessage.getFrom());
//						if (incomingMessageShows != null) {
//							message = incomingMessageShows.getMessage();
//							long counts = incomingMessageShow1.size();
//							String removedSpacesMessage = message.replaceAll("\\s", "");
//							System.out.println(removedSpacesMessage);
//							int count = 0;
//							if ((removedSpacesMessage.equalsIgnoreCase("SalesEnquiry") && textType.getText() != null)
//									&& outgoingActivity2.getIsopen() != false) {
//
//								String chatGptResponse = this.ChatGptAPI(textType.getText());
//								// String chatGptResponse=textType.getText();
//
//								OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
//								outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
//								outgoingMessageFormat.setMessage_type("text");
//								outgoingMessageFormat.setContent(chatGptResponse);
//								outgoingMessageFormat.setTemplateName(null);
//								outgoingMessageFormat.setFormId((long) 1);
//
//								String[] attribute = { null };
//								String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
//								outgoingMessageResponseController.createWebhookSentMessage(responseData1);
//
//							}
//						}
						if (customersUpdateFlag.isFlag() == true && outgoingActivity2.getIsopen() != false
								&& textType.getText() != null && !outgoingMessageShow.getMessage().equals("Template")) {

							String chatGptResponse = this.ChatGptAPI(textType.getText());
							OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
							outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
							outgoingMessageFormat.setMessage_type("text");
							outgoingMessageFormat.setContent(chatGptResponse);
							outgoingMessageFormat.setTemplateName(null);
							outgoingMessageFormat.setFormId((long) 1);

							String[] attribute = { null };
							String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
							outgoingMessageResponseController.createWebhookSentMessage(responseData1);
						}

						if (incomingMessageShows != null) {
							message = incomingMessageShows.getMessage();
							long counts = incomingMessageShow1.size();
							String removedSpacesMessage = message.replaceAll("\\s", "");
							System.out.println(removedSpacesMessage);
							int count = 0;
							if ((removedSpacesMessage.equalsIgnoreCase("ServiceRequest") && textType.getText() != null
									&& outgoingActivity2.getIsopen() != false) && count == 0) {
								// TicketCreation ticketCreation=new TicketCreation();
								System.out.println("ticket");
								// count++;
								if (customers.getEmail() != null) {

									LocalDateTime currentDateTime = LocalDateTime.now();
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
									String currentDateTimeString = currentDateTime.format(formatter);
									String ticketNo = "T-" + currentDateTimeString;
									TicketDto ticketCreation = new TicketDto();
									ticketCreation.setEmailId(customers.getEmail());
									ticketCreation.setCategoryId(6);
									ticketCreation.setSubCategoryId(6);
									ticketCreation.setServiceTitleId(6);
									String alternativeContactNoString = incomingMessage.getFrom();
									long alternativeContactNo = Long.parseLong(alternativeContactNoString);
									ticketCreation.setAlternativeContactNo(alternativeContactNo);
									ticketCreation.setPriority(15);
									ticketCreation.setIssueId(13);
									ticketCreation.setDepartmentId(2);
									ticketCreation.setAssignedTo(1);
									ticketCreation.setShortNotes(textType.getText());
									ticketCreation.setComment("Service Request through Chatbot");
									ticketCreation.setCreatedBy(1);
									Customer customerss = customerRepository.findByContact(incomingMessage.getFrom());
									ticketCreation.setCustomerId(customerss.getCustomerId());
									ticketCreation.setStatus(true);

//									TicketDto ticketCreation = new TicketDto();
//									ticketCreation.setEmailId(customers.getEmail());
//									ticketCreation.setCategoryId(94);
//									ticketCreation.setSubCategoryId(1);
//									ticketCreation.setServiceTitleId(1);
//									String alternativeContactNoString = incomingMessage.getFrom();
//									long alternativeContactNo = Long.parseLong(alternativeContactNoString);
//									ticketCreation.setAlternativeContactNo(alternativeContactNo);
//									ticketCreation.setPriority(1);
//									ticketCreation.setIssueId(1);
//									ticketCreation.setDepartmentId(1);
//									ticketCreation.setAssignedTo(1);
//									ticketCreation.setShortNotes(textType.getText());
//									ticketCreation.setComment("Service Request through Chatbot");
//									ticketCreation.setCreatedBy(1);
//									Customer customerss = customerRepository.findByContact(incomingMessage.getFrom());
//									ticketCreation.setCustomerId(customerss.getCustomerId());
//									ticketCreation.setStatus(true);

									String userData = mapper.writeValueAsString(ticketCreation);
									byte[] file = null;
									String str = ticketCreationController.TicketCreationForCustomer(userData,
											customers,ticketNo);
									if (str.equalsIgnoreCase("successfully")) {
										OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
										outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
										outgoingMessageFormat.setMessage_type("text");
										outgoingMessageFormat.setContent(
												"Thank you for your feedback. Ticket number "+ticketNo+" has been raised. Please check your email for further updates");
										outgoingMessageFormat.setTemplateName(null);
										outgoingMessageFormat.setFormId((long) 1);

										String[] attribute = { null };
										String responseData2 = mapper.writeValueAsString(outgoingMessageFormat);
										outgoingMessageResponseController.createWebhookSentMessage(responseData2);
									}
								} else {
									OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
									outgoingMessageFormat.setRecipient_whatsapp(incomingMessage.getFrom());
									outgoingMessageFormat.setMessage_type("text");
									outgoingMessageFormat.setContent("Sorry !! Your email id is not registered");
									outgoingMessageFormat.setTemplateName(null);
									outgoingMessageFormat.setFormId((long) 1);

									String[] attribute = { null };
									String responseData3 = mapper.writeValueAsString(outgoingMessageFormat);
									outgoingMessageResponseController.createWebhookSentMessage(responseData3);
								}

							}
						}
// change status of number is as true						
//						OutgoingActivity outgoingActivity = outgoingActivityRepository
//								.findFirstByMobileNoOrderByTimeDesc(incomingMessageShow.getMobileNo());

//						chatWebSocketHandler.sendMessageToNumber(incomingMessageShow.getMobileNo(), responseData);
//						String str = chatWebSocketHandler.sendMessageToAllSessions(responseData);

						LatestMessageListShow latestMessageListShow = latestMessageListShowService
								.findbymobilenumber(incomingMessage.getFrom());

						if (latestMessageListShow != null) {
							Long id = latestMessageListShow.getId();
							latestMessageListShowService.update(id, incomingMessageShow);
							System.out.println("IncomingListShow found: " + latestMessageListShow);
						} else {
							latestMessageListShowService.save(incomingMessageShow);
							System.out.println("No IncomingListShow found for the given mobile number.");
						}
						return response;
					}
				} else {

					response.setStatus("Error");
					response.setMessage("text can't be null !!!");
					return response;
				}
			}
			if ((messageType.equalsIgnoreCase("image")) || (messageType.equalsIgnoreCase("video"))
					|| (messageType.equalsIgnoreCase("media")) || (messageType.equalsIgnoreCase("audio"))
					|| (messageType.equalsIgnoreCase("document"))) {
				if (imageType != null) {
					if ((imageType.getSha256() != null) && (imageType.getMime_type() != null)
							&& (imageType.getId() != null)) {
						webhookRepository.save(webhookEntity);
						response.setStatus("success");
						response.setMessage("media/audio/video processed and saved successfully");
						return response;
					}
				} else {

					response.setStatus("Error");
					response.setMessage("not getting the all file data");
					return response;
				}
			}
			if (messageType.equalsIgnoreCase("location")) {
				if (location_type != null) {
					Double latitude = location_type.getLatitude();
					Double longitute = location_type.getLongitude();
					if ((location_type.getAddress() != null) && (location_type.getUrl() != null) && (latitude != null)
							&& (location_type.getName() != null) && (longitute != null)) {
						webhookRepository.save(webhookEntity);
						response.setStatus("success");
						response.setMessage("Location processed and saved successfully");
						return response;
					}
				} else {
					response.setStatus("Error");
					response.setMessage("location type wrong");
					return response;
				}
			}
		}
		response.setStatus("error");
		response.setMessage("please enter the valid message type");
		return response;

	}

	public List<WebhookEntity> getAll() {
		return webhookRepository.findAll();
	}

	public String ChatGptAPI(String requestBody) {

		String Body = requestBody;
		String apiURL = "http://65.0.4.50:5000/chatgpt";

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode jsonBody = objectMapper.createObjectNode();
		jsonBody.put("Body", requestBody);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create the request entity with the JSON body and headers
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
				String.class);

		String response = responseEntity.getBody();
		return response;
	}

}
