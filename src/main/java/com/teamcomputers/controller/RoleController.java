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

import com.teamcomputers.dto.RoleDto;
import com.teamcomputers.dto.RoleFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.RoleEntity;
import com.teamcomputers.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	private Logger logger=Logg.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody RoleDto roleDto) {

		try {
			 logger.info("add method called with roleDto from controller: {}", roleDto);
			this.roleService.add(roleDto);
			message = "Role Details successfully saved !!";
			logger.info("add method executed successfully in controller class");

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			 logger.error("Error occurred while saving role details in controller class", e);
			message = "Role details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/{roleId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int roleId, @RequestBody RoleDto roleDto) {

		String message = "";

		try {
			 logger.info("update method called with roleId from controller class: {}, roleDto: {}", roleId, roleDto);
			roleDto.setRoleId(roleId);
			this.roleService.update(roleDto);
			message = "Role Details successfully Updated !!";
			logger.info("update method executed successfully in controller class");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			 logger.error("Error occurred while updating role details from controller class", e);
			message = "Role details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{roleId}")
	private RoleEntity getById(@PathVariable int roleId) {
		try {
	        // Log the start of the method
	        logger.info("getById method called with roleId from controller class: {}", roleId);

	        RoleEntity roleEntity = roleService.getById(roleId);

	        // Log the successful completion of the method
	        logger.info("getById method executed successfully. RoleEntity in controller class: {}", roleEntity);
	        return roleService.getById(roleId);
	        
	    } catch (Exception e) {
	        // Log the exception and error message
	        logger.error("Error occurred while retrieving role by ID from contrller class: {}", roleId, e);

	        throw new RuntimeException(e); // Rethrow the exception or handle it according to your needs
	    }
		
	}

	@GetMapping
	private List<RoleEntity> getAll() {
		try {
	        // Log the start of the method
	        logger.info("getAll method called from controller class");

	        List<RoleEntity> roleEntities = roleService.getAll();

	        // Log the successful completion of the method
	        logger.info("getAll method executed successfully. Retrieved {} role entities in controller class", roleEntities.size());
	        return roleService.getAll();
	      
	    } catch (Exception e) {
	        // Log the exception and error message
	        logger.error("Error occurred while retrieving all role entities from controller class", e);

	        throw new RuntimeException(e); // Rethrow the exception or handle it according to your needs
	    }
		
	}

	@GetMapping("/active")
	public List<RoleFilterDto> getActiveUsers() {
		 try {
		        // Log the start of the method
		        logger.info("getActiveUsers method called from controller class");

		        List<RoleFilterDto> activeUsers = roleService.getActiveUsers();

		        // Log the successful completion of the method
		        logger.info("getActiveUsers method executed successfully. Retrieved {} active users from controller class", activeUsers.size());
               return roleService.getActiveUsers();
		       // return activeUsers;
		    } catch (Exception e) {
		        // Log the exception and error message
		        logger.error("Error occurred while retrieving active users in controller class", e);

		        throw new RuntimeException(e); // Rethrow the exception or handle it according to your needs
		    }
		
	}

	@DeleteMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int customerId) {
		try {
			// Log the start of the method
	        logger.info("delete method called with customerId from controller class: {}", customerId);

			roleService.deleteById(customerId);
			message = "Role Details successfully deleted !!";
			logger.info("delete method executed successfully in controller class");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		} catch (Exception e) {
			 logger.error("Error occurred while deleting role details in controller class", e);
			message = "Role details are not deleted";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

}