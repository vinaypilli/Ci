package com.teamcomputers.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.MessageHistory;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.IncomingMessageShow;
import com.teamcomputers.model.MessageNotes;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.OutgoingMessageShow;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.IncomingMessageShowRepository;
import com.teamcomputers.repository.MessageNotesRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;
import com.teamcomputers.repository.OutgoingMessageShowRepository;
import com.teamcomputers.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;

@Service
public class MessageHistoryService {

	@Autowired
	private OutgoingMessageShowRepository outgoingMessageShowRepository;

	@Autowired
	private IncomingMessageShowRepository incomingMessageShowRepository;

	@Autowired
	private MessageNotesRepository messageNotesRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;
	
	
	public List<MessageHistory> mergeMessageHistories(String number) {
		List<MessageHistory> messageHistoriesList = new ArrayList<>();

//		List<OutgoingMessageShow> outgoing = outgoingMessageShowRepository.findByMobileNo(number);
		List<OutgoingMessageShow> outgoing = outgoingMessageShowRepository.findByMobileNo(number);
		for (OutgoingMessageShow outgoingMessage : outgoing) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(outgoingMessage.getMessage());
			messageHistory.setTime(outgoingMessage.getTime());
			messageHistory.setMobileNo(outgoingMessage.getMobileNo());
			messageHistory.setType("Sender");
			messageHistory.setMessagetype(outgoingMessage.getMessagetype());
			messageHistory.setGroup(outgoingMessage.getGroup());
			;
			messageHistory.setName(outgoingMessage.getName());
			messageHistory.setIsopen(true);

			messageHistoriesList.add(messageHistory);
		}

//		List<IncomingMessageShow> incoming = incomingMessageShowRepository.findByMobileNo(number);
		List<IncomingMessageShow> incoming = incomingMessageShowRepository.findByMobileNo(number);
		for (IncomingMessageShow incomingMessage : incoming) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(incomingMessage.getMessage());
			messageHistory.setTime(incomingMessage.getTime());
			messageHistory.setMobileNo(incomingMessage.getMobileNo());
			messageHistory.setType("Receiver");
			messageHistory.setMessagetype(incomingMessage.getMessagetype());
			messageHistory.setGroup(incomingMessage.getGroup());
			;
			messageHistory.setName(incomingMessage.getName());
			messageHistory.setIsopen(true);

			messageHistoriesList.add(messageHistory);
		}

		List<MessageNotes> messageNotes = messageNotesRepository.findByMobileNo(number);
		
		
		
		for (MessageNotes messageNote : messageNotes) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(messageNote.getMessage());
		
			messageHistory.setTime(messageNote.getTime());
			messageHistory.setMobileNo(messageNote.getMobileNo());
			messageHistory.setType(messageNote.getType());
			messageHistory.setMessagetype(messageNote.getMessagetype());
			messageHistory.setGroup(messageNote.getGroup());
			messageHistory.setName(messageNote.getName());
			messageHistory.setIsopen(true);
			messageHistoriesList.add(messageHistory);
		}

//		List<OutgoingAssigned> outgoingAssigneds = outgoingAssignedRepository.findByMobileNo(number);
//		for (OutgoingAssigned outgoingAssigned : outgoingAssigneds) {
//			MessageHistory messageHistory = new MessageHistory();
//
//			messageHistory.setTime(outgoingAssigned.getTime());
//			messageHistory.setMobileNo(outgoingAssigned.getMobileNo());
//			messageHistory.setType(outgoingAssigned.getType());
//			messageHistory.setMessagetype(outgoingAssigned.getMessagetype());
//			messageHistory.setGroup(outgoingAssigned.getGroup());
//
//			UserDao assignedto=null;
//			if (outgoingAssigned.getAssignedto()==0) {
//				
//				assignedto = userRepository.findById((int) outgoingAssigned.getAssignedto()).orElseThrow(null);
//
//			}
//			
////			UserDao assignedto = userRepository.findById((int) outgoingAssigned.getAssignedto()).orElseThrow(null);
////					.orElseThrow(() -> new ResourceNotFoundException(
////							"AssignedTo Id :" + outgoingAssigned.getAssignedto() + " Unavailable"));
//			String assignedtoname = null;
//			String fromname = null;
//
//			UserDao fromId = userRepository.findById((int) outgoingAssigned.getAssignedto())
//					.orElseThrow(null);
//
//			// get user full name
//			if (assignedto.getFirstName() != null) {
//
//				if (assignedto.getFirstName() != null && assignedto.getLastName() != null) {
//					assignedtoname = assignedto.getFirstName() + " " + assignedto.getLastName();
//				} else {
//					assignedtoname = assignedto.getFirstName();
//				}
//			}
//
//			if (fromId.getFirstName() != null) {
//
//				if (fromId.getFirstName() != null && fromId.getLastName() != null) {
//					fromname = fromId.getFirstName() + " " + fromId.getLastName();
//				} else {
//					fromname = assignedto.getFirstName();
//				}
//			}
//
//			messageHistory.setName(fromname);
//			messageHistory.setIsopen(true);
//			messageHistoriesList.add(messageHistory);
//
//			String message = null;
//
//			// Calculate the time difference
////		    LocalDateTime currentTime = LocalDateTime.now();
////		    LocalDateTime messageTime = outgoingAssigned.getTime();
////		    Duration duration = Duration.between(messageTime, currentTime);
////
////		    // Convert the duration to a human-readable format
////		    long days = duration.toDays();
////		    long hours = duration.toHours() % 24;
////		    long minutes = duration.toMinutes() % 60;
////		    long seconds = duration.getSeconds() % 60;
////
////		    String timeAgo;
////		    if (days > 0) {
////		        timeAgo = days + " days ago";
////		    } else if (hours > 0) {
////		        timeAgo = hours + " hours ago";
////		    } else if (minutes > 0) {
////		        timeAgo = minutes + " minutes ago";
////		    } else {
////		        timeAgo = seconds + " seconds ago";
////		    }
////
////		    // Update the message with the calculated time difference
////		    message="This conversation was assigned from Amulya Sah to Dhruv Kumar " + timeAgo;
//
//			// Calculate the time difference
//			LocalDateTime currentTime = LocalDateTime.now();
//			LocalDateTime messageTime = outgoingAssigned.getTime();
//			Duration duration = Duration.between(messageTime, currentTime);
//
//			// Convert the duration to a human-readable format
//			long days = duration.toDays();
//			long hours = duration.toHours();
//
//			String timeAgo;
//
//			if (outgoingAssigned.getMessagetype().equals("assigned")) {
//				if (outgoingAssigned.getFromId() == 0) {
//					// Assigned without a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
//					message = "This conversation was assigned to " + assignedtoname + " " + timeAgo;
//				} else {
//					// Assigned with a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
//					message = "This conversation was assigned from " + fromname + " to " + assignedtoname + " "
//							+ timeAgo;
//				}
//			} else if (outgoingAssigned.getMessagetype().equals("closed")) {
//				// Opened by customer
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
//				message = "This chat was closed by customer " + timeAgo;
//			}else if (outgoingAssigned.getMessagetype().equals("open")) {
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
//				message = "This chat was opened by customer " + timeAgo;
//				
//			}
//
//			messageHistory.setMessage(message);
//		}

		List<OutgoingActivity> outgoingActivities = outgoingActivityRepository.findByMobileNo(number);
		for (OutgoingActivity outgoingActivity : outgoingActivities) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setTime(outgoingActivity.getTime());
			messageHistory.setMobileNo(outgoingActivity.getMobileNo());
			messageHistory.setType(outgoingActivity.getType());
			messageHistory.setMessagetype(outgoingActivity.getMessagetype());
			messageHistory.setGroup(outgoingActivity.getGroup());

			String assignedtoname = null;
			String fromname = null;

			if (outgoingActivity.getAssignedto() != 0) {
				UserDao assignedto = userRepository.findById((int) outgoingActivity.getAssignedto())
						.orElseThrow(() -> new ResourceNotFoundException(
								"AssignedTo Id :" + outgoingActivity.getAssignedto() + " Unavailable"));

				if (assignedto.getFirstName() != null) {
					if (assignedto.getFirstName() != null && assignedto.getLastName() != null) {
						assignedtoname = assignedto.getFirstName() + " " + assignedto.getLastName();
					} else {
						assignedtoname = assignedto.getFirstName();
					}
				}
			}

			if (outgoingActivity.getFromId() != 0) {
				UserDao fromId = userRepository.findById((int) outgoingActivity.getFromId())
						.orElseThrow(() -> new ResourceNotFoundException(
								"FromId :" + outgoingActivity.getFromId() + " Unavailable"));

				if (fromId.getFirstName() != null) {
					if (fromId.getFirstName() != null && fromId.getLastName() != null) {
						fromname = fromId.getFirstName() + " " + fromId.getLastName();
					} else {
						fromname = fromId.getFirstName();
					}
				}
			}

			messageHistory.setName(fromname);
			messageHistory.setIsopen(true);
			messageHistoriesList.add(messageHistory);

			String message = null;

			// Calculate the time difference
			LocalDateTime currentTime = LocalDateTime.now();
			LocalDateTime messageTime = outgoingActivity.getTime();
			Duration duration = Duration.between(messageTime, currentTime);

			// Convert the duration to a human-readable format
			long days = duration.toDays();
			long hours = duration.toHours();

			String timeAgo;

			if (outgoingActivity.getMessagetype().equals("assigned")) {
				if (outgoingActivity.getFromId() == 0) {
					// Assigned without a specific sender
					if (days > 0) {
						timeAgo = days + " days ago";
					} else {
						timeAgo = hours + " hours ago";
					}
					message = "This conversation was assigned to " + assignedtoname + " " + timeAgo;
				} else {
					// Assigned with a specific sender
					if (days > 0) {
						timeAgo = days + " days ago";
					} else {
						timeAgo = hours + " hours ago";
					}
					message = "This conversation was assigned from " + fromname + " to " + assignedtoname + " "
							+ timeAgo;
				}
			} else if (outgoingActivity.getMessagetype().equals("closed")) {
				// Opened by customer
				if (days > 0) {
					timeAgo = days + " days ago";
				} else {
					timeAgo = hours + " hours ago";
				}
				message = "This chat was closed by "+fromname+" "+  timeAgo;
			} else if (outgoingActivity.getMessagetype().equals("open")) {
				if (days > 0) {
					timeAgo = days + " days ago";
				} else {
					timeAgo = hours + " hours ago";
				}
				message = "This chat was opened by "+fromname +" " + timeAgo;
			}else if (outgoingActivity.getMessagetype().equals("incomingopen")) {
				if (days > 0) {
					timeAgo = days + " days ago";
				} else {
					timeAgo = hours + " hours ago";
				}
				message = "This chat was opened by customer " + timeAgo;
			}

			messageHistory.setMessage(message);
		}

		// Sort the messageHistoriesList based on messagetime in descending order
		Comparator<MessageHistory> messageTimeComparator = Comparator.comparing(MessageHistory::getTime);
		Collections.sort(messageHistoriesList, messageTimeComparator);

		return messageHistoriesList;
	}
	

	public List<MessageHistory> mergeMessageHistories(String number,long assigned) {
		List<MessageHistory> messageHistoriesList = new ArrayList<>();

//		List<OutgoingMessageShow> outgoing = outgoingMessageShowRepository.findByMobileNo(number);
		List<OutgoingMessageShow> outgoing = outgoingMessageShowRepository.findByMobileNoAndAssignedto(number,assigned);
		for (OutgoingMessageShow outgoingMessage : outgoing) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(outgoingMessage.getMessage());
			messageHistory.setTime(outgoingMessage.getTime());
			messageHistory.setMobileNo(outgoingMessage.getMobileNo());
			messageHistory.setType("Sender");
			messageHistory.setMessagetype(outgoingMessage.getMessagetype());
			messageHistory.setGroup(outgoingMessage.getGroup());
			;
			messageHistory.setName(outgoingMessage.getName());
			messageHistory.setIsopen(true);

			messageHistoriesList.add(messageHistory);
		}

//		List<IncomingMessageShow> incoming = incomingMessageShowRepository.findByMobileNo(number);
		List<IncomingMessageShow> incoming = incomingMessageShowRepository.findByMobileNoAndAssignedto(number,assigned);
		for (IncomingMessageShow incomingMessage : incoming) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(incomingMessage.getMessage());
			messageHistory.setTime(incomingMessage.getTime());
			messageHistory.setMobileNo(incomingMessage.getMobileNo());
			messageHistory.setType("Receiver");
			messageHistory.setMessagetype(incomingMessage.getMessagetype());
			messageHistory.setGroup(incomingMessage.getGroup());
			;
			messageHistory.setName(incomingMessage.getName());
			messageHistory.setIsopen(true);

			messageHistoriesList.add(messageHistory);
		}

		List<MessageNotes> messageNotes = messageNotesRepository.findByMobileNo(number);
		
		
		
		for (MessageNotes messageNote : messageNotes) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setMessage(messageNote.getMessage());
			messageHistory.setTime(messageNote.getTime());
			messageHistory.setMobileNo(messageNote.getMobileNo());
			messageHistory.setType(messageNote.getType());
			messageHistory.setMessagetype(messageNote.getMessagetype());
			messageHistory.setGroup(messageNote.getGroup());
			messageHistory.setName(messageNote.getName());
			messageHistory.setIsopen(true);
			messageHistoriesList.add(messageHistory);
		}

