package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompanyName(String companyName);
	 Company findByApiKey(String apiKey);
	 Company findByCompanyNameAndCompanyIdNot(String companyName, Long companyId);
	 Company findByApiKeyAndCompanyIdNot(String apiKey, Long companyId);
}
