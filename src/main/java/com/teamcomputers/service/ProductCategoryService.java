package com.teamcomputers.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.ProductCategoryDto;
import com.teamcomputers.dto.ProductCategoryFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.ProductCategory;
import com.teamcomputers.repository.ProductCategoryRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Component
@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private UserRepository userRepository;

    public String UPLOAD_DIR;
//	public String UPLOAD_DIR = "/home/ubuntu/CDC/uploads/";

	public ResponseEntity<?> create(MultipartFile file, String userData) throws IOException {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTimeString = currentDateTime.format(formatter);
		currentDateTimeString = currentDateTimeString.replace(":", "").replace(" ", "").replace("-", "");

		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				file.getBytes(), productCategory.getCreatedBy(), productCategory.getCreatedDate(),
				productCategory.getUpdatedBy(), productCategory.getUpdatedDate(), productCategory.isStatus(),
				productCategory.getFilename(), productCategory.getFilePath(), productCategory.getFileUrl());

		String fileName = currentDateTimeString+file.getOriginalFilename();
		fileName = fileName.replaceAll(" ", "");
		UPLOAD_DIR = new ClassPathResource("uploads/").getFile().getAbsolutePath();
		
		

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, fileName);
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		ProductCategory userDup = productCategoryRepository
				.findByProductCategoryName(productCategoryDto.getProductCategoryName());
		if (userDup != null) {
			throw new DuplicateUserException("Product category name already exists");
		}

		ProductCategory productCat = new ProductCategory();

		productCat.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCat.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		productCat.setStatus(productCategoryDto.isStatus());
//		productCat.setImage(productCategoryDto.getBytes());
		productCat.setCreatedBy(userRepository.findById((int) (productCategoryDto.getCreatedBy())).orElse(null));
		productCat.setCreatedDate(productCategoryDto.getCreatedDate());
		productCat.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCat.setUpdatedDate(productCategoryDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			productCat.setImage(file.getBytes());
			productCat.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName)
					.toUriString());
			productCat.setFilePath(UPLOAD_DIR + File.separator + fileName);
			productCat.setFilename(fileName);

		} else {

			productCat.setImage(null);
			productCat.setFileUrl(null);
			productCat.setFilePath(null);
			productCat.setFilename(null);
		}
		productCategoryRepository.save(productCat);
//		 message = "request successful" ;
//      status=ErrorCode.Success.getMessage();
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "Product category successfully saved",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> create(String userData) throws IOException {

		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				productCategory.getCreatedBy(), productCategory.getCreatedDate(), productCategory.getUpdatedBy(),
				productCategory.getUpdatedDate(), productCategory.isStatus());

		ProductCategory userDup = productCategoryRepository
				.findByProductCategoryName(productCategoryDto.getProductCategoryName());
		if (userDup != null) {
			throw new DuplicateUserException("Product category name already exists");
		}

		ProductCategory productCat = new ProductCategory();
		productCat.setProductCategoryId(productCategoryDto.getProductCategoryId());
		productCat.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCat.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		productCat.setStatus(productCategoryDto.isStatus());
//		productCat.setImage(productCategoryDto.getBytes());
		productCat.setCreatedBy(userRepository.findById((int) (productCategoryDto.getCreatedBy())).orElse(null));
		productCat.setCreatedDate(productCategoryDto.getCreatedDate());
		productCat.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCat.setUpdatedDate(productCategoryDto.getUpdatedDate());
		productCategoryRepository.save(productCat);
