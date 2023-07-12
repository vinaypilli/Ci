package com.teamcomputers.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.teamcomputers.model.Product;
import com.teamcomputers.model.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByProductName(String productName);

	List<Product> findByStatusOrderByProductNameAsc(boolean b);
	
	List<Product> findByProductCategoryAndStatusOrderByProductNameAsc(ProductCategory productCategory, boolean status);
	
	

	Product findByProductNameAndProductIdNot(String productName, Long productId);

	Product findByFilename(String filename);
}
