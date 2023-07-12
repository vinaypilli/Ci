package com.teamcomputers.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.OutgoingTemplateDto;
import com.teamcomputers.dto.OutgoingTemplateDtoWithoutAttribute;
import com.teamcomputers.dto.OutgoingTemplateLanguageDTO;
import com.teamcomputers.dto.OutgoingTemplateMessageDto;
import com.teamcomputers.dto.OutgoingTemplateMessageDtoWithoutTemplate;
import com.teamcomputers.dto.OutgoingTypeTemplateDTO;
import com.teamcomputers.dto.OutgoingTypeTemplateWithoutAttributeDTO;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.OutgoingMessageNotAllowedException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.model.MessageNotes;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.OutgoingData;
import com.teamcomputers.model.OutgoingMessage;
import com.teamcomputers.model.OutgoingMessageData;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.model.OutgoingMessageResponce;
import com.teamcomputers.model.OutgoingMessageShow;
import com.teamcomputers.model.OutgoingStatus;
import com.teamcomputers.model.OutgoingType_text;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;
import com.teamcomputers.repository.OutgoingMessageShowRepository;
import com.teamcomputers.service.CustomerService;
import com.teamcomputers.service.LatestMessageListShowService;
import com.teamcomputers.service.MessageNotesService;
import com.teamcomputers.service.OutgoingActivityService;
import com.teamcomputers.service.OutgoingMessageResponceService;
import com.teamcomputers.service.OutgoingMessageShowService;
import com.teamcomputers.websocket.ChatWebSocketComponent;
import com.teamcomputers.websocket.ChatWebSocketHandler;

@RestController
@RequestMapping("/outgoing-message")
public class OutgoingMessageResponseController <T>{

	@Autowired
	private ChatWebSocketComponent chatWebSocketHandler;

	@Autowired
	private OutgoingMessageResponceService outgoingMessageResponceService;

	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@Autowired
	private OutgoingMessageShowRepository outgoingMessageShowRepository;
	
	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MessageNotesService messageNotesService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OutgoingMessageShowService outgoingMessageShowService;
	
	@Autowired
	private OutgoingActivityService outgoingActivityService;

