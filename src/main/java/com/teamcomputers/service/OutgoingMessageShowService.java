package com.teamcomputers.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.MessageNotes;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.model.OutgoingMessageResponce;
import com.teamcomputers.model.OutgoingMessageShow;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.OutgoingMessageShowRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class OutgoingMessageShowService {

	@Autowired
	private OutgoingMessageShowRepository outgoingMessageRepositoryShowRepository;

	@Autowired
	private UserRepository userRepository;

	public String findnamebyuserid(int id) {
		String fullname = null;
		UserDao assignedto = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Id :" + id + " Unavailable"));

		if (assignedto.getFirstName() != null) {
			if (assignedto.getFirstName() != null && assignedto.getLastName() != null) {
				fullname = assignedto.getFirstName() + " " + assignedto.getLastName();
			} else {
				fullname = assignedto.getFirstName();
			}
		}

		return fullname;

	}

	public OutgoingMessageShow saveOutgoingMessage(OutgoingMessageResponce outgoingMessageResponce,
			OutgoingMessageFormat outgoingMessageFormat) {

		String fullname = this.findnamebyuserid((int) outgoingMessageFormat.getFormId());

//
		OutgoingMessageShow outgoingMessageShow = new OutgoingMessageShow();
		outgoingMessageShow.setMessage(outgoingMessageFormat.getContent());
		outgoingMessageShow.setTime(outgoingMessageResponce.getCreatedDate());
		outgoingMessageShow.setMobileNo(outgoingMessageResponce.getRecipientwhatsapp());
		outgoingMessageShow.setType("Sender");
		outgoingMessageShow.setMessagetype("Text");
		outgoingMessageShow.setGroup("you");
		outgoingMessageShow.setName(fullname);
		outgoingMessageShow.setIsopen(true);

		return outgoingMessageRepositoryShowRepository.save(outgoingMessageShow);
	}

	public OutgoingMessageShow updateOutgoingMessage(Long id, LocalDateTime localDateTime) {
		OutgoingMessageShow outgoingMessageShow = new OutgoingMessageShow();
		outgoingMessageShow.setId(id);
		outgoingMessageShow.setMessage(outgoingMessageShow.getMessage());
		outgoingMessageShow.setTime(localDateTime);
		outgoingMessageShow.setMobileNo(outgoingMessageShow.getMobileNo());
		outgoingMessageShow.setType("Sender");
		outgoingMessageShow.setMessagetype("Text");
		outgoingMessageShow.setGroup("you");
		outgoingMessageShow.setName(outgoingMessageShow.getName());
		outgoingMessageShow.setIsopen(true);

		return outgoingMessageRepositoryShowRepository.save(outgoingMessageShow);
	}

	public OutgoingMessageShow saveOutgoingNotes(MessageNotes outgoingMessageResponce,
			OutgoingMessageFormat outgoingMessageFormat) {
		String fullname = this.findnamebyuserid((int) outgoingMessageFormat.getFormId());

		OutgoingMessageShow outgoingMessageShow = new OutgoingMessageShow();
		outgoingMessageShow.setMessage(outgoingMessageFormat.getContent());
		outgoingMessageShow.setTime(outgoingMessageResponce.getTime());
		outgoingMessageShow.setMobileNo(outgoingMessageResponce.getMobileNo());
		outgoingMessageShow.setType("Sender");
		outgoingMessageShow.setMessagetype("Text");
		outgoingMessageShow.setGroup("you");
		outgoingMessageShow.setName(fullname);
		outgoingMessageShow.setIsopen(true);
		return outgoingMessageRepositoryShowRepository.save(outgoingMessageShow);
	}

	public OutgoingMessageShow savetemplate(OutgoingMessageResponce outgoingMessageResponce) {
//		String fullname = this.findnamebyuserid((int) outgoingMessageResponce.get());

		OutgoingMessageShow outgoingMessageShow = new OutgoingMessageShow();
		outgoingMessageShow = new OutgoingMessageShow();
		outgoingMessageShow.setMessage("Template");
		outgoingMessageShow.setTime(outgoingMessageResponce.getCreatedDate());
		outgoingMessageShow.setMobileNo(outgoingMessageResponce.getRecipientwhatsapp());
		outgoingMessageShow.setType("Sender");
		outgoingMessageShow.setMessagetype(outgoingMessageResponce.getMessage_type());
		outgoingMessageShow.setGroup("you");
		outgoingMessageShow.setName("You");
		outgoingMessageShow.setIsopen(true);
		return outgoingMessageRepositoryShowRepository.save(outgoingMessageShow);
	}

	public void updateName(String recipientWhatsApp, String newName) {
		List<OutgoingMessageShow> outgoingMessageShows = outgoingMessageRepositoryShowRepository
				.findByMobileNo(recipientWhatsApp);
		if (!outgoingMessageShows.isEmpty()) {
			for (OutgoingMessageShow note : outgoingMessageShows) {
				note.setName(newName);
				outgoingMessageRepositoryShowRepository.save(note);
			}
		}
	}

//    
////    public MessageData saveWebhookSentMessage(MessageData rp) {
////    	 return outgoingMessageRepository.save(rp);
////    
////    }
////
//    public Optional<OutgoingMessage> getWebhookSentMessageById(long id) {
//        return outgoingMessageRepository.findById(id) ;
//    }
////
////    public void deleteWebhookSentMessage(MessageData messageData) {
////        outgoingMessageRepository.delete(messageData);
////    }
//
//    // Add other service methods as required
}
