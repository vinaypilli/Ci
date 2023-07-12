package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Category;
import com.teamcomputers.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>{
	
	List<SubCategory> findByStatus(boolean b);
	//List<SubCategory> findAllByOrderBySubCategoryNameAsc();

	SubCategory findBySubCategoryName(String subCategoryName);
	
	List<SubCategory> findByCategoryAndStatusTrueOrderBySubCategoryNameAsc(Category category);


}
