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

import com.teamcomputers.dto.SubCategoryDto;
import com.teamcomputers.dto.SubCategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Category;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.service.SubCategoryService;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
	private Logger logger=Logg.getLogger(SubCategoryController.class);

	@Autowired
	private SubCategoryService subCategoryService;

	String message = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody SubCategoryDto subCategoryDto) {

		try {

			subCategoryService.add(subCategoryDto);
		    logger.info("Adding subcategory data contoller: {}", subCategoryDto);
	        String message = "SubCategory Details successfully saved  contoller!!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			// return ResponseEntity.ok(subCategoryService.add(subCategoryDto));

		}

//			catch (DuplicateKeyException e) {
//				//System.out.println(e + "duplicate....");
//				message = "data is duplicate"+e.getCause().getMessage();
//				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//				} 
		catch (Exception e) {
			logger.error("Failed to add subcategory contoller: {}", e.getMessage());
			String message = "SubCategory details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/active/{category}")
	public List<SubCategoryFilterDto> getAllActiveSubcategoriesByCategoryId(@PathVariable Long category) {
		  try {
		        logger.info("Fetching all active subcategories for category contoller: {}", category);
		        return subCategoryService.getAllActiveSubcategoriesByCategoryId(category);
		    } catch (Exception e) {
		        logger.error("Failed to fetch active subcategories for category contoller: {}. Error: {}", category, e.getMessage());
		        throw e; // or handle the exception accordingly
		    }
		//return subCategoryService.getAllActiveSubcategoriesByCategoryId(category);
	}

	@GetMapping("/{subCategoryId}")
	private SubCategory getById(@PathVariable int subCategoryId) {
		 try {
		        logger.info("Fetching subcategory by ID controller: {}", subCategoryId);
		        return subCategoryService.getById(subCategoryId);
		    } catch (Exception e) {
		        logger.error("Failed to fetch subcategory by ID controller: {}. Error: {}", subCategoryId, e.getMessage());
		        throw e; // or handle the exception accordingly
		    }
		//return subCategoryService.getById(subCategoryId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping
	private List<SubCategory> getAll() {
		 try {
		        logger.info("Fetching all subcategories data form controller");
		        return subCategoryService.getAll();
		    } catch (Exception e) {
		        logger.error("Failed to fetch all subcategories form controller. Error: {}", e.getMessage());
		        throw e; // or handle the exception accordingly
		    }
			
		//return subCategoryService.getAll();
	}

	@GetMapping("/active")
	public List<SubCategoryFilterDto> getActiveUsers() {
		 try {
		        logger.info("Fetching active users form controller");
		        return subCategoryService.getActiveUsers();
		    } catch (Exception e) {
		        logger.error("Failed to fetch active users form controller. Error: {}", e.getMessage());
		        throw new RuntimeException( e); // or handle the exception accordingly
		    }
		
		//return subCategoryService.getActiveUsers();
	}

	@PutMapping("/{subCategoryId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int subCategoryId,
			@RequestBody SubCategoryDto subCategoryDto) {

		String message = "";

		try {
	        subCategoryDto.setSubCategoryId(subCategoryId);
	        this.subCategoryService.update(subCategoryDto);
	        message = "SubCategory Details successfully Updated !!";
	        logger.info("Updated subcategory with ID in controller: {}", subCategoryId);
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	        message = "SubCategory details are not updated in controller";
	        logger.error("Failed to update subcategory with ID in controller: {}. Error: {}", subCategoryId, e.getMessage());
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }

	}
//		@PutMapping("/{subCategoryId}")
//		private String update(@PathVariable int subCategoryId,@RequestBody SubCategoryDto subCategoryDto) {
//			subCategoryDto.setSubCategoryId(subCategoryId);
//			this.subCategoryService.update(subCategoryDto);
//			return "SubCategory Details successfully Updated !!";
//		}

	@DeleteMapping("/{subCategoryId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int subCategoryId) {
		 try {
		        subCategoryService.deleteById(subCategoryId);
		        String message = "SubCategory Details successfully deleted in controller!!";
		        logger.info("Deleted subcategory with ID in contoller: {}", subCategoryId);
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		    } catch (Exception e) {
		        String message = "SubCategory details are not deleted in contoller" + e.getCause().getMessage();
		        logger.error("Failed to delete subcategory with ID in  contoller: {}. Error: {}", subCategoryId, e.getMessage());
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }
}
}