//		List<OutgoingAssigned> outgoingAssigneds = outgoingAssignedRepository.findByMobileNo(number);
//		for (OutgoingAssigned outgoingAssigned : outgoingAssigneds) {
//			MessageHistory messageHistory = new MessageHistory();
//
//			messageHistory.setTime(outgoingAssigned.getTime());
//			messageHistory.setMobileNo(outgoingAssigned.getMobileNo());
//			messageHistory.setType(outgoingAssigned.getType());
//			messageHistory.setMessagetype(outgoingAssigned.getMessagetype());
//			messageHistory.setGroup(outgoingAssigned.getGroup());
//
//			UserDao assignedto=null;
//			if (outgoingAssigned.getAssignedto()==0) {
//				
//				assignedto = userRepository.findById((int) outgoingAssigned.getAssignedto()).orElseThrow(null);
//
//			}
//			
////			UserDao assignedto = userRepository.findById((int) outgoingAssigned.getAssignedto()).orElseThrow(null);
////					.orElseThrow(() -> new ResourceNotFoundException(
////							"AssignedTo Id :" + outgoingAssigned.getAssignedto() + " Unavailable"));
//			String assignedtoname = null;
//			String fromname = null;
//
//			UserDao fromId = userRepository.findById((int) outgoingAssigned.getAssignedto())
//					.orElseThrow(null);
//
//			// get user full name
//			if (assignedto.getFirstName() != null) {
//
//				if (assignedto.getFirstName() != null && assignedto.getLastName() != null) {
//					assignedtoname = assignedto.getFirstName() + " " + assignedto.getLastName();
//				} else {
//					assignedtoname = assignedto.getFirstName();
//				}
//			}
//
//			if (fromId.getFirstName() != null) {
//
//				if (fromId.getFirstName() != null && fromId.getLastName() != null) {
//					fromname = fromId.getFirstName() + " " + fromId.getLastName();
//				} else {
//					fromname = assignedto.getFirstName();
//				}
//			}
//
//			messageHistory.setName(fromname);
//			messageHistory.setIsopen(true);
//			messageHistoriesList.add(messageHistory);
//
//			String message = null;
//
//			// Calculate the time difference
////		    LocalDateTime currentTime = LocalDateTime.now();
////		    LocalDateTime messageTime = outgoingAssigned.getTime();
////		    Duration duration = Duration.between(messageTime, currentTime);
////
////		    // Convert the duration to a human-readable format
////		    long days = duration.toDays();
////		    long hours = duration.toHours() % 24;
////		    long minutes = duration.toMinutes() % 60;
////		    long seconds = duration.getSeconds() % 60;
////
////		    String timeAgo;
////		    if (days > 0) {
////		        timeAgo = days + " days ago";
////		    } else if (hours > 0) {
////		        timeAgo = hours + " hours ago";
////		    } else if (minutes > 0) {
////		        timeAgo = minutes + " minutes ago";
////		    } else {
////		        timeAgo = seconds + " seconds ago";
////		    }
////
////		    // Update the message with the calculated time difference
////		    message="This conversation was assigned from Amulya Sah to Dhruv Kumar " + timeAgo;
//
//			// Calculate the time difference
//			LocalDateTime currentTime = LocalDateTime.now();
//			LocalDateTime messageTime = outgoingAssigned.getTime();
//			Duration duration = Duration.between(messageTime, currentTime);
//
//			// Convert the duration to a human-readable format
//			long days = duration.toDays();
//			long hours = duration.toHours();
//
//			String timeAgo;
//
//			if (outgoingAssigned.getMessagetype().equals("assigned")) {
//				if (outgoingAssigned.getFromId() == 0) {
//					// Assigned without a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
//					message = "This conversation was assigned to " + assignedtoname + " " + timeAgo;
//				} else {
//					// Assigned with a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
//					message = "This conversation was assigned from " + fromname + " to " + assignedtoname + " "
//							+ timeAgo;
//				}
//			} else if (outgoingAssigned.getMessagetype().equals("closed")) {
//				// Opened by customer
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
//				message = "This chat was closed by customer " + timeAgo;
//			}else if (outgoingAssigned.getMessagetype().equals("open")) {
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
//				message = "This chat was opened by customer " + timeAgo;
//				
//			}
//
//			messageHistory.setMessage(message);
//		}

		List<OutgoingActivity> outgoingActivities = outgoingActivityRepository.findByMobileNo(number);
		for (OutgoingActivity outgoingActivity : outgoingActivities) {
			MessageHistory messageHistory = new MessageHistory();
			messageHistory.setTime(outgoingActivity.getTime());
			messageHistory.setMobileNo(outgoingActivity.getMobileNo());
			messageHistory.setType(outgoingActivity.getType());
			messageHistory.setMessagetype(outgoingActivity.getMessagetype());
			messageHistory.setGroup(outgoingActivity.getGroup());

			String assignedtoname = null;
			String fromname = null;

			if (outgoingActivity.getAssignedto() != 0) {
				UserDao assignedto = userRepository.findById((int) outgoingActivity.getAssignedto())
						.orElseThrow(() -> new ResourceNotFoundException(
								"AssignedTo Id :" + outgoingActivity.getAssignedto() + " Unavailable"));

				if (assignedto.getFirstName() != null) {
					if (assignedto.getFirstName() != null && assignedto.getLastName() != null) {
						assignedtoname = assignedto.getFirstName() + " " + assignedto.getLastName();
					} else {
						assignedtoname = assignedto.getFirstName();
					}
				}
			}

			if (outgoingActivity.getFromId() != 0) {
				UserDao fromId = userRepository.findById((int) outgoingActivity.getFromId())
						.orElseThrow(() -> new ResourceNotFoundException(
								"FromId :" + outgoingActivity.getFromId() + " Unavailable"));

				if (fromId.getFirstName() != null) {
					if (fromId.getFirstName() != null && fromId.getLastName() != null) {
						fromname = fromId.getFirstName() + " " + fromId.getLastName();
					} else {
						fromname = fromId.getFirstName();
					}
				}
			}

			messageHistory.setName(fromname);
			messageHistory.setIsopen(true);
			messageHistoriesList.add(messageHistory);

			String message = null;

			// Calculate the time difference
//			LocalDateTime currentTime = LocalDateTime.now();
//			LocalDateTime messageTime = outgoingActivity.getTime();
//			Duration duration = Duration.between(messageTime, currentTime);

			// Convert the duration to a human-readable format
//			long days = duration.toDays();
//			long hours = duration.toHours();

//			String timeAgo;
//
			if (outgoingActivity.getMessagetype().equals("assigned")) {
				if (outgoingActivity.getFromId() == 0) {
//					// Assigned without a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
					message = "This conversation was assigned to " + assignedtoname ;//+ " " + timeAgo;
				} else {
					// Assigned with a specific sender
//					if (days > 0) {
//						timeAgo = days + " days ago";
//					} else {
//						timeAgo = hours + " hours ago";
//					}
					message = "This conversation was assigned from " + fromname + " to " + assignedtoname ;//+ " "+ timeAgo;
				}
			} else if (outgoingActivity.getMessagetype().equals("closed")) {
				// Opened by customer
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
				message = "This chat was closed by "+fromname;//+" "+  timeAgo;
			} else if (outgoingActivity.getMessagetype().equals("open")) {
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
				message = "This chat was opened by "+ fromname;//+" "+ timeAgo;
				
			}else if (outgoingActivity.getMessagetype().equals("incomingopen")) {
//				if (days > 0) {
//					timeAgo = days + " days ago";
//				} else {
//					timeAgo = hours + " hours ago";
//				}
				message = "This chat was opened by customer ";// + timeAgo;
			}

			messageHistory.setMessage(message);
		}

		// Sort the messageHistoriesList based on messagetime in descending order
		Comparator<MessageHistory> messageTimeComparator = Comparator.comparing(MessageHistory::getTime);
		Collections.sort(messageHistoriesList, messageTimeComparator);

		return messageHistoriesList;
	}

}
