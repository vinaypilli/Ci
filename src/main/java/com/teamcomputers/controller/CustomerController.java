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

import com.teamcomputers.dto.CustomerDto;
import com.teamcomputers.dto.CustomerFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Customer;
import com.teamcomputers.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private Logger logger=Logg.getLogger( CustomerController.class);
	@Autowired
	private CustomerService customerService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody CustomerDto customerDto) {

		try {
		     logger.debug("Adding customer details in controller class...");
			this.customerService.add(customerDto);
			message = "Customer Details successfully saved !!";
			logger.info("Customer details saved successfully in controller class.");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplivcate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			 logger.error("Failed to save customer details in controller class.", e);
			message = "Customer details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{customerId}")
	private Customer getById(@PathVariable int customerId) {
		try
		{
			 // Log a debug message
	        logger.debug("Fetching customer with ID from controller class: {}", customerId);
	        // Log an info message
	        logger.info("Customer with ID: {} fetched successfully from controller class.", customerId);
	        return customerService.getById(customerId);
		 } catch (Exception e) {
		        // Log an error message
		        logger.error("Failed to fetch customer with ID from controller class: {}", customerId, e);
               throw new RuntimeException(e);
		    }
		
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping
	private List<Customer> getAll() {
		 try {
		        // Log a debug message
		        logger.debug("Fetching all customers from controller class...");

		        List<Customer> customers = customerService.getAll();
		        // Log an info message
		        logger.info("Fetched {} customers successfully from controller class.", customers.size());
		        return customerService.getAll();
		 } catch (Exception e) {
		        // Log an error message
		        logger.error("Failed to fetch customers from controller class.", e);
		        throw new RuntimeException(e);
		 }
	}

	@GetMapping("/active")
	public List<CustomerFilterDto> getActiveUsers() {
		try
		{
				logger.info("Fetching  all active users successfully from controller class...");
			    return customerService.getActiveUsers();
		  } catch (Exception e) {
		        // Log an error message
		        logger.error("Failed to fetch active users from controller class.", e);
		        throw new RuntimeException(e);
		 }
	}

	@PutMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int customerId, @RequestBody CustomerDto customerDto) {

		String message = "";

		try {
			 logger.debug("Updating customer details for ID in controller class: {}", customerId);
			customerDto.setCustomerId(customerId);
			this.customerService.update(customerDto);
			message = "Customer Details successfully Updated !!";
			// Log an info message
	        logger.info("Customer details updated successfully for ID in controller class: {}", customerId);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			logger.error("Failed to update customer details for ID in controller class: {}", customerId, e);
			message = "Customer details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
//	@PutMapping("/{customerId}")
//	private String update(@PathVariable int customerId,@RequestBody CustomerDto customerDto) {
//		customerDto.setCustomerId(customerId);
//		this.customerService.update(customerDto);
//		return "Customer Details successfully Updated !!";
//	}

	@DeleteMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int customerId) {
		try {
			// Log a debug message
	        logger.debug("Deleting customer details for ID in controller class: {}", customerId);
			customerService.deleteById(customerId);
			message = "Customer Details successfully deleted !!";
			// Log an info message
	        logger.info("Customer details deleted successfully for ID in controller class: {}", customerId);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			// Log an error message
	        logger.error("Failed to delete customer details for ID in controller class: {}", customerId, e);
			message = "Customer details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

}
