package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.CompanyDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Company;
import com.teamcomputers.repository.CompanyRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private UserRepository userRepository;


	public Company add(CompanyDto companyDto) {

		Company company = new Company();

		company.setCompanyId(companyDto.getCompanyId());
		company.setCompanyName(companyDto.getCompanyName());
		company.setApiKey(companyDto.getApiKey());
		company.setCompanyDescription(companyDto.getCompanyDescription());
		company.setCreatedBy(userRepository.findById((int)(companyDto.getCreatedBy())).orElse(null));
		company.setCreatedDate(companyDto.getCreatedDate());
		company.setUpdatedBy(userRepository.findById((int)(companyDto.getUpdatedBy())).orElse(null));
		company.setUpdatedDate(companyDto.getUpdatedDate());
		company.setStatus(companyDto.isStatus());

		return companyRepository.save(company);
	}
	public Company getById(long companyId) {
		return companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id :" + companyId + "Unavailable"));
	}
	public Company update(CompanyDto companyDto) {

		Company company = new Company();

		company.setCompanyId(companyDto.getCompanyId());
		company.setCompanyName(companyDto.getCompanyName());
		company.setApiKey(companyDto.getApiKey());
		company.setCompanyDescription(companyDto.getCompanyDescription());
		company.setCreatedBy(userRepository.findById((int)(companyDto.getCreatedBy())).orElse(null));
		company.setCreatedDate(companyDto.getCreatedDate());
		company.setUpdatedBy(userRepository.findById((int)(companyDto.getUpdatedBy())).orElse(null));
		company.setUpdatedDate(companyDto.getUpdatedDate());
		company.setStatus(companyDto.isStatus());


		return companyRepository.save(company);
	}
	public List<Company> getAll() {
		return companyRepository.findAll();
	}


	
	public boolean deleteById(long companyId) throws NotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("customer Id : " + companyId + " is Not Present in DataBase"));
		company.setStatus(false); // Update status to false
		companyRepository.save(company);
		return true;
	}

}