//		 message = "request successful" ;
//      status=ErrorCode.Success.getMessage();
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "Product category successfully saved",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> getById(long productCategoryId) {
		ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(
				() -> new ResourceNotFoundException("Product category not found with ID: " + productCategoryId));

//        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
//        BeanUtils.copyProperties(productCategory, productCategoryDto);

		// Create the response body
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(productCategory);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getAll() {
		List<ProductCategory> productCategories = productCategoryRepository.findAll();

		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(productCategories);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// FOR PAGINATION
//	public ResponseEntity<ApiResponseGetAll<ProductCategory>> getAll(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<ProductCategory> productCategories = productCategoryRepository.findAll();
//		Page<ProductCategory> productCategoriesPage = productCategoryRepository.findAll(pageable);
//		List<ProductCategory> productCategoriesList = productCategoriesPage.getContent();
//
//		ApiResponseGetAll<ProductCategory> response = new ApiResponseGetAll<>();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(productCategoriesList);
//		response.setTotalPages(productCategoriesPage.getTotalPages());
//		response.setCurrentPage(productCategoriesPage.getNumber());
//		response.setTotalRecords((int) productCategoriesPage.getTotalElements());
//		response.setPerPage(productCategoriesPage.getSize());
//
//		if (productCategories.isEmpty()) {
//			response.setMessage("Data not found");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		if (page + 1 > productCategoriesPage.getTotalPages()) {
//			throw new IllegalArgumentException("Invalid page number");
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	public ResponseEntity<?> update(long productCategoryId, MultipartFile file, String userData) throws IOException {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTimeString = currentDateTime.format(formatter);
		currentDateTimeString = currentDateTimeString.replace(":", "").replace(" ", "").replace("-", "");
		Optional<ProductCategory> userDup2 = productCategoryRepository.findById(productCategoryId);
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Product Category Id does not exist");
		}
		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				file.getBytes(), productCategory.getCreatedBy(), productCategory.getCreatedDate(),
				productCategory.getUpdatedBy(), productCategory.getUpdatedDate(), productCategory.isStatus(),
				productCategory.getFilename(), productCategory.getFilePath(), productCategory.getFileUrl());

		ProductCategory userDup = productCategoryRepository.findByProductCategoryNameAndProductCategoryIdNot(
				productCategoryDto.getProductCategoryName(), productCategoryId);
		if (userDup != null) {
			throw new DuplicateUserException("Product category name already exists");
		}

		String fileName = currentDateTimeString+file.getOriginalFilename();
		fileName = fileName.replaceAll(" ", "");
//		UPLOAD_DIR = new ClassPathResource("uploads/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, fileName);
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		ProductCategory productCat = new ProductCategory();
		productCat.setProductCategoryId(productCategoryId);
		productCat.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCat.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		productCat.setStatus(productCategoryDto.isStatus());
//		productCat.setImage(productCategoryDto.getBytes());
		productCat.setCreatedDate(productCategoryDto.getCreatedDate());
		productCat.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCat.setUpdatedDate(productCategoryDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			productCat.setImage(file.getBytes());
			productCat.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName)
					.toUriString());
			productCat.setFilePath(UPLOAD_DIR + File.separator + fileName);
			productCat.setFilename(fileName);

		} else {
			Optional<ProductCategory> fileDetails = productCategoryRepository.findById(productCategoryId);

			ProductCategory img = null;
			if (fileDetails.isPresent()) {
				img = fileDetails.get();
			}

			productCat.setImage(productCat.getImage());
			productCat.setFileUrl(productCat.getFileUrl());
			productCat.setFilePath(productCat.getFilePath());
			productCat.setFilename(productCat.getFilename());
		}
		productCategoryRepository.save(productCat);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product category successfully updated", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> update(long productCategoryId, String userData) throws IOException {

		Optional<ProductCategory> userDup1 = productCategoryRepository.findById(productCategoryId);

		Optional<ProductCategory> userDup2 = productCategoryRepository.findById(productCategoryId);
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Product Category Id does not exist");
		}
		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				productCategory.getCreatedBy(), productCategory.getCreatedDate(), productCategory.getUpdatedBy(),
				productCategory.getUpdatedDate(), productCategory.isStatus());

		ProductCategory userDup = productCategoryRepository.findByProductCategoryNameAndProductCategoryIdNot(
				productCategoryDto.getProductCategoryName(), productCategoryId);
		if (userDup != null) {
			throw new DuplicateUserException("Product category name already exists");
		}

		ProductCategory productCat = new ProductCategory();

		productCat.setImage(userDup1.get().getImage());
		productCat.setFileUrl(userDup1.get().getFileUrl());
		productCat.setFilePath(userDup1.get().getFilePath());
		productCat.setFilename(userDup1.get().getFilename());

		productCat.setProductCategoryId(productCategoryId);
		productCat.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCat.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
		productCat.setStatus(productCategoryDto.isStatus());
		productCat.setCreatedDate(productCategoryDto.getCreatedDate());
		productCat.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCat.setUpdatedDate(productCategoryDto.getUpdatedDate());
		productCategoryRepository.save(productCat);

		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product category successfully updated ", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> getActiveProductCategory() {
		List<ProductCategory> productCategory = productCategoryRepository
				.findByStatusOrderByProductCategoryNameAsc(true);
		List<ProductCategoryFilterDto> productCategoryFilterDto = new ArrayList<>();

		for (ProductCategory productTitle : productCategory) {
			ProductCategoryFilterDto productservice = new ProductCategoryFilterDto();

			productservice.setProductCategoryId(productTitle.getProductCategoryId());
			productservice.setProductCategoryName(productTitle.getProductCategoryName());
			productservice.setFileUrl(productTitle.getFileUrl());
			productCategoryFilterDto.add(productservice);
		}

		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(productCategoryFilterDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	public ResponseEntity<ErrorResponse> deleteById(long productCategoryId) throws NotFoundException {
		ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Product Category Id : " + productCategoryId + " is Not Present in DataBase"));
		if (productCategory.isStatus() == true) {
			productCategory.setStatus(false); // Update status to false
			productCategoryRepository.save(productCategory);
			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
					"Product category successfully deleted", ErrorCode.Success.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFoundException("Data not found from this Id");
		}
	}

//	public byte[] downloadImageFromFileSystem(long productCategoryId) throws IOException {
//		Optional<ProductCategory> fileDetails = productCategoryRepository.findById(productCategoryId);
//		String filePath = fileDetails.get().getFilePath();
//		byte[] images = Files.readAllBytes(new File(filePath).toPath());
//		return images;
//	}

	public byte[] downloadImageFromFileSystem(String filename) throws IOException {
		ProductCategory fileDetails = productCategoryRepository.findByFilename(filename);
		String filePath = fileDetails.getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		invoiceService.saveFile(images, filename);
		return images;
	}

}
