//package com.teamcomputers.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.teamcomputers.dto.commentTicketsDto;
//import com.teamcomputers.model.Data;
//import com.teamcomputers.model.MessageData;
//import com.teamcomputers.model.MessageResponce;
//import com.teamcomputers.model.OutgoingMessage;
//import com.teamcomputers.model.OutgoingStatus;
//import com.teamcomputers.model.TicketCreation;
//import com.teamcomputers.model.Type_text;
//import com.teamcomputers.repository.MessageDataRepository;
//import com.teamcomputers.service.MessageDataService;
//import com.teamcomputers.service.MessageResponceService;
//import com.teamcomputers.service.OutgoingMessageService;
//import com.teamcomputers.service.OutgoingStatusService;
//
//import com.fasterxml.jackson.databind.JsonNode;
//
//@RestController
//@RequestMapping("/webhooksent")
//public class OutgoingMessageController {
//
//	@Autowired
//	private OutgoingMessageService outgoingMessageService;
////	
////	@Autowired
////	private OutgoingStatusService outgoingStatusService; 
//
//	@Autowired
//	private MessageDataService messageDataService;
//	
//	@Autowired
//	private MessageResponceService messageResponceService;
//
//	@Autowired
//	private ObjectMapper mapper;
//
//	@PostMapping
//	public ResponseEntity<String> createWebhookSentMessage(@RequestBody OutgoingMessage outgoingMessage)
//			throws JsonMappingException, JsonProcessingException {
//		String apiURL = "https://waapi.pepipost.com/api/v2/message/";
//
//		// Set the headers
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		// Add the JWT token to the headers
//		String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZXRjb3Jlc2FsZXNleHAiLCJleHAiOjI0MjUxMDI1MjZ9.ljC4Tvgz031i6DsKr2ILgCJsc9C_hxdo2Kw8iZp9tsVcCaKbIOXaFoXmpU7Yo7ob4P6fBtNtdNBQv_NSMq_Q8w";
//		headers.set("Authorization", "Bearer " + jwtToken);
//
//		// Create the request entity with the outgoingMessage object as the body and
//		// headers
//		HttpEntity<OutgoingMessage> requestEntity = new HttpEntity<>(outgoingMessage, headers);
//
//		// Send the POST request to the Netcore API
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity,
//				String.class);
//
//		// Retrieve the response body
//		String response = responseEntity.getBody();
//
//		OutgoingStatus os = null;
//		Data data = null;
//		// Process the response or return it as-is
//		if (response != null) {
//			// Handle the response as needed
//
//			// String response = responseEntity.getBody();
//			JsonNode jsonNode = mapper.readTree(response);
//			JsonNode dataNode = jsonNode.get("data");
//			os = mapper.readValue(response, OutgoingStatus.class);
//			// Deserialize the data field
//			data = mapper.treeToValue(dataNode, Data.class);
//
////	        return ResponseEntity.status(HttpStatus.OK).body(response);
//	    OutgoingMessage outMessage=	outgoingMessage;
//	   List<MessageData>  md= outMessage.getMessage();
//	   MessageData messageData= md.get(0);
//	  
//	   messageResponceService.saveOutgoingMessage(messageData, os, data);
////	   Long mid= m.getmId();
////	   
////	   MessageData ms=this.getById(mid);
////	    outgoingStatusService.save(tickets, ms,s);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//
//		} else {
//			// Handle the case when the response is null
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Failed to process the webhook message");
//		}
//	}
//
//	@GetMapping("/{messageId}")
//	public ResponseEntity<OutgoingMessage> getWebhookSentMessage(@PathVariable long messageId) {
//		Optional<OutgoingMessage> optionalWebhookSentMessage = outgoingMessageService
//				.getWebhookSentMessageById(messageId);
//		if (optionalWebhookSentMessage.isPresent()) {
//			OutgoingMessage messageData = optionalWebhookSentMessage.get();
//			return ResponseEntity.ok(messageData);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@GetMapping
//	private List<MessageResponce> getAlls() {
//		return messageResponceService.getAll();
//	}
//	
////	@GetMapping
////	private List<Message> getAll() {
////		return messageDataService.getAll();
////	}
//
////	@GetMapping("/{mid}")
////	public MessageData getBymId(@PathVariable long mid) {
////		return messageDataService.getById(mid);
////	}
//
//	@GetMapping("/{mid}")
//	private MessageData getById(@PathVariable Long mid) {
//		return messageDataService.getById(mid);
//	}
//
//	@GetMapping("/webhooksent/{mid}")
//	public ResponseEntity<MessageData> getWebhookSent(@PathVariable long mid) {
//		MessageData messageData = messageDataService.getById(mid);
//		return ResponseEntity.ok(messageData);
//	}
//
//	@GetMapping("/webhookid/{id}")
//	public Optional<OutgoingMessage> getById(@PathVariable long id) {
//		return outgoingMessageService.getWebhookSentMessageById(id);
//
//	}
//
////
////	@DeleteMapping("/{messageId}")
////	public ResponseEntity<Void> deleteWebhookSentMessage(@PathVariable long messageId) {
////		Optional<MessageData> optionalWebhookSentMessage = outgoingMessageService.getWebhookSentMessageById(messageId);
////		if (optionalWebhookSentMessage.isPresent()) {
////			MessageData messageData = optionalWebhookSentMessage.get();
////			outgoingMessageService.deleteWebhookSentMessage(messageData);
////			return ResponseEntity.noContent().build();
////		} else {
////			return ResponseEntity.notFound().build();
////		}
////	}
//
//	// Add other controller methods as required
//}
