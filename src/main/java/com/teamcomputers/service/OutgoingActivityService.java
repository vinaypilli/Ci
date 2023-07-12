package com.teamcomputers.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.LatestMessageListShowRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class OutgoingActivityService<T> {

	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;
	
	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@Autowired
	private UserRepository userRepository;

	public OutgoingActivity saveOutgoingAssigned(OutgoingActivity outgoingActivity) {

		OutgoingActivity outgoingAssigned2 = new OutgoingActivity();

		List<OutgoingActivity> outgoingAssigned3 = outgoingActivityRepository
				.findByMobileNo(outgoingActivity.getMobileNo());

		if (outgoingAssigned3.isEmpty()) {
			if (outgoingActivity.getAssignedto() == 0) {
				throw new ResourceNotFoundException("AssignedTo Id :" + outgoingActivity.getAssignedto()
						+ " Not Allow Please Enter valid AssignedTo Id");
			} else if (outgoingActivity.getAssignedto() != 0) {
				UserDao assignedto = userRepository.findById((int) outgoingActivity.getAssignedto())
						.orElseThrow(() -> new ResourceNotFoundException(
								"AssignedTo Id :" + outgoingActivity.getAssignedto() + " Unavailable"));
			}
		} else if (!outgoingAssigned3.isEmpty()) {
			if (outgoingActivity.getAssignedto() == 0) {
				throw new ResourceNotFoundException("AssignedTo Id :" + outgoingActivity.getAssignedto()
						+ " Not Allow Please Enter valid AssignedTo Id");
			} else if (outgoingActivity.getAssignedto() != 0) {
				UserDao assignedto = userRepository.findById((int) outgoingActivity.getAssignedto())
						.orElseThrow(() -> new ResourceNotFoundException(
								"AssignedTo Id :" + outgoingActivity.getAssignedto() + " Unavailable"));
			}

			if (outgoingActivity.getFromId() == 0) {
				throw new ResourceNotFoundException(
						"From Id :" + outgoingActivity.getFromId() + " Not Allow Please Enter valid From Id");

			} else if (outgoingActivity.getFromId() != 0) {
				UserDao fromId = userRepository.findById((int) outgoingActivity.getFromId())
						.orElseThrow(() -> new ResourceNotFoundException(
								"FromId :" + outgoingActivity.getFromId() + " Unavailable"));
			}

		}

		String message=this.getmessage(outgoingActivity);
		
		outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
		outgoingAssigned2.setFromId(outgoingActivity.getFromId());
		outgoingAssigned2.setGroup("You");
		outgoingAssigned2.setMessage(message);
		outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
		outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
		outgoingAssigned2.setIsopen(outgoingActivity.getIsopen());
		outgoingAssigned2.setTime(LocalDateTime.now());
		outgoingAssigned2.setType("Activity");
		
		
		latestMessageListShowService.updateAssignedto(outgoingActivity.getMobileNo(),outgoingActivity.getAssignedto());
		
		
		return outgoingActivityRepository.save(outgoingAssigned2);
	}

	public OutgoingActivity saveOutgoingClosed(OutgoingActivity outgoingActivity) {

		OutgoingActivity outgoingAssigned2 = new OutgoingActivity();

		if (outgoingActivity.getFromId() != 0) {
			UserDao fromId = userRepository.findById((int) outgoingActivity.getFromId()).orElseThrow(
					() -> new ResourceNotFoundException("FromId :" + outgoingActivity.getFromId() + " Unavailable"));
		}

		String message=this.getmessage(outgoingActivity);
		outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
		outgoingAssigned2.setFromId(outgoingActivity.getFromId());
		outgoingAssigned2.setGroup("You");
		outgoingAssigned2.setMessage(message);
		outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
		outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
		outgoingAssigned2.setIsopen(outgoingActivity.getIsopen());
		outgoingAssigned2.setType("Activity");
		return outgoingActivityRepository.save(outgoingAssigned2);
	}

	public OutgoingActivity saveOutgoingOpen(OutgoingActivity outgoingActivity) {

//		OutgoingActivity outgoingAssigned3 = new OutgoingActivity();
		LatestMessageListShow latestMessageListShow = latestMessageListShowService
				.findbymobilenumber(outgoingActivity.getMobileNo());
		
//		OutgoingActivity savedOutgoingAssigned = 
		
		OutgoingActivity savedOutgoingAssigned = null;
		OutgoingActivity outgoingAssigned2 = null;
		if (latestMessageListShow != null) {
			
			if (outgoingActivity.getFromId() != 0) {

				String message=this.getmessage(outgoingActivity);
			
				OutgoingActivity savedOutgoingAssigned2=outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(outgoingActivity.getMobileNo());

				if (savedOutgoingAssigned2 != null && savedOutgoingAssigned2.getIsopen() == false ) {
					if (savedOutgoingAssigned2.getAssignedto() != 0) {
						outgoingAssigned2 = new OutgoingActivity();
//						outgoingAssigned2.setId(savedOutgoingAssigned2.getId());
						outgoingAssigned2.setAssignedto(savedOutgoingAssigned2.getAssignedto());
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(message);
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(true);
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");
					} else {
						
						outgoingAssigned2 = new OutgoingActivity();
//						outgoingAssigned2.setId(savedOutgoingAssigned2.getId());
						outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(message);
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(true);
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");

					}
				} else {
				outgoingAssigned2 = new OutgoingActivity();
				outgoingAssigned2.setId(savedOutgoingAssigned2.getId());
				outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
				outgoingAssigned2.setFromId(outgoingActivity.getFromId());
				outgoingAssigned2.setGroup("You");
				outgoingAssigned2.setMessage(message);
				outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
				outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
				outgoingAssigned2.setIsopen(true);
				outgoingAssigned2.setTime(LocalDateTime.now());
				outgoingAssigned2.setType("Activity");
//
				}
			} else {
				throw new ResourceNotFoundException("From Id :" + outgoingActivity.getFromId() + " Not Allow Please Enter valid From Id");
			}

			savedOutgoingAssigned = outgoingActivityRepository.save(outgoingAssigned2);
			if (savedOutgoingAssigned.getMessagetype().equals("open")) {
				LatestMessageListShow latestMessageListShow1 = latestMessageListShowService
						.findbymobilenumber(savedOutgoingAssigned.getMobileNo());
				latestMessageListShowService.updateclosed(latestMessageListShow1, savedOutgoingAssigned.getTime(),savedOutgoingAssigned.getIsopen());
			}

		} else {
			throw new ResourceNotFoundException("Mobile number does not exist: " + outgoingActivity.getMobileNo());
		}

		return savedOutgoingAssigned;
	}
	
	
	
	public OutgoingActivity saveIncomingOpen(OutgoingActivity outgoingActivity) {

//		OutgoingActivity outgoingAssigned3 = new OutgoingActivity();
		LatestMessageListShow latestMessageListShow = latestMessageListShowService
				.findbymobilenumber(outgoingActivity.getMobileNo());
		OutgoingActivity savedOutgoingAssigned = null;
		OutgoingActivity outgoingAssigned2 = null;
//		if (latestMessageListShow != null) {
			if (outgoingActivity.getFromId() != 0) {

				String message=this.getmessage(outgoingActivity);
			
				OutgoingActivity savedOutgoingAssigned2=outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(outgoingActivity.getMobileNo());

				if (savedOutgoingAssigned2 != null) {
					if (savedOutgoingAssigned2.getAssignedto() != 0) {
						outgoingAssigned2 = new OutgoingActivity();
						outgoingAssigned2.setAssignedto(savedOutgoingAssigned2.getAssignedto());
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(message);
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(outgoingActivity.getIsopen());
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");
					} else {

						outgoingAssigned2 = new OutgoingActivity();
						outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(message);
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(outgoingActivity.getIsopen());
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");

					}
				} else {
				outgoingAssigned2 = new OutgoingActivity();
				outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
				outgoingAssigned2.setFromId(outgoingActivity.getFromId());
				outgoingAssigned2.setGroup("You");
				outgoingAssigned2.setMessage(message);
				outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
				outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
				outgoingAssigned2.setIsopen(outgoingActivity.getIsopen());
				outgoingAssigned2.setTime(LocalDateTime.now());
				outgoingAssigned2.setType("Activity");

				}
//			} else {
//				throw new ResourceNotFoundException("From Id :" + outgoingActivity.getFromId() + " Not Allow Please Enter valid From Id");
//			}

			savedOutgoingAssigned = outgoingActivityRepository.save(outgoingAssigned2);
			if (savedOutgoingAssigned.getMessagetype().equals("open")) {
				LatestMessageListShow latestMessageListShow1 = latestMessageListShowService
						.findbymobilenumber(savedOutgoingAssigned.getMobileNo());
				latestMessageListShowService.updateclosed(latestMessageListShow1, savedOutgoingAssigned.getTime(),savedOutgoingAssigned.getIsopen());
			}

		} else {
			throw new ResourceNotFoundException("Mobile number does not exist: " + outgoingActivity.getMobileNo());
		}

		return savedOutgoingAssigned;
	}

	
	
	public String getmessage(OutgoingActivity outgoingActivity ) {
		
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

		String message = null;

		if (outgoingActivity.getMessagetype().equals("assigned")) {
			if (outgoingActivity.getFromId() == 0) {
				
				message = "This conversation was assigned to " + assignedtoname ;
			} else {
				
				message = "This conversation was assigned from " + fromname + " to " + assignedtoname ;
			}
		} else if (outgoingActivity.getMessagetype().equals("closed")) {
			
			message = "This chat was closed by "+fromname;
		}else if (outgoingActivity.getMessagetype().equals("open")) {
			
			message = "This chat was open by "+fromname;
		}else if (outgoingActivity.getMessagetype().equals("incomingopen")) {
			
			message = "This chat was open by Customer";
		}
		
		
		return message;
	}
	
	

//	findFirstByMobileNoAndMessagetypeOrderByTimeDesc
	
	public OutgoingActivity getFirstByMobileNoAndMessagetype(String number,String messagetype ) {
		return outgoingActivityRepository.findFirstByMobileNoAndMessagetypeOrderByTimeDesc(number,messagetype);
	}
	
	public OutgoingActivity getFirstByMobileNo(String number) {
		return outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(number);
	}
	
	public List<OutgoingActivity> getAllOutgoingAssigned() {
		return outgoingActivityRepository.findAll();
	}

	public Optional<OutgoingActivity> getOutgoingAssignedById(long id) {
		return outgoingActivityRepository.findById(id);
	}

	public void deleteOutgoingAssigned(long id) {
		outgoingActivityRepository.deleteById(id);
	}
}
