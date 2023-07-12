package com.teamcomputers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.teamcomputers.dto.CategoryDto;
import com.teamcomputers.dto.CategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Category;
import com.teamcomputers.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	private Logger logger=Logg.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody CategoryDto categoryDto) {
	    try {
	        
	        
	        this.categoryService.add(categoryDto);
	        logger.info("Adding category from controller: {}", categoryDto);
	        String message = "Category details successfully saved!";
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	        logger.error("Failed to add category from controller: {}"+e, e.getMessage());
	        String message = "Category details not saved";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	}


	@GetMapping("/{categoryId}")
	public Category getById(@PathVariable long categoryId) {
		try {
			Category deatilsById=categoryService.getById(categoryId);
			logger.info("get category by id from the database");
			logger.debug("Retrieved {} category from the database");
			return deatilsById;
				
		}catch(Exception e) {
			logger.error("An error occurred while get category by Id: {}", e.getMessage());
	        throw new RuntimeException("Failed to get category by id", e);

		}
		
	}

	@GetMapping("/active")
	public List<CategoryFilterDto> getActiveUsers() {
		try {
			
			List<CategoryFilterDto> entities =categoryService.getActiveUsers();
			logger.info("Fetching all category from the database");
			logger.debug("Retrieved {} category from the database", entities.size());
			return entities;	
			
	}catch (Exception e) {
        logger.error("An error occurred while fetching category: {}", e.getMessage());
        throw new RuntimeException("Failed to fetch active category", e);
    }
	}

	@GetMapping
	private List<Category> getAll() {
		try {
			logger.info("Fetching all category from the database");
			
			List<Category> entities = categoryService.getAll();
			
			  logger.debug("Retrieved {} category from the database", entities.size());
			  return entities;
		 } catch (Exception e) {
		        logger.error("An error occurred while fetching category: {}", e.getMessage());
		        throw new RuntimeException("Failed to fetch category", e);
		    }
	}

	@PutMapping("/{categoryId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long categoryId,
			@RequestBody CategoryDto categoryDto) {

		String message = "";

		try {
			categoryDto.setCategoryId(categoryId);
			this.categoryService.update(categoryDto);
			logger.info("Update category by id from the database");
			message = "category Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			 logger.error("An error occurred while update category by id: {}", e.getMessage());
			message = "category details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@DeleteMapping("/{categoryId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long categoryId) {
		
			 Logger logger = LoggerFactory.getLogger(getClass());
			  String message;
			  try {
			        categoryService.deleteById(categoryId);
			        logger.info("Deleted category by ID: {}", categoryId);
			        logger.debug("Retrieved {} category from the database", categoryId);
			        message = "Category details successfully deleted!";
			        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			    } catch (Exception e) {
			        logger.error("An error occurred while deleting category by ID  controller{}: {}", categoryId, e.getMessage());
			        message = "Category details were not deleted. Error:controller " + e.getCause().getMessage();
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
