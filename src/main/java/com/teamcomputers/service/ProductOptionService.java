package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.ProductOptionDto;
import com.teamcomputers.dto.ProductOptionFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ConflictException;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ApiResponseGetAll;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.ProductOption;
import com.teamcomputers.repository.ProductOptionRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class ProductOptionService {

	@Autowired
	private ProductOptionRepository productOptionRepository;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> add(ProductOptionDto productOptionDto) {

		ProductOption userDup = productOptionRepository.findByParentCategory(productOptionDto.getParentCategory());
		if (userDup != null) {
			throw new DuplicateUserException("Parent category already exists");
		}

		ProductOption optionName = productOptionRepository.findByOptionName(productOptionDto.getOptionName());
		if (optionName != null) {
			throw new DuplicateUserException("Option name already exists");
		}

		ProductOption productOption = new ProductOption();

		productOption.setParentCategory(productOptionDto.getParentCategory());
		productOption.setOptionName(productOptionDto.getOptionName());
		productOption.setCreatedBy(userRepository.findById((int) (productOptionDto.getCreatedBy())).orElse(null));
		productOption.setCreatedDate(productOptionDto.getCreatedDate());
		productOption.setUpdatedBy(userRepository.findById((int) (productOptionDto.getUpdatedBy())).orElse(null));
		productOption.setUpdatedDate(productOptionDto.getUpdatedDate());
		productOption.setStatus(productOptionDto.isStatus());

		productOptionRepository.save(productOption);
//		 message = "request successful" ;
//       status=ErrorCode.Success.getMessage();
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	public ResponseEntity<?> getById(long id) {
		ProductOption productOption = productOptionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Opotion Id : " + id + " Unavailable"));
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(productOption);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	public ResponseEntity<ErrorResponse> update(ProductOptionDto productOptionDto) {

		Optional<ProductOption> userDup2 = productOptionRepository.findById(productOptionDto.getId());
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Id does not exist");
		}

		ProductOption userDup = productOptionRepository
				.findByParentCategoryAndIdNot(productOptionDto.getParentCategory(), productOptionDto.getId());
		if (userDup != null) {
			throw new ConflictException("Parent Category already exists");
		}
		ProductOption userDup1 = productOptionRepository.findByOptionNameAndIdNot(productOptionDto.getOptionName(),
				productOptionDto.getId());
		if (userDup1 != null) {
			throw new DuplicateUserException("Option name already exists");
		}

		ProductOption productOption = new ProductOption();
		productOption.setId(productOptionDto.getId());
		productOption.setParentCategory(productOptionDto.getParentCategory());
		productOption.setOptionName(productOptionDto.getOptionName());
		productOption.setCreatedBy(userRepository.findById((int) (productOptionDto.getCreatedBy())).orElse(null));
		productOption.setCreatedDate(productOptionDto.getCreatedDate());
		productOption.setUpdatedBy(userRepository.findById((int) (productOptionDto.getUpdatedBy())).orElse(null));
		productOption.setUpdatedDate(productOptionDto.getUpdatedDate());
		productOption.setStatus(productOptionDto.isStatus());

		productOptionRepository.save(productOption);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<ApiResponseGetAll<ProductOption>> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<ProductOption> productOption = productOptionRepository.findAll();
		Page<ProductOption> productOptionPage = productOptionRepository.findAll(pageable);
		List<ProductOption> productsList = productOptionPage.getContent();
		
		ApiResponseGetAll<ProductOption> response = new ApiResponseGetAll<>();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(productsList);
		response.setTotalPages(productOptionPage.getTotalPages());
		response.setCurrentPage(productOptionPage.getNumber());
		response.setTotalRecords((int) productOptionPage.getTotalElements());
		response.setPerPage(productOptionPage.getSize());

		if(productOption.isEmpty()) {
			response.setMessage("Data not found");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		if (page + 1 > productOptionPage.getTotalPages()) {
			throw new IllegalArgumentException("Invalid page number");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ErrorResponse> deleteById(long id) throws NotFoundException {
		ProductOption productOption = productOptionRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Produt option Id : " + id + " is Not Present in DataBase"));
		if (productOption.isStatus() == true) {
			productOption.setStatus(false); // Update status to false
			productOptionRepository.save(productOption);
			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
					"request Deleted successful", ErrorCode.Success.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFoundException("Data not found from this Id");
		}
	}

	public List<ProductOptionFilterDto> getActiveProductOption() {
		List<ProductOption> productOptions = productOptionRepository.findByStatusOrderByOptionNameAsc(true);
		List<ProductOptionFilterDto> filteredProductOptions = new ArrayList<>();

		for (ProductOption productOption : productOptions) {
			ProductOptionFilterDto filtered = new ProductOptionFilterDto();

			filtered.setId(productOption.getId());
			filtered.setParentCategory(productOption.getParentCategory());
			filtered.setOptionName(productOption.getOptionName());

			filteredProductOptions.add(filtered);
		}

		return filteredProductOptions;
	}

}
