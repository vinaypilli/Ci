package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.AutomatedMessageDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.AutomatedMessages;
import com.teamcomputers.repository.AutomatedMessagesRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class AutomatedMessagesService {

	@Autowired
	private AutomatedMessagesRepository automatedMessagesRepository;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> add(AutomatedMessageDto automatedMessageDto) {
//		Company userDup = companyRepository.findByCompanyName(companyDto.getCompanyName());
//		if (userDup != null) {
//			throw new DuplicateUserException("Company name already exists");
//		}
//
//		Company apiKeyDup = companyRepository.findByApiKey(companyDto.getApiKey());
//		if (apiKeyDup != null) {
//			throw new DuplicateUserException("API key already exists");
//		}


		AutomatedMessages automatedMessages = new AutomatedMessages();

		automatedMessages.setReceivedMessage(automatedMessageDto.getReceivedMessage());
		automatedMessages.setResponseMessage(automatedMessageDto.getResponseMessage());
		automatedMessages.setWorkflowId(automatedMessageDto.getWorkflowId());
		automatedMessages.setCreatedBy(userRepository.findById((int) (automatedMessageDto.getCreatedBy())).orElse(null));
		automatedMessages.setCreatedDate(automatedMessageDto.getCreatedDate());
		automatedMessages.setUpdatedBy(userRepository.findById((int) (automatedMessageDto.getUpdatedBy())).orElse(null));
		automatedMessages.setUpdatedDate(automatedMessageDto.getUpdatedDate());
		automatedMessages.setStatus(automatedMessageDto.isStatus());

		automatedMessagesRepository.save(automatedMessages);
//		 message = "request successful" ;
//         status=ErrorCode.Success.getMessage();
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> getById(long automatedMessagesId) {
		AutomatedMessages automatedMessages = automatedMessagesRepository.findById(automatedMessagesId)
				.orElseThrow(() -> new ResourceNotFoundException("Automated Messages Id : " + automatedMessagesId + " Unavailable"));
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(automatedMessages);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	public ResponseEntity<ErrorResponse> update(AutomatedMessageDto automatedMessageDto) {
		
//		Optional<Company> userDup2 = companyRepository.findById(companyDto.getCompanyId());
//		if (!userDup2.isPresent()) {
//		    throw new ResourceNotFoundException("Id does not exist");
//		}
//		
//		Company userDup = companyRepository.findByCompanyNameAndCompanyIdNot(companyDto.getCompanyName(), companyDto.getCompanyId());
//	    if (userDup != null) {
//	        throw new ConflictException("Company name already exists");
//	    }
//	    Company userDup1 = companyRepository.findByApiKeyAndCompanyIdNot(companyDto.getApiKey(), companyDto.getCompanyId());
//	    if (userDup1 != null) {
//	        throw new DuplicateUserException("API key already exists");
//	    }
		
		AutomatedMessages automatedMessages = new AutomatedMessages();

		automatedMessages.setAutomatedMessagesId(automatedMessageDto.getAutomatedMessagesId());
		automatedMessages.setReceivedMessage(automatedMessageDto.getReceivedMessage());
		automatedMessages.setResponseMessage(automatedMessageDto.getResponseMessage());
		automatedMessages.setWorkflowId(automatedMessageDto.getWorkflowId());
		automatedMessages.setCreatedBy(userRepository.findById((int) (automatedMessageDto.getCreatedBy())).orElse(null));
		automatedMessages.setUpdatedBy(userRepository.findById((int) (automatedMessageDto.getUpdatedBy())).orElse(null));
		automatedMessages.setUpdatedDate(automatedMessageDto.getUpdatedDate());
		automatedMessages.setStatus(automatedMessageDto.isStatus());
		automatedMessagesRepository.save(automatedMessages);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<ApiResponseFormat> getAll() {
		
	    List<AutomatedMessages> automatedMessages=automatedMessagesRepository.findAll();
	    ApiResponseFormat response = new ApiResponseFormat();
	    response.setStatus("success");
	    response.setCode(HttpStatus.OK.value());
	    response.setMessage("Request successful");
	    response.setData(automatedMessages);
	    
	    if(automatedMessages.isEmpty()) {
			response.setMessage("Data Not Found");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}



	public ResponseEntity<ErrorResponse> deleteById(long automatedMessagesId) throws NotFoundException {
		AutomatedMessages automatedMessages = automatedMessagesRepository.findById(automatedMessagesId).orElseThrow(
				() -> new ResourceNotFoundException("Automated Messages Id : " + automatedMessagesId + " is Not Present in DataBase"));
		if(automatedMessages.isStatus()==true) {
			automatedMessages.setStatus(false); // Update status to false
			automatedMessagesRepository.save(automatedMessages);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request Deleted successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			throw new ResourceNotFoundException("Data not found from this Id");
		}
	}
}
