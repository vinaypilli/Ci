package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.SubCategoryDto;
import com.teamcomputers.dto.SubCategoryFilterDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.Category;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class SubCategoryService {
	private Logger logger=Logg.getLogger(SubCategoryService.class);
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;

	public SubCategory add(SubCategoryDto subCategoryDto) throws DuplicateUserException {
		try {
	     SubCategory userDup = subCategoryRepository.findBySubCategoryName(subCategoryDto.getSubCategoryName());
	        if (userDup != null) {
	            throw new DuplicateUserException("Username already exists");
	        }

	        SubCategory subCategory = new SubCategory();

	        subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
	        subCategory.setCreatedBy(userRepository.findById((int)(subCategoryDto.getCreatedBy())).orElse(null));
	        subCategory.setCreatedDate(subCategoryDto.getCreatedDate());
	        subCategory.setUpdatedBy(userRepository.findById((int)(subCategoryDto.getUpdatedBy())).orElse(null));
	        subCategory.setUpdatedDate(subCategoryDto.getUpdatedDate());
	        subCategory.setStatus(subCategoryDto.isStatus());
	        subCategory.setCategory(categoryRepository.findById((long) subCategoryDto.getCategoryId()).orElse(null));

	        return subCategoryRepository.save(subCategory);
	    } catch (Exception e) {
	        logger.error("Failed to add subcategory in service: {}", e.getMessage());
	        throw e; // or handle the exception accordingly
	    }
		
		//return subCategoryRepository.save(subCategory);
	}

	public SubCategory getById(int subCategoryId) {
		 try {
		        return subCategoryRepository.findById(subCategoryId)
		                .orElseThrow(() -> new ResourceNotFoundException("SubCategory Id :" + subCategoryId + "Unavailable"));
		    
		    } catch (Exception e) {
		        logger.error("Failed to get subcategory by ID from service class : {}. Error: {}", subCategoryId, e.getMessage());
		        throw e; // or handle the exception accordingly
		    }	
	}

	public List<SubCategory> getAll() {
		try {
	        logger.info("Fetching all subcategories from service class");
	        return subCategoryRepository.findAll();
	    } catch (Exception e) {
	        logger.error("Failed to fetch all subcategories. Error: {}", e.getMessage());
	        throw e; // or handle the exception accordingly
	    }
			
	}

	public List<SubCategoryFilterDto> getActiveUsers() {
		try {
	        logger.info("Fetching active users form subcategory service class");
	        List<SubCategory> subCategories = subCategoryRepository.findByStatus(true);
	        List<SubCategoryFilterDto> filteredCategories = new ArrayList<>();

	        for (SubCategory subcategory : subCategories) {
	            SubCategoryFilterDto filteredCategory = new SubCategoryFilterDto();

	            filteredCategory.setSubCategoryId(subcategory.getSubCategoryId());
	            filteredCategory.setSubCategoryName(subcategory.getSubCategoryName());
	            filteredCategories.add(filteredCategory);
	        }

	        return filteredCategories;
	    } catch (Exception e) {
	        logger.error("Failed to fetch active users. Error from service class: {}", e.getMessage());
	        throw e; // or handle the exception accordingly
	    }	
		
	}

	public List<SubCategoryFilterDto> getAllActiveSubcategoriesByCategoryId(Long categoryId) {
		 try {
		        logger.info("Fetching all active subcategories by category ID from service class: {}", categoryId);
		        Category category = new Category();
		        category.setCategoryId(categoryId);
		        List<SubCategory> subCategories = subCategoryRepository
		                .findByCategoryAndStatusTrueOrderBySubCategoryNameAsc(category);
		        List<SubCategoryFilterDto> filteredCategories = new ArrayList<>();

		        for (SubCategory subcategory : subCategories) {
		            SubCategoryFilterDto filteredCategory = new SubCategoryFilterDto();

		            filteredCategory.setSubCategoryId(subcategory.getSubCategoryId());
		            filteredCategory.setSubCategoryName(subcategory.getSubCategoryName());
		            filteredCategories.add(filteredCategory);
		        }

		        return filteredCategories;
		    } catch (Exception e) {
		        logger.error("Failed to fetch all active subcategories by category ID from service class: {}. Error: {}", categoryId, e.getMessage());
		        throw e; // or handle the exception accordingly
		    }
		}
		
	
	public SubCategory update(SubCategoryDto subCategoryDto) {
		try {
	    logger.info("Updating subcategory in service class : {}", subCategoryDto);
        SubCategory subCategory = new SubCategory();
       
        subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
        subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
        subCategory.setCreatedBy(userRepository.findById((int)(subCategoryDto.getCreatedBy())).orElse(null));
        subCategory.setCreatedDate(subCategoryDto.getCreatedDate());
        subCategory.setUpdatedBy(userRepository.findById((int)(subCategoryDto.getUpdatedBy())).orElse(null));
        subCategory.setUpdatedDate(subCategoryDto.getUpdatedDate());
        subCategory.setStatus(subCategoryDto.isStatus());
        subCategory.setCategory(categoryRepository.findById((long) subCategoryDto.getCategoryId()).orElse(null));
       
        return subCategoryRepository.save(subCategory);
        
    } catch (Exception e) {
        logger.error("Failed to update subcategory. Error in service class: {}", e.getMessage());
        throw e; // or handle the exception accordingly
    }

	}

	public boolean deleteById(int id) throws NotFoundException {
		try {
	        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(
	                () -> new ResourceNotFoundException("SubCategory Id: " + id + " is Not Present in Database"));
	        subCategory.setStatus(false);
	        subCategoryRepository.save(subCategory);
	        logger.info("Status deactive subcategory with ID: {} from service class", id);
	        return true;
	    } catch (Exception e) {
	        logger.error("Failed to delete subcategory with ID service class: {}. Error: {}", id, e.getMessage());
	        throw new RuntimeException( e); // or handle the exception accordingly
	    }

	}
}
