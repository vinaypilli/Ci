package com.teamcomputers.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.LatestMessageListShow;
import com.teamcomputers.model.OutgoingActivity;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.OutgoingActivityRepository;
import com.teamcomputers.service.LatestMessageListShowService;
import com.teamcomputers.service.OutgoingActivityService;

@RestController
@RequestMapping("/outgoing-activity")
public class OutgoingActivityController<T> {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OutgoingActivityService outgoingActivityService;
	
	@Autowired
	private OutgoingActivityRepository outgoingActivityRepository;

	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@PostMapping("/assigned")
	public ResponseEntity<?> saveOutgoingAssigned(@RequestBody OutgoingActivity outgoingActivity) {
		OutgoingActivity savedOutgoingAssigned = outgoingActivityService
				.getFirstByMobileNoAndMessagetype(outgoingActivity.getMobileNo(), outgoingActivity.getMessagetype());
		OutgoingActivity outgoingAssigned = null;

		LatestMessageListShow latestMessageListShow = latestMessageListShowService
				.findbymobilenumber(outgoingActivity.getMobileNo());
//		if (latestMessageListShow != null) {
		if (savedOutgoingAssigned != null) {
			OutgoingActivity outgoingAssigned2 = new OutgoingActivity();
			outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
			outgoingAssigned2.setFromId(outgoingActivity.getFromId());
			outgoingAssigned2.setGroup("You");
			outgoingAssigned2.setMessage(null);
			outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
			outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
			outgoingAssigned2.setIsopen(savedOutgoingAssigned.getIsopen());
			outgoingAssigned2.setTime(LocalDateTime.now());
			outgoingAssigned2.setType("Activity");
			outgoingAssigned = outgoingActivityService.saveOutgoingAssigned(outgoingAssigned2);
		} else {

			OutgoingActivity outgoingAssigned2 = new OutgoingActivity();
			outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
			outgoingAssigned2.setFromId(outgoingActivity.getFromId());
			outgoingAssigned2.setGroup("You");
			outgoingAssigned2.setMessage(null);
			outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
			outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
			outgoingAssigned2.setIsopen(true);
			outgoingAssigned2.setTime(LocalDateTime.now());
			outgoingAssigned2.setType("Activity");
			outgoingAssigned = outgoingActivityService.saveOutgoingAssigned(outgoingAssigned2);

		}
//		} else {
//			throw new ResourceNotFoundException("Mobile number does not exist: " + outgoingActivity.getMobileNo());
//		}
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				outgoingAssigned.getMessage(), ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);

//		return ResponseEntity.status(HttpStatus.CREATED).body(outgoingAssigned);
	}

	@PostMapping("/closed")
	public ResponseEntity<?> saveOutgoingClosed(@RequestBody OutgoingActivity outgoingActivity) {
		Customer customer = customerRepository.findByContact(outgoingActivity.getMobileNo());
		customer.setFlag(false);
		customerRepository.save(customer);
		LatestMessageListShow latestMessageListShow = latestMessageListShowService
				.findbymobilenumber(outgoingActivity.getMobileNo());
		OutgoingActivity savedOutgoingAssigned = null;
		OutgoingActivity outgoingAssigned2 = null;
		if (latestMessageListShow != null) {
			if (outgoingActivity.getFromId() != 0) {

//				OutgoingActivity savedOutgoingAssigned2 = outgoingActivityService.getFirstByMobileNoAndMessagetype(
//						outgoingActivity.getMobileNo(), outgoingActivity.getMessagetype());
//				
//				OutgoingActivity savedOutgoingAssigned2 = outgoingActivityService.getFirstByMobileNo(outgoingActivity.getMobileNo());

				OutgoingActivity savedOutgoingAssigned2 = outgoingActivityRepository.findFirstByMobileNoOrderByTimeDesc(outgoingActivity.getMobileNo());

				if (savedOutgoingAssigned2 != null) {
					if (savedOutgoingAssigned2.getAssignedto() != 0) {
						outgoingAssigned2 = new OutgoingActivity();
						outgoingAssigned2.setAssignedto(savedOutgoingAssigned2.getAssignedto());
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(null);
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(false);
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");
					} else {

						outgoingAssigned2 = new OutgoingActivity();
						outgoingAssigned2.setAssignedto(0);
						outgoingAssigned2.setFromId(outgoingActivity.getFromId());
						outgoingAssigned2.setGroup("You");
						outgoingAssigned2.setMessage(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
						outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
						outgoingAssigned2.setIsopen(false);
						outgoingAssigned2.setTime(LocalDateTime.now());
						outgoingAssigned2.setType("Activity");

					}
//				} else {
//				outgoingAssigned2 = new OutgoingActivity();
//				outgoingAssigned2.setAssignedto(outgoingActivity.getAssignedto());
//				outgoingAssigned2.setFromId(outgoingActivity.getFromId());
//				outgoingAssigned2.setGroup("You");
//				outgoingAssigned2.setMessage(outgoingActivity.getMessagetype());
//				outgoingAssigned2.setMessagetype(outgoingActivity.getMessagetype());
//				outgoingAssigned2.setMobileNo(outgoingActivity.getMobileNo());
//				outgoingAssigned2.setIsopen(false);
//				outgoingAssigned2.setTime(LocalDateTime.now());
//				outgoingAssigned2.setType("Sender");

				}
			} else {
				throw new ResourceNotFoundException("From Id :" + outgoingActivity.getFromId() + " Not Allow Please Enter valid From Id");
			}

			savedOutgoingAssigned = outgoingActivityService.saveOutgoingClosed(outgoingAssigned2);
			if (savedOutgoingAssigned.getMessagetype().equals("closed")) {
				LatestMessageListShow latestMessageListShow1 = latestMessageListShowService
						.findbymobilenumber(savedOutgoingAssigned.getMobileNo());
				latestMessageListShowService.updateclosed(latestMessageListShow1, savedOutgoingAssigned.getTime());

			}

		} else {
			throw new ResourceNotFoundException("Mobile number does not exist: " + outgoingActivity.getMobileNo());
		}

		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				savedOutgoingAssigned.getMessage(), ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<List<OutgoingActivity>> getAllOutgoingAssigned() {
		List<OutgoingActivity> outgoingAssignedList = outgoingActivityService.getAllOutgoingAssigned();
		return ResponseEntity.ok(outgoingAssignedList);
	}

	@GetMapping("/{mobileNo}")
	public OutgoingActivity getOutgoingAssignedById(@PathVariable String mobileNo) {
		return outgoingActivityService.getFirstByMobileNo(mobileNo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOutgoingAssigned(@PathVariable("id") long id) {
		outgoingActivityService.deleteOutgoingAssigned(id);
		return ResponseEntity.noContent().build();
	}
}
