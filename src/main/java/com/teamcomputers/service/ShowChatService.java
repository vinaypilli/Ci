package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.NumberWiseChatDTO;
import com.teamcomputers.dto.showChatDTO;
import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.model.OutgoingMessageResponce;
import com.teamcomputers.model.OutgoingType_text;
import com.teamcomputers.repository.IncomingMessageRepository;
import com.teamcomputers.repository.OutgoingMessageResponceRepository;

@Service
public class ShowChatService {

	@Autowired
	private IncomingMessageRepository incomingRepository;

	@Autowired
	private OutgoingMessageResponceRepository outgoingMessageResponceRepository;

	public List<NumberWiseChatDTO> getNumberWiseChats() {
		List<String> numbers = getAllNumbers();
		List<NumberWiseChatDTO> numberWiseChats = new ArrayList<>();

		for (String number : numbers) {
			List<IncomingMessage> incomingMessages = incomingRepository.findByFrom(number);
			List<OutgoingMessageResponce> outgoingMessageResponces = outgoingMessageResponceRepository
					.findByRecipientwhatsapp(number);
			// List<OutgoingMessageResponce> outgoingMessages =
			// outgoingMessageRepository.findByNumber(number);
			List<showChatDTO> chats = transformToChatDTOs(incomingMessages, outgoingMessageResponces);
			NumberWiseChatDTO numberWiseChatDTO = new NumberWiseChatDTO();
			numberWiseChatDTO.setNumber(number);
			numberWiseChatDTO.setChats(chats);

			numberWiseChats.add(numberWiseChatDTO);
		}

		return numberWiseChats;
	}

	private List<String> getAllNumbers() {
		List<String> numbers = new ArrayList<>();

		List<String> incomingNumbers = incomingRepository.findAllFrom();
		List<String> outgoingNumbers = outgoingMessageResponceRepository.findAllRecipientwhatsapp();
		numbers.addAll(incomingNumbers);
		numbers.addAll(outgoingNumbers);
		// Logic to fetch all numbers from the database
		// ...
		numbers = new ArrayList<>(new HashSet<>(numbers));

		return numbers;
	}

	private List<showChatDTO> transformToChatDTOs(List<IncomingMessage> incomingMessages,
			List<OutgoingMessageResponce> outgoingMessageResponces) {
		List<showChatDTO> chats = new ArrayList<>();

		for (IncomingMessage incomingMessage : incomingMessages) {
			showChatDTO chatDTO = new showChatDTO();
			chatDTO.setType("Reciever");
			chatDTO.setMessagetype("Text");
			chatDTO.setMessage(incomingMessage.getText_type().getText());
			chatDTO.setTime(incomingMessage.getCreatedDate());
			chatDTO.setName("Reception");
			chatDTO.setIsopen(true);
			chatDTO.setGroup("Reception");
			chatDTO.setMobileNo(incomingMessage.getFrom());

			chats.add(chatDTO);
		}
		for (OutgoingMessageResponce outgoingMessageResponce : outgoingMessageResponces) {
			// OutgoingMessageData chatDTO1 = new OutgoingMessageData();
			OutgoingType_text outgoingResponseStatus = outgoingMessageResponce.getType_text().get(0);
			showChatDTO chatDTO = new showChatDTO();
			chatDTO.setType("Sender");
			chatDTO.setMessagetype("Text");
			chatDTO.setMessage(outgoingResponseStatus.getContent());
			chatDTO.setTime(outgoingMessageResponce.getCreatedDate());
			chatDTO.setName("You");
			chatDTO.setIsopen(true);
			chatDTO.setGroup("You");
			chatDTO.setMobileNo(outgoingMessageResponce.getRecipientwhatsapp());
			chatDTO.setTime(outgoingMessageResponce.getCreatedDate());

			chats.add(chatDTO);
		}

		// Sort the chats based on timestamp
		chats.sort(Comparator.comparing(showChatDTO::getTime));

		return chats;
	}

	// bynumber
	public List<showChatDTO> getChatMessagesByNumber(String number) {
		List<OutgoingMessageResponce> outgoingMessages = outgoingMessageResponceRepository
				.findByRecipientwhatsapp(number);
		List<IncomingMessage> incomingMessages = incomingRepository.findByFrom(number);

		List<showChatDTO> chatMessages = new ArrayList<>();

		for (IncomingMessage incomingMessage : incomingMessages) {
			showChatDTO chatDTO = new showChatDTO();

			chatDTO.setType("Reciever");
			chatDTO.setMessagetype("Text");
			chatDTO.setMessage(incomingMessage.getText_type().getText());
			chatDTO.setTime(incomingMessage.getCreatedDate());
			chatDTO.setName("Reception");
			chatDTO.setIsopen(true);
			chatDTO.setGroup("Reception");
			chatDTO.setMobileNo(incomingMessage.getFrom());

			chatMessages.add(chatDTO);
		}
		// List<showChatDTO> chatMessages1 = new ArrayList<>();
		for (OutgoingMessageResponce outgoingMessageResponce : outgoingMessages) {
			// OutgoingMessageData chatDTO1 = new OutgoingMessageData();
			OutgoingType_text outgoingResponseStatus = outgoingMessageResponce.getType_text().get(0);
			showChatDTO chatDTO = new showChatDTO();
			chatDTO.setType("Sender");
			chatDTO.setMessagetype("Text");
			chatDTO.setMessage(outgoingResponseStatus.getContent());
			chatDTO.setTime(outgoingMessageResponce.getCreatedDate());
			chatDTO.setName("You");
			chatDTO.setIsopen(true);
			chatDTO.setGroup("You");
			chatDTO.setMobileNo(outgoingMessageResponce.getRecipientwhatsapp());
			chatDTO.setTime(outgoingMessageResponce.getCreatedDate());

			chatMessages.add(chatDTO);
		}
		chatMessages.sort(Comparator.comparing(showChatDTO::getTime));
		// chatMessages.sort(Comparator.comparing(showChatDTO::getTimestamp));
//	        List<Object> combinedList = new ArrayList<>();
//
//	        combinedList.add(chatMessages1);
//	        combinedList.add(chatMessages);

		// chatMessages.sort(Comparator.comparing(showChatDTO::getTimestamp));

		return chatMessages;
	}

}
