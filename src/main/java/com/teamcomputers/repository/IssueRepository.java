package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{
	

	List<Issue> findByStatus(boolean b);
	//List<SubCategory> findAllByOrderBySubCategoryNameAsc();

	Issue findByIssueName(String issueName);
	
	//List<Issue> findByCategoryAndStatusTrueOrderByIssueNameAsc(Category category);

//	List<Issue> findAllByOrderByIssueIDAsc();

}
