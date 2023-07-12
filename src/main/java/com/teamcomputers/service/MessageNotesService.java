package com.teamcomputers.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Customer;
import com.teamcomputers.model.MessageNotes;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.MessageNotesRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;

@Service
public class MessageNotesService {

	@Autowired
	private MessageNotesRepository messageNotesRepository;

	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public MessageNotes save(OutgoingMessageFormat outgoingMessageFormat, String fullname) {
		OutgoingActivity outgoingActivity = outgoingActivityRepository
				.findFirstByMobileNoOrderByTimeDesc(outgoingMessageFormat.getRecipient_whatsapp());
//		Customer customer = customerRepository.findByContact(outgoingMessageFormat.getRecipient_whatsapp());
		OutgoingActivity outgoingAssigned2 = new OutgoingActivity();

		if (outgoingActivity != null) {
			outgoingAssigned2.setId(outgoingActivity.getId());
			outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
			outgoingAssigned2.setFromId(outgoingActivity.getFromId());
			outgoingAssigned2.setGroup("You");
			outgoingAssigned2.setMessage(outgoingActivity.getMessagetype());
			outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
			outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
			outgoingAssigned2.setIsopen(true);
			outgoingAssigned2.setTime(LocalDateTime.now());
			outgoingAssigned2.setType("Receiver");
			outgoingActivityRepository.save(outgoingAssigned2);
		} else {
			outgoingAssigned2.setAssignedto(1);
			outgoingAssigned2.setFromId(1);
			outgoingAssigned2.setGroup("You");
			outgoingAssigned2.setMessage("open");
			outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
			outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
			outgoingAssigned2.setIsopen(true);
			outgoingAssigned2.setTime(LocalDateTime.now());
			outgoingAssigned2.setType("Receiver");
			outgoingActivityRepository.save(outgoingAssigned2);
		}

		MessageNotes messageNotes = new MessageNotes();
		messageNotes.setMobileNo(outgoingMessageFormat.getRecipient_whatsapp());
		messageNotes.setFromId(outgoingMessageFormat.getFormId());
		messageNotes.setIsopen(true);
		messageNotes.setName(fullname);
		messageNotes.setMessage(outgoingMessageFormat.getContent());
		messageNotes.setMessagetype(outgoingMessageFormat.getMessage_type());
		messageNotes.setType("Sender");
		messageNotes.setGroup("Sender");
		messageNotes.setFromId(outgoingAssigned2.getFromId());
		messageNotes.setAssignedto(outgoingAssigned2.getAssignedto());
		return messageNotesRepository.save(messageNotes);
	}

	public void updateName(String recipientWhatsApp, String newName) {
		List<MessageNotes> messageNotes = messageNotesRepository.findByMobileNo(recipientWhatsApp);
		if (!messageNotes.isEmpty()) {
			for (MessageNotes note : messageNotes) {
				note.setName(newName);
				messageNotesRepository.save(note);
			}
		}
	}

}