	@Autowired
	private ObjectMapper mapper;

	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<?> createWebhookSentMessage(@RequestBody String formate) throws IOException {

		
		
	//	try {
			OutgoingMessageFormat outgoingMessageFormat = mapper.readValue(formate, OutgoingMessageFormat.class);

			OutgoingMessageShow outgoingMessageShow = null;

			String fullname = null;

			Customer customer = customerRepository.findByContact(outgoingMessageFormat.getRecipient_whatsapp());
			if (customer == null) {
				throw new ResourceNotFoundException(
						"Mobile number does not exist: " + outgoingMessageFormat.getRecipient_whatsapp());
			} else if (customer.getFirstName() != null) {
				if (customer.getFirstName() != null && customer.getLastName() != null) {
					fullname = customer.getFirstName() + " " + customer.getLastName();
				} else {
					fullname = customer.getFirstName();
				}
			}

			if (outgoingMessageFormat.getMessage_type().equalsIgnoreCase("text")) {
				LatestMessageListShow latestMessageListShow1 = latestMessageListShowService
						.findbymobilenumber(outgoingMessageFormat.getRecipient_whatsapp());

				if (latestMessageListShow1 != null) {
					LocalDateTime latestMessageTime = latestMessageListShow1.getTime();
					LocalDateTime currentDateTime = LocalDateTime.now();
					LocalDateTime twentyFourHoursAgo = currentDateTime.minusHours(24);

					if (!latestMessageTime.isAfter(twentyFourHoursAgo)) {
						throw new OutgoingMessageNotAllowedException(
								"Outgoing message not allowed. Latest message not within the last 24 hours.");
					}
				}

				OutgoingMessage outgoingMessage = new OutgoingMessage();
				OutgoingMessageData msd = new OutgoingMessageData();

				msd.setMessage_type(outgoingMessageFormat.getMessage_type());
				msd.setRecipient_whatsapp(outgoingMessageFormat.getRecipient_whatsapp());
				msd.setRecipient_type("individual");
				msd.setSource("94fab010fa989469a2d25d91790e58f1");
				msd.setX_apiheader("custom_data");

				OutgoingType_text outgoingType_text = new OutgoingType_text();
				outgoingType_text.setPreview_url("false");
				outgoingType_text.setContent(outgoingMessageFormat.getContent());

				List<OutgoingType_text> text = new ArrayList<>();
				text.add(outgoingType_text);
				msd.setType_text(text);

				List<OutgoingMessageData> outmessage = new ArrayList<>();
				outmessage.add(msd);
				outgoingMessage.setMessage(outmessage);
				String apiURL = "https://waapi.pepipost.com/api/v2/message/";

				// Set the headers
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// Add the JWT token to the headers
				String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFtY29tcHV0ZXJzd2EiLCJleHAiOjI1NDg5OTcwMjZ9.vojnkMuJT1bSmSYIjFGdH7hdLaPaFOQsvFtLgmnTVyZg8V9Nw6c5vJD-O8NWndvCdb5d44KH7siJ_wvtYhtVVg";
				headers.set("Authorization", "Bearer " + jwtToken);

				// Create the request entity with the outgoingMessage object as the body and
				// headers
				HttpEntity<OutgoingMessage> requestEntity = new HttpEntity<>(outgoingMessage, headers);

				// Send the POST request to the Netcore API
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
						String.class);

				// Retrieve the response body
				String response = responseEntity.getBody();

				OutgoingStatus outgoingStatus = null;
				OutgoingData outgoingData = null;

				// Process the response or return it as-is
				if (response != null) {
					JsonNode jsonNode = mapper.readTree(response);
					JsonNode dataNode = jsonNode.get("data");
					outgoingStatus = mapper.readValue(response, OutgoingStatus.class);

					// Deserialize the data field
					outgoingData = mapper.treeToValue(dataNode, OutgoingData.class);
					OutgoingMessage outMessage = outgoingMessage;
					List<OutgoingMessageData> md = outMessage.getMessage();
					OutgoingMessageData outgoingMessageData = md.get(0);

					OutgoingActivity outgoingAssigned3=outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(outgoingMessageFormat.getRecipient_whatsapp());
//					System.out.println(outgoingAssigned3);
					if (outgoingAssigned3.getIsopen() == false) {
						
						OutgoingActivity outgoingAssigned2 = new OutgoingActivity();
						outgoingAssigned2.setAssignedto(outgoingMessageFormat.getFormId());
						outgoingAssigned2.setFromId(outgoingMessageFormat.getFormId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(null);
						outgoingAssigned2.setMessagetype("open");
						outgoingAssigned2.setMobileNo(outgoingMessageFormat.getRecipient_whatsapp());
						outgoingAssigned2.setIsopen(true);
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");
						
						outgoingActivityService.saveOutgoingOpen(outgoingAssigned2);
					}
					
					
					OutgoingMessageResponce outgoingMessageResponce = outgoingMessageResponceService
							.saveOutgoingMessage(outgoingMessageData, outgoingStatus, outgoingData);
					outgoingMessageShow = outgoingMessageShowService.saveOutgoingMessage(outgoingMessageResponce,
							outgoingMessageFormat);			
					

				}
//				OutgoingActivity outgoingAssigned3=outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(outgoingMessageFormat.getRecipient_whatsapp());
//				
//				String rsp=mapper.writeValueAsString(outgoingAssigned3);
				String responseData = mapper.writeValueAsString(outgoingMessageShow);
//				chatWebSocketHandler.sendMessageToNumber(outgoingMessageShow.getMobileNo(), responseData);
				chatWebSocketHandler.sendMessageToAllSessions(responseData);
				LatestMessageListShow latestMessageListShow = latestMessageListShowService
						.findbymobilenumber(outgoingMessageShow.getMobileNo());

				if (latestMessageListShow != null) {
					Long id = latestMessageListShow.getId();
					latestMessageListShowService.update(id, outgoingMessageShow, fullname);
					System.out.println("IncomingListShow found: " + latestMessageListShow);
				} else {
					latestMessageListShowService.save(outgoingMessageShow, fullname);
					System.out.println("No IncomingListShow found for the given mobile number.");
				}

				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);

// notes				

			} else if (outgoingMessageFormat.getMessage_type().equalsIgnoreCase("notes")) {

				MessageNotes messageNotes = messageNotesService.save(outgoingMessageFormat,fullname);
				outgoingMessageShow = outgoingMessageShowService.saveOutgoingNotes(messageNotes, outgoingMessageFormat);
				String responseData = mapper.writeValueAsString(messageNotes);
//				chatWebSocketHandler.sendMessageToNumber(messageNotes.getMobileNo(), responseData);
				chatWebSocketHandler.sendMessageToAllSessions(responseData);
				LatestMessageListShow latestMessageListShow = latestMessageListShowService
						.findbymobilenumber(messageNotes.getMobileNo());

				if (latestMessageListShow != null) {
					Long id = latestMessageListShow.getId();
					latestMessageListShowService.update(id, messageNotes, fullname);
					System.out.println("IncomingListShow found: " + latestMessageListShow);
				} else {
					latestMessageListShowService.save(messageNotes, fullname);
					System.out.println("No IncomingListShow found for the given mobile number.");
				}

				ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
						outgoingMessageShow.getMessage(), ErrorCode.Success.getMessage());
				return ResponseEntity.status(HttpStatus.OK).body(response);
				
//				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(outgoingMessageShow.getMessage());
//				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("notes ");

// template				
			} else if (outgoingMessageFormat.getMessage_type().equalsIgnoreCase("template")) {
				String[] attributeArray =null;
				try {
				    ObjectMapper mapper = new ObjectMapper();
				    JsonNode jsonNode = mapper.readTree(formate);
				    JsonNode attributeNode = jsonNode.get("attribute");

				    if (attributeNode != null) {
				        attributeArray = mapper.convertValue(attributeNode, String[].class);

				        for (String attributeValue : attributeArray) {
				            System.out.println("Attribute value: " + attributeValue);
				        }
				    } else {
				        System.out.println("Attribute is null");
				    }
				} catch (Exception e) {
				    e.printStackTrace();
				}

				if(attributeArray!=null) {
				OutgoingTemplateDto outgoingTemplateDto = new OutgoingTemplateDto();
				OutgoingTemplateMessageDto outgoingTemplateMessageDto = new OutgoingTemplateMessageDto();

				outgoingTemplateMessageDto.setRecipient_whatsapp(outgoingMessageFormat.getRecipient_whatsapp());
				outgoingTemplateMessageDto.setMessage_type(outgoingMessageFormat.getMessage_type());
				outgoingTemplateMessageDto.setRecipient_type("individual");

				OutgoingTypeTemplateDTO outgoingTypeTemplateDTO = new OutgoingTypeTemplateDTO();
				outgoingTypeTemplateDTO.setName(outgoingMessageFormat.getTemplateName());
				String[] attributes = outgoingMessageFormat.getAttributes();
				outgoingTypeTemplateDTO.setAttributes(attributeArray);

				OutgoingTemplateLanguageDTO outgoingTemplateLanguageDTO = new OutgoingTemplateLanguageDTO();
				outgoingTemplateLanguageDTO.setLocale("en");
				outgoingTemplateLanguageDTO.setPolicy("deterministic");

				outgoingTypeTemplateDTO.setLanguage(outgoingTemplateLanguageDTO);

				List<OutgoingTypeTemplateDTO> outgoingTypeTemplateDTOs = new ArrayList<>();
				outgoingTypeTemplateDTOs.add(outgoingTypeTemplateDTO);
				outgoingTemplateMessageDto.setType_template(outgoingTypeTemplateDTOs);

				List<OutgoingTemplateMessageDto> outgoingTemplateMessageDtos = new ArrayList<>();
				outgoingTemplateMessageDtos.add(outgoingTemplateMessageDto);

				outgoingTemplateDto.setMessage(outgoingTemplateMessageDtos);

				String apiURL = "https://waapi.pepipost.com/api/v2/message/";

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// Add the JWT token to the headers
				String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFtY29tcHV0ZXJzd2EiLCJleHAiOjI1NDg5OTcwMjZ9.vojnkMuJT1bSmSYIjFGdH7hdLaPaFOQsvFtLgmnTVyZg8V9Nw6c5vJD-O8NWndvCdb5d44KH7siJ_wvtYhtVVg";
				headers.set("Authorization", "Bearer " + jwtToken);

				// Create the request entity with the outgoingMessage object as the body and
				// headers
				HttpEntity<OutgoingTemplateDto> requestEntity = new HttpEntity<>(outgoingTemplateDto, headers);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
						String.class);

				OutgoingStatus outgoingStatus = null;
				OutgoingData outgoingData = null;

				String response = responseEntity.getBody();

				if (response != null) {
					JsonNode jsonNode = mapper.readTree(response);
					JsonNode dataNode = jsonNode.get("data");
					outgoingStatus = mapper.readValue(response, OutgoingStatus.class);

					outgoingData = mapper.treeToValue(dataNode, OutgoingData.class);

					List<OutgoingTemplateMessageDto> md = outgoingTemplateDto.getMessage();
					OutgoingTemplateMessageDto outgoingMessageData = md.get(0);

					OutgoingMessageResponce outgoingMessageResponce = outgoingMessageResponceService
							.saveOutgoingMessage(outgoingMessageData, outgoingStatus, outgoingData);

					outgoingMessageShow = outgoingMessageShowService.savetemplate(outgoingMessageResponce);
				}

//				ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//						outgoingMessageShow.getMessage(), ErrorCode.Success.getMessage());
//				return ResponseEntity.status(HttpStatus.OK).body(response1);
				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
			}else if(attributeArray==null){
				OutgoingTemplateDtoWithoutAttribute outgoingTemplateDto = new OutgoingTemplateDtoWithoutAttribute();
				OutgoingTemplateMessageDtoWithoutTemplate outgoingTemplateMessageDto = new OutgoingTemplateMessageDtoWithoutTemplate();

				outgoingTemplateMessageDto.setRecipient_whatsapp(outgoingMessageFormat.getRecipient_whatsapp());
				outgoingTemplateMessageDto.setMessage_type(outgoingMessageFormat.getMessage_type());
				outgoingTemplateMessageDto.setRecipient_type("individual");

				OutgoingTypeTemplateWithoutAttributeDTO outgoingTypeTemplateDTO = new OutgoingTypeTemplateWithoutAttributeDTO();
				outgoingTypeTemplateDTO.setName(outgoingMessageFormat.getTemplateName());
				

				OutgoingTemplateLanguageDTO outgoingTemplateLanguageDTO = new OutgoingTemplateLanguageDTO();
				outgoingTemplateLanguageDTO.setLocale("en");
				outgoingTemplateLanguageDTO.setPolicy("deterministic");

				outgoingTypeTemplateDTO.setLanguage(outgoingTemplateLanguageDTO);

				List<OutgoingTypeTemplateWithoutAttributeDTO> outgoingTypeTemplateDTOs = new ArrayList<>();
				outgoingTypeTemplateDTOs.add(outgoingTypeTemplateDTO);
				outgoingTemplateMessageDto.setType_template(outgoingTypeTemplateDTOs);

				List<OutgoingTemplateMessageDtoWithoutTemplate> outgoingTemplateMessageDtos = new ArrayList<>();
				outgoingTemplateMessageDtos.add(outgoingTemplateMessageDto);

				outgoingTemplateDto.setMessage(outgoingTemplateMessageDtos);

				String apiURL = "https://waapi.pepipost.com/api/v2/message/";

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// Add the JWT token to the headers
				String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFtY29tcHV0ZXJzd2EiLCJleHAiOjI1NDg5OTcwMjZ9.vojnkMuJT1bSmSYIjFGdH7hdLaPaFOQsvFtLgmnTVyZg8V9Nw6c5vJD-O8NWndvCdb5d44KH7siJ_wvtYhtVVg";
				headers.set("Authorization", "Bearer " + jwtToken);

				// Create the request entity with the outgoingMessage object as the body and
				// headers
				HttpEntity<OutgoingTemplateDtoWithoutAttribute> requestEntity = new HttpEntity<>(outgoingTemplateDto, headers);

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
						String.class);

				OutgoingStatus outgoingStatus = null;
				OutgoingData outgoingData = null;

				String response = responseEntity.getBody();

				if (response != null) {
					JsonNode jsonNode = mapper.readTree(response);
					JsonNode dataNode = jsonNode.get("data");
					outgoingStatus = mapper.readValue(response, OutgoingStatus.class);

					outgoingData = mapper.treeToValue(dataNode, OutgoingData.class);

					List<OutgoingTemplateMessageDtoWithoutTemplate> md = outgoingTemplateDto.getMessage();
					OutgoingTemplateMessageDtoWithoutTemplate outgoingMessageData = md.get(0);

					OutgoingMessageResponce outgoingMessageResponce = outgoingMessageResponceService
							.saveOutgoingMessage(outgoingMessageData, outgoingStatus, outgoingData);

					outgoingMessageShow = outgoingMessageShowService.savetemplate(outgoingMessageResponce);
				}

				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
			}else {
				ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
						outgoingMessageShow.getMessage(), ErrorCode.Success.getMessage());
				return ResponseEntity.status(HttpStatus.OK).body(response1);
			}
			} else {
				// Handle the case when the response is null
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Failed to process the webhook message");
			}
//		} catch (ResourceNotFoundException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//
//		catch (OutgoingMessageNotAllowedException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		} catch (IOException e) {
//			// Handle JSON parsing or IO exceptions
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("An error occurred during request processing");
//		} catch (Exception e) {
//			// Handle other exceptions
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("An error occurred during request processing");
//		}
			
			
	
	}
}
