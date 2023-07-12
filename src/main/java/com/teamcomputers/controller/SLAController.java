package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.SLAFilterDto;
import com.teamcomputers.dto.SLATimelinesDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.SLATimelines;
import com.teamcomputers.service.SLATimelinesService;

@RestController
@RequestMapping("/SLA")
public class SLAController {

	@Autowired
	private SLATimelinesService slaTimelinesService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody SLATimelinesDto slaTimelinesDto) {

		try {

			this.slaTimelinesService.add(slaTimelinesDto);
			message = "SLA Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "SLA details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{id}")
	private SLATimelines getById(@PathVariable long id) {
		return slaTimelinesService.getById(id);
	}

	@GetMapping("/active")
	public List<SLAFilterDto> getActiveUsers() {
		return slaTimelinesService.getActiveUsers();
	}

	@GetMapping
	private List<SLATimelines> getAll() {
		return slaTimelinesService.getAll();
	}

	@PutMapping("/{id}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long id,
			@RequestBody SLATimelinesDto slaTimelinesDto) {

		String message = "";

		try {
			slaTimelinesDto.setId(id);
			this.slaTimelinesService.update(slaTimelinesDto);
			message = "SLA Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SLA details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long id) {
		try {
			slaTimelinesService.deleteById(id);
			message = "SLA Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SLA details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("CATEGORY NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
}
