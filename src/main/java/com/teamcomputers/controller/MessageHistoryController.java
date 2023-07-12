package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.LatestMessageListShowResponseDTO;
import com.teamcomputers.dto.MessageHistory;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.repository.LatestMessageListShowRepository;
import com.teamcomputers.service.LatestMessageListShowService;
import com.teamcomputers.service.MessageHistoryService;

@RestController
@RequestMapping("/message-history")
public class MessageHistoryController {

	@Autowired
	private MessageHistoryService messageHistoryService;

	private LatestMessageListShowRepository latestMessageListShowRepository;

	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@GetMapping("/number/{mobilenumber}/assignedto/{assignedto}")
	public List<MessageHistory> getAllActiveSubcategoriesByCategoryId(@PathVariable String mobilenumber,@PathVariable long assignedto) {
		return messageHistoryService.mergeMessageHistories(mobilenumber,assignedto);
	}

	@GetMapping("/number/{mobilenumber}")
	public List<MessageHistory> getAllActiveSubcategoriesByCategoryId(@PathVariable String mobilenumber) {
		return messageHistoryService.mergeMessageHistories(mobilenumber);
	}
	
	@GetMapping("contact-list")
	public List<LatestMessageListShow> getAll() {
		return latestMessageListShowService.findAll();
	}

	@GetMapping("/latest-messages")
	public List<LatestMessageListShowResponseDTO> getLatestMessages() {
		return latestMessageListShowService.findAllisopen();
	}
	
	@GetMapping("/latest-messages/assignedto/{assignedto}")
	public List<LatestMessageListShowResponseDTO> getLatestMessagess(@PathVariable long assignedto) {
		return latestMessageListShowService.findAllisopens(assignedto);
	}

//    @GetMapping("/latest-messages")
//    public ResponseEntity<LatestMessageListShowResponseDTO> getLatestMessages() {
//        LatestMessageListShowResponseDTO responseDTO = new LatestMessageListShowResponseDTO();
//
//        // Retrieve the open and closed messages from the database
//        List<LatestMessageListShow> openMessages = latestMessageListShowRepository.findByIsOpen(true);
//        List<LatestMessageListShow> closedMessages = latestMessageListShowRepository.findByIsOpen(false);
//
//        // Set the counts
//        responseDTO.setOpenCount(openMessages.size());
//        responseDTO.setClosedCount(closedMessages.size());
//        responseDTO.setTotalCount(openMessages.size() + closedMessages.size());
//
//        // Convert open messages to DTOs
//        List<LatestMessageListShowResponseDTO.LatestMessageDTO> openMessageDTOs = new ArrayList<>();
//        for (LatestMessageListShow openMessage : openMessages) {
//            LatestMessageListShowResponseDTO.LatestMessageDTO openMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
//            openMessageDTO.setId(openMessage.getId());
//            openMessageDTO.setPhoneNo(openMessage.getMobileNo());
//            openMessageDTO.setFullName(openMessage.getFullName());
//            openMessageDTO.setTime(openMessage.getTime().toString());
//            openMessageDTO.setLastMsg(openMessage.getLastMessage());
//            openMessageDTO.setCount(openMessage.getCount());
//            openMessageDTOs.add(openMessageDTO);
//        }
//
//        // Convert closed messages to DTOs
//        List<LatestMessageListShowResponseDTO.LatestMessageDTO> closedMessageDTOs = new ArrayList<>();
//        for (LatestMessageListShow closedMessage : closedMessages) {
//            LatestMessageListShowResponseDTO.LatestMessageDTO closedMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
//            closedMessageDTO.setId(closedMessage.getId());
//            closedMessageDTO.setPhoneNo(closedMessage.getMobileNo());
//            closedMessageDTO.setFullName(closedMessage.getFullName());
//            closedMessageDTO.setTime(closedMessage.getTime().toString());
//            closedMessageDTO.setLastMsg(closedMessage.getLastMessage());
//            closedMessageDTOs.add(closedMessageDTO);
//        }
//
//        // Set the open and closed messages in the response DTO
//        responseDTO.setOpen(openMessageDTOs);
//        responseDTO.setClosed(closedMessageDTOs);
//
//        return ResponseEntity.ok(responseDTO);
//    }
//}

}
