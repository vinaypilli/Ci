package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.teamcomputers.dto.CategoryDto;
import com.teamcomputers.dto.CategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.Category;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class CategoryService {
	private Logger logger=Logg.getLogger(CategoryService.class);
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Category add(CategoryDto categoryDto) {
		try {
			
		
		
	    Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCreatedBy(userRepository.findById((int)(categoryDto.getCreatedBy())).orElse(null));
		category.setCreatedDate(categoryDto.getCreatedDate());
		category.setUpdatedBy(userRepository.findById((int)(categoryDto.getUpdatedBy())).orElse(null));
		category.setUpdatedDate(categoryDto.getUpdatedDate());
		category.setStatus(categoryDto.isStatus());
		
		Category savedCategory = categoryRepository.save(category);
		logger.info("Adding category from service: {}", categoryDto.getCategoryName());
		logger.info("Category added successfully: {}", categoryDto);
		//return categoryRepository.save(category);
		 return savedCategory;
		}catch(Exception e) {
			
			logger.error("Error occurred while adding a category from service: {}", e.getMessage());
	        throw new RuntimeException("Failed to add category");
		}
	}

	public Category getById(long customerId) {
		  Logger logger = LoggerFactory.getLogger(getClass());
		  logger.info("Getting category with ID service category: {}", customerId);
		return categoryRepository.findById(customerId)
				.orElseThrow(() -> {
					logger.error("Category with ID {} not found in category service", customerId);
					 return new ResourceNotFoundException("Customer Id : " + customerId + " Unavailable");
				//new ResourceNotFoundException("Customer Id : " + customerId + " Unavailable")
	});
	}

	public List<Category> getAll() {
      Logger logger = LoggerFactory.getLogger(getClass());

		    logger.info("Getting all categories service class");

		    return categoryRepository.findAll();
		
		//return categoryRepository.findAll();
	}

	public List<CategoryFilterDto> getActiveUsers() {
		 Logger logger = LoggerFactory.getLogger(getClass());
	     logger.info("Fetching active users categoryservice class");
  List<Category> categories = categoryRepository.findCategoryIdAndCategoryNameByStatusOrderByCategoryNameAsc(true);
  List<CategoryFilterDto> filteredCategories = new ArrayList<>();

    for (Category category : categories) {
	     CategoryFilterDto filteredCategory = new CategoryFilterDto();
         filteredCategory.setCategoryId(category.getCategoryId());
         filteredCategory.setCategoryName(category.getCategoryName());
         // filteredCategory.setStatus(category.isStatus());
         filteredCategories.add(filteredCategory);
  }
    logger.info("Fetched {} active users category service class", filteredCategories.size());
          return filteredCategories;
}
	    public Category update(CategoryDto categoryDto) {
	    	 Logger logger = LoggerFactory.getLogger(getClass());
	    	 logger.info("Updating category");
		Category category = new Category();
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCreatedBy(userRepository.findById((int)(categoryDto.getCreatedBy())).orElse(null));
		category.setCreatedDate(categoryDto.getCreatedDate());
		category.setUpdatedBy(userRepository.findById((int)(categoryDto.getUpdatedBy())).orElse(null));
		category.setUpdatedDate(categoryDto.getUpdatedDate());
		category.setStatus(categoryDto.isStatus());

		return categoryRepository.save(category);
	}	
	
	public boolean deleteById(long categoryId) throws NotFoundException {
		Logger logger = LoggerFactory.getLogger(getClass());
	    logger.info("Deleting category with ID: {} from category service", categoryId);

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> {
	                logger.error("Category with ID {} not found", categoryId);
	                return new ResourceNotFoundException("category Id : " + categoryId + " Unavailable");
	            });

	    category.setStatus(false); // Update status to false
	    categoryRepository.save(category);

	    logger.info("Category with ID {} deleted successfully", categoryId);
	    return true;
				//.orElseThrow(() -> new ResourceNotFoundException("category Id : " + categoryId + " Unavailable"));
		//category.setStatus(false); // Update status to false
       // categoryRepository.save(category);
		//return true;
	}
	
//	public boolean deleteById(long id) {
//		categoryRepository.deleteById(id);
//		return true;
//	}

}
