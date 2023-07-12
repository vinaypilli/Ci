package com.teamcomputers.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.LatestMessageListShowResponseDTO;
import com.teamcomputers.model.IncomingMessageShow;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.model.MessageNotes;
import com.teamcomputers.model.OutgoingMessageShow;
import com.teamcomputers.repository.LatestMessageListShowRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class LatestMessageListShowService {

	@Autowired
	private LatestMessageListShowRepository latestMessageListShowRepository;

	@Autowired
	private UserRepository userRepository;
	
	//for save incomming
	
	public String save(IncomingMessageShow incomingMessageShow) {
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setLastMessage(incomingMessageShow.getMessage());
		latestMessageListShow.setTime(incomingMessageShow.getTime());
		latestMessageListShow.setMobileNo(incomingMessageShow.getMobileNo());
		latestMessageListShow.setMessagetype(incomingMessageShow.getMessagetype());
		latestMessageListShow.setFullName(incomingMessageShow.getName());
		latestMessageListShow.setIsopen(incomingMessageShow.getIsopen());
		latestMessageListShow.setAssignedto(incomingMessageShow.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	
	
	//for save outgoing

	public String save(OutgoingMessageShow outgoingMessageShow,String fullname) {
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setLastMessage(outgoingMessageShow.getMessage());
		latestMessageListShow.setTime(outgoingMessageShow.getTime());
		latestMessageListShow.setMessagetype(outgoingMessageShow.getMessagetype());
		latestMessageListShow.setMobileNo(outgoingMessageShow.getMobileNo());
		latestMessageListShow.setFullName(outgoingMessageShow.getName());
		latestMessageListShow.setIsopen(true);
		latestMessageListShow.setAssignedto(outgoingMessageShow.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	
	
	public void updatename(String mobileno, String fullname) {
		
		LatestMessageListShow latestMessageListShow1=latestMessageListShowRepository.findByMobileNo(mobileno);
//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(latestMessageListShow1.getId());
		latestMessageListShow.setLastMessage(latestMessageListShow1.getLastMessage());
		latestMessageListShow.setMessagetype(latestMessageListShow1.getMessagetype());
		latestMessageListShow.setTime(LocalDateTime.now());
		latestMessageListShow.setMobileNo(latestMessageListShow1.getMobileNo());
		latestMessageListShow.setFullName(fullname);
		latestMessageListShow.setIsopen(latestMessageListShow1.isIsopen());
		latestMessageListShow.setAssignedto(latestMessageListShow1.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);		
	}
	
	
	
	//for update name from customer
	
	public void updateAssignedto(String mobileno,long assignedto) {

		LatestMessageListShow latestMessageListShow1=latestMessageListShowRepository.findByMobileNo(mobileno);
//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(latestMessageListShow1.getId());
		latestMessageListShow.setLastMessage(latestMessageListShow1.getLastMessage());
		latestMessageListShow.setMessagetype(latestMessageListShow1.getMessagetype());
		latestMessageListShow.setTime(latestMessageListShow1.getTime());
		latestMessageListShow.setMobileNo(latestMessageListShow1.getMobileNo());
		latestMessageListShow.setFullName(latestMessageListShow1.getFullName());
		latestMessageListShow.setIsopen(latestMessageListShow1.isIsopen());
		latestMessageListShow.setAssignedto(assignedto);
		latestMessageListShowRepository.save(latestMessageListShow);		
	}

	//for update incomingmessage

	public String update(Long id, IncomingMessageShow incomingMessageShow) {

//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(id);
		latestMessageListShow.setLastMessage(incomingMessageShow.getMessage());
		latestMessageListShow.setMessagetype(incomingMessageShow.getMessagetype());
		latestMessageListShow.setTime(incomingMessageShow.getTime());
		latestMessageListShow.setMessagetype(incomingMessageShow.getMessagetype());
		latestMessageListShow.setMobileNo(incomingMessageShow.getMobileNo());
		latestMessageListShow.setFullName(incomingMessageShow.getName());
		latestMessageListShow.setIsopen(incomingMessageShow.getIsopen());
		latestMessageListShow.setAssignedto(incomingMessageShow.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	
	public String updateclosed(LatestMessageListShow latestMessageListShow1,LocalDateTime localDateTime) {

//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(latestMessageListShow1.getId());
		latestMessageListShow.setLastMessage(latestMessageListShow1.getLastMessage());
		latestMessageListShow.setTime(localDateTime);
		latestMessageListShow.setMessagetype(latestMessageListShow1.getMessagetype());
		latestMessageListShow.setMobileNo(latestMessageListShow1.getMobileNo());
		latestMessageListShow.setFullName(latestMessageListShow1.getFullName());
		latestMessageListShow.setAssignedto(latestMessageListShow1.getAssignedto());
		latestMessageListShow.setIsopen(false);
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	
	public String updateclosed(LatestMessageListShow latestMessageListShow1,LocalDateTime localDateTime,boolean openclosed) {

//		IncomingListShow upls=incomingListShowRepository.findByMobilenumber(mobilenumber);
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(latestMessageListShow1.getId());
		latestMessageListShow.setLastMessage(latestMessageListShow1.getLastMessage());
		latestMessageListShow.setTime(localDateTime);
		latestMessageListShow.setMessagetype(latestMessageListShow1.getMessagetype());
		latestMessageListShow.setMobileNo(latestMessageListShow1.getMobileNo());
		latestMessageListShow.setFullName(latestMessageListShow1.getFullName());
		latestMessageListShow.setIsopen(openclosed);
		latestMessageListShow.setAssignedto(latestMessageListShow1.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	

	

	//for update outgoing
	
	public String update(Long id, OutgoingMessageShow outgoingMessageShow,String fullname) {

//		LatestMessageListShow upls = latestMessageListShowRepository
//				.findByMobilenumber(outgoingMessageShow.getMobileNo());
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		
		latestMessageListShow.setId(id);
		latestMessageListShow.setLastMessage(outgoingMessageShow.getMessage());
		latestMessageListShow.setTime(outgoingMessageShow.getTime());
		latestMessageListShow.setMobileNo(outgoingMessageShow.getMobileNo());
		latestMessageListShow.setFullName(fullname);
		latestMessageListShow.setMessagetype(outgoingMessageShow.getMessagetype());
		latestMessageListShow.setIsopen(true);
		latestMessageListShow.setAssignedto(outgoingMessageShow.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	
	
	
	// for notes
	
	public String save(MessageNotes messageNotes,String fullname) {
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setLastMessage(messageNotes.getMessage());
		latestMessageListShow.setTime(messageNotes.getTime());
		latestMessageListShow.setMessagetype(messageNotes.getMessagetype());
		latestMessageListShow.setMobileNo(messageNotes.getMobileNo());
		latestMessageListShow.setFullName(fullname);
		latestMessageListShow.setIsopen(true);
		latestMessageListShow.setAssignedto(messageNotes.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		
		return "success";
	}
	
	// for notes update
	
	public String update(Long id, MessageNotes messageNotes,String fullname) {

//		LatestMessageListShow upls = latestMessageListShowRepository
//				.findByMobilenumber(outgoingMessageShow.getMobileNo());
		LatestMessageListShow latestMessageListShow = new LatestMessageListShow();
		latestMessageListShow.setId(id);
		latestMessageListShow.setLastMessage(messageNotes.getMessage());
		latestMessageListShow.setMessagetype(messageNotes.getMessagetype());
		latestMessageListShow.setTime(messageNotes.getTime());
		latestMessageListShow.setMobileNo(messageNotes.getMobileNo());
		latestMessageListShow.setFullName(fullname);
		latestMessageListShow.setIsopen(true);
		latestMessageListShow.setAssignedto(messageNotes.getAssignedto());
		latestMessageListShowRepository.save(latestMessageListShow);
		return "success";
	}
	

	public LatestMessageListShow findbymobilenumber(String mobilenumber) {
		return latestMessageListShowRepository.findByMobileNo(mobilenumber);
	}
	
//	public List<LatestMessageListShow>  findAll() {
//		return latestMessageListShowRepository.findAll();
//	}

	public List<LatestMessageListShow> findAll() {
	    List<LatestMessageListShow> messages = latestMessageListShowRepository.findAll();
	    // Sort the messages by time
	    Collections.sort(messages, Comparator.comparing(LatestMessageListShow::getTime));
	    return messages;
	}
	
	public List<LatestMessageListShowResponseDTO> findAllisopen( ) {
	    
		LatestMessageListShowResponseDTO responseDTO = new LatestMessageListShowResponseDTO();

        List<LatestMessageListShow> openMessages = latestMessageListShowRepository.findByIsopenOrderByTimeDesc(true);
        List<LatestMessageListShow> closedMessages = latestMessageListShowRepository.findByIsopenOrderByTimeDesc(false);

        // Set the counts
        responseDTO.setOpenCount(openMessages.size());
        responseDTO.setClosedCount(closedMessages.size());
//        responseDTO.setTotalCount(openMessages.size() + closedMessages.size());

        // Convert open messages to DTOs
        List<LatestMessageListShowResponseDTO.LatestMessageDTO> openMessageDTOs = new ArrayList<>();
        for (LatestMessageListShow openMessage : openMessages) {
            LatestMessageListShowResponseDTO.LatestMessageDTO openMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
            openMessageDTO.setId(openMessage.getId());
            openMessageDTO.setPhoneNo(openMessage.getMobileNo());
            openMessageDTO.setAssignedto(openMessage.getAssignedto());
            openMessageDTO.setMessagetype(openMessage.getMessagetype());
            openMessageDTO.setFullName(openMessage.getFullName());
            openMessageDTO.setTime(openMessage.getTime().toString());
            openMessageDTO.setLastMsg(openMessage.getLastMessage());
            openMessageDTO.setCount((int)openMessage.getCount());
            openMessageDTOs.add(openMessageDTO);
        }

        // Convert closed messages to DTOs
        List<LatestMessageListShowResponseDTO.LatestMessageDTO> closedMessageDTOs = new ArrayList<>();
        for (LatestMessageListShow closedMessage : closedMessages) {
            LatestMessageListShowResponseDTO.LatestMessageDTO closedMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
            closedMessageDTO.setId(closedMessage.getId());
            closedMessageDTO.setMessagetype(closedMessage.getMessagetype());
            closedMessageDTO.setAssignedto(closedMessage.getAssignedto());
            closedMessageDTO.setPhoneNo(closedMessage.getMobileNo());
            closedMessageDTO.setFullName(closedMessage.getFullName());
            closedMessageDTO.setTime(closedMessage.getTime().toString());
            closedMessageDTO.setLastMsg(closedMessage.getLastMessage());
            closedMessageDTOs.add(closedMessageDTO);
        }

        // Set the open and closed messages in the response DTO
        responseDTO.setOpen(openMessageDTOs);
        responseDTO.setClosed(closedMessageDTOs);
	    
        List<LatestMessageListShowResponseDTO> resoDtos=new ArrayList<>();
        resoDtos.add(responseDTO);
	    return resoDtos;
	}
	
public List<LatestMessageListShowResponseDTO> findAllisopens(long assignedto ) {
	    
		LatestMessageListShowResponseDTO responseDTO = new LatestMessageListShowResponseDTO();

//		UserDao user = userRepository.findById((int)assignedto).orElseThrow(
//				() -> new ResourceNotFoundException("AssignedTot Id :" + ticketDto.getAssignedTo() + " Unavailable"));
//		
		
        List<LatestMessageListShow> openMessages = latestMessageListShowRepository.findByIsopenAndAssignedtoOrderByTimeDesc(true,assignedto);
        List<LatestMessageListShow> closedMessages = latestMessageListShowRepository.findByIsopenAndAssignedtoOrderByTimeDesc(false,assignedto);

        // Set the counts
        responseDTO.setOpenCount(openMessages.size());
        responseDTO.setClosedCount(closedMessages.size());
//        responseDTO.setTotalCount(openMessages.size() + closedMessages.size());

        // Convert open messages to DTOs
        List<LatestMessageListShowResponseDTO.LatestMessageDTO> openMessageDTOs = new ArrayList<>();
        for (LatestMessageListShow openMessage : openMessages) {
            LatestMessageListShowResponseDTO.LatestMessageDTO openMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
            openMessageDTO.setId(openMessage.getId());
            openMessageDTO.setMessagetype(openMessage.getMessagetype());
            openMessageDTO.setPhoneNo(openMessage.getMobileNo());
            openMessageDTO.setFullName(openMessage.getFullName());
            openMessageDTO.setTime(openMessage.getTime().toString());
            openMessageDTO.setLastMsg(openMessage.getLastMessage());
            openMessageDTO.setCount((int)openMessage.getCount());
            openMessageDTOs.add(openMessageDTO);
        }

        // Convert closed messages to DTOs
        List<LatestMessageListShowResponseDTO.LatestMessageDTO> closedMessageDTOs = new ArrayList<>();
        for (LatestMessageListShow closedMessage : closedMessages) {
            LatestMessageListShowResponseDTO.LatestMessageDTO closedMessageDTO = new LatestMessageListShowResponseDTO.LatestMessageDTO();
            closedMessageDTO.setId(closedMessage.getId());
            closedMessageDTO.setMessagetype(closedMessage.getMessagetype());
            closedMessageDTO.setPhoneNo(closedMessage.getMobileNo());
            closedMessageDTO.setFullName(closedMessage.getFullName());
            closedMessageDTO.setTime(closedMessage.getTime().toString());
            closedMessageDTO.setLastMsg(closedMessage.getLastMessage());
            closedMessageDTOs.add(closedMessageDTO);
        }

        // Set the open and closed messages in the response DTO
        responseDTO.setOpen(openMessageDTOs);
        responseDTO.setClosed(closedMessageDTOs);
	    
        List<LatestMessageListShowResponseDTO> resoDtos=new ArrayList<>();
        resoDtos.add(responseDTO);
	    return resoDtos;
	}
	
	
	
	
	    

	
}
