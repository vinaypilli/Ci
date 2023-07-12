package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.CompanyDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Company;
import com.teamcomputers.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController<T> {

	@Autowired
	private CompanyService companyService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody CompanyDto companyDto) {

		try {

			this.companyService.add(companyDto);
			message = "Company Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplivcate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			message = "Company details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	@GetMapping("/{companyId}")
	private Company getById(@PathVariable long companyId) {
		return companyService.getById(companyId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
	@GetMapping
	private List<Company> getAll() {
		return companyService.getAll();
	}

	@PutMapping("/{companyId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long companyId, @RequestBody CompanyDto CompanyDto) {

		String message = "";

		try {
			CompanyDto.setCompanyId(companyId);
			this.companyService.update(CompanyDto);
			message = "Company Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Company details are not updated" ;
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
//	@PutMapping("/{customerId}")
//	private String update(@PathVariable int customerId,@RequestBody CustomerDto customerDto) {
//		customerDto.setCustomerId(customerId);
//		this.customerService.update(customerDto);
//		return "Customer Details successfully Updated !!";
//	}
	
	@DeleteMapping("/{companyId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long companyId) {
		try {
			companyService.deleteById(companyId);
			message = "Customer Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Customer details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
}
