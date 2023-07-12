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

import com.teamcomputers.dto.SLACategoryWiseDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.SLACategoryWise;
import com.teamcomputers.service.SLACategoryWiseService;

@RestController
@RequestMapping("/slaCatBy")
public class SLACategoryWiseController {

	@Autowired
	private SLACategoryWiseService slaCategoryWiseService;
	
	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody SLACategoryWiseDto slaCategoryWiseDto) {

		try {

			this.slaCategoryWiseService.add(slaCategoryWiseDto);
			message = "SLA (Catgory By) Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "SLA (Catgory By)details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	@GetMapping("/{sId}")
	private SLACategoryWise getById(@PathVariable long sId) {
		return slaCategoryWiseService.getById(sId);
	}


	@GetMapping
	private List<SLACategoryWise> getAll() {
		return slaCategoryWiseService.getAll();
	}
	
	@PutMapping("/{sId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long sId,
			@RequestBody SLACategoryWiseDto slaCategoryWiseDto) {

		String message = "";

		try {
			slaCategoryWiseDto.setsId(sId);
			this.slaCategoryWiseService.update(slaCategoryWiseDto);
			message = "SLA (Catgory By) Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SLA (Catgory By) details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	@DeleteMapping("/{sId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long sId) {
		try {
			slaCategoryWiseService.deleteById(sId);
			message = "SLA (Catgory By) Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SLA (Catgory By) details are not deleted" + e.getCause().getMessage();
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
