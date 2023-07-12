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

import com.teamcomputers.dto.ServiceFilterDto;
import com.teamcomputers.dto.ServicePriorityDto;
import com.teamcomputers.dto.ServiceTitleDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.service.ServiceTitleService;

@RestController
@RequestMapping("/servicetitle")
public class serviceEntityController {

	@Autowired
	private ServiceTitleService serviceTitleService;
	private Logger logger = Logg.getLogger(serviceEntityController.class);

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody ServiceTitleDto serviceTitleDto) {

		try {

			this.serviceTitleService.add(serviceTitleDto);
			message = "Service Details successfully saved !!";
			logger.info("Adding in serviceEntity controller class : {}", serviceTitleDto);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//      catch (DuplicateKeyException e) {
//          //System.out.println(e + "duplicate....");
//          message = "data is duplicate"+e.getCause().getMessage();
//          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//          } 
		catch (Exception e) {
			message = "Service details not saved";
			logger.error("Failed to add serviceEntity controller class: {}");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{serviceId}")
	private ServiceTitleEntity getById(@PathVariable int serviceId) {
		logger.info("Getting service by ID from controller : {}", serviceId);
		logger.error("Failed to get service by ID from controller class: {}. Error: {}");
		return serviceTitleService.getById(serviceId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping("/active")
	public List<ServiceFilterDto> getActiveUsers() {
		logger.info("Getting active service users from controller class");
		logger.error("Failed to get active service users from controller class. Error: {}");
		return serviceTitleService.getActiveUsers();
	}

	@GetMapping("/active/{subCategory}")
	public List<ServiceFilterDto> getAllActiveServiceTitlesBySubCategoryId(@PathVariable int subCategory) {
		try {
			logger.info("Getting all active service titles for subcategory in controller: {}", subCategory);
			return serviceTitleService.getAllActiveServiceTitlesBySubCategoryId(subCategory);
		} catch (Exception e) {
			logger.error("Failed to get all active service titles for subcategory {}. Error: {}", subCategory,
					e.getMessage());
			throw new RuntimeException("Failed to get active from serviceEntity controller class", e);

		}
	}

	@GetMapping
	private List<ServiceTitleEntity> getAll() {
		try {
			logger.info("Getting all service titles from controller class");
			return serviceTitleService.getAll();
		} catch (Exception e) {
			logger.error("Failed to get all service titles in controller class. Error: {}", e.getMessage());
			throw new RuntimeException("Failed to get all service titles from serviceEntity controller class", e);
		}
	}

	@GetMapping("/servicePriority/{serviceId}")
	public List<ServicePriorityDto> getPriorityNamesByServiceId(@PathVariable int serviceId) {
		try {
			logger.info("Getting priority names for service ID from controller class: {}", serviceId);
			return serviceTitleService.findPriorityNameByServiceId(serviceId);
		} catch (Exception e) {
			logger.error("Failed to get priority names for service ID {}. Error: {}", serviceId, e.getMessage());
			throw new RuntimeException("Failed to get priority names for service from serviceEntity controller class",
					e); // or handle the exception accordingly
		}
	}

	@PutMapping("/{serviceId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int serviceId,
			@RequestBody ServiceTitleDto serviceTitleDto) {

		String message = "";

		try {
			serviceTitleDto.setServiceId(serviceId);
			this.serviceTitleService.update(serviceTitleDto);
			message = "Service Details successfully Updated !!";
			logger.info("Updated service with ID in controller class: {}", serviceId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Service details are not updated";
			logger.error("Failed to update service with ID in servicetitle in controller class: {}. Error: {}",
					serviceId, e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/{serviceId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int serviceId) {
		try {
			serviceTitleService.deleteById(serviceId);
			message = "Service Details successfully deleted !!";
			logger.info("Deleted service with ID form controller class: {}");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Service details are not deleted" + e.getCause().getMessage();
			logger.error("Failed to delete service with ID from controller class: {}. Error: {}");

			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
}
