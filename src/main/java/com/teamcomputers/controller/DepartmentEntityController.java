package com.teamcomputers.controller;

import java.util.List;

import org.slf4j.Logger;
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

import com.teamcomputers.dto.DepartmentDto;
import com.teamcomputers.dto.DepartmentfilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentEntityController {
	private Logger logger=Logg.getLogger(DepartmentEntityController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	
	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody DepartmentDto departmentDto) {

		try {
			logger.info("Department Details successfully saved in controller class!!");

			this.departmentService.add(departmentDto);
			message = "Department Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			logger.error("Department details not saved in controler class", e);
			message = "Department details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{departmentId}")
	private DepartmentEntity getById(@PathVariable int departmentId) {
		try
		{
			logger.info("Getting department by ID from controller class: {}", departmentId);
		return departmentService.getById(departmentId);
		} catch (Exception e) {
		    logger.error("Error getting department by ID from controller class: {}", departmentId, e);
		    throw new RuntimeException(e); 
		}
	}

	@GetMapping
	private List<DepartmentEntity> getAll() {
		try {
			
			logger.info("Fetching all departments from controller class");
		    return departmentService.getAll();
		} catch (Exception e) {
		    logger.error("Error fetching all departments from controller class", e);
		    throw new RuntimeException(e);
		}
	}

	@PutMapping("/{departmentId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int departmentId,
			@RequestBody DepartmentDto departmentDto) {

		String message = "";

		try {
			
			departmentDto.setDepartmentId(departmentId);
			this.departmentService.update(departmentDto);
			message = "Department Details successfully Updated !!";
			logger.info("Updating department with ID in controller class: {}", departmentId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			logger.error("Error updating department with ID in controller class: {}", departmentId, e);
			message = "Department details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	
	@GetMapping("/active")
    public List<DepartmentfilterDto> getActiveUsers() {
		try
		{
			logger.info("Fetching active users from controller class");	
		
        return departmentService.getActiveUsers();
		} catch (Exception e) {
		    logger.error("Error fetching active users from controller class", e);
		    throw new RuntimeException(e);
		}
    }

	@DeleteMapping("/{departmentId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int departmentId) {
		try {
			logger.info("Deleting department with ID from controller class: {}", departmentId);
			departmentService.deleteById(departmentId);
			message = "Department Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			 logger.error("Error deleting department with ID from controller class: {}", departmentId, e);
			message = "Department details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("Department NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
}