package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	ProductCategory findByProductCategoryName(String productCategoryName);
	ProductCategory findByProductCategoryNameAndProductCategoryIdNot(String productCategoryName, long id);

	List<ProductCategory> findByStatusOrderByProductCategoryNameAsc(boolean b);
	
	ProductCategory findByFilename(String filename);
	
	List<ProductCategory> findByStatus(boolean status);
}
