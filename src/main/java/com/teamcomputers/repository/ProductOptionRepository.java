package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

	List<ProductOption> findByStatusOrderByOptionNameAsc(boolean b);

	ProductOption findByParentCategory(String parentCategory);

	ProductOption findByOptionName(String optionName);

	ProductOption findByParentCategoryAndIdNot(String parentCategory, Long id);

	ProductOption findByOptionNameAndIdNot(String optionName, Long id);

}
