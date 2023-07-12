package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	//List<Category> findAllByOrderByCategoryNameAsc();
	List<Category> findByStatus(boolean status);

	List<Category> findCategoryIdAndCategoryNameByStatusOrderByCategoryNameAsc(boolean b);
}
