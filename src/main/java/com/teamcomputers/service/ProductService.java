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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.teamcomputers.dto.ProductDto;
import com.teamcomputers.dto.ProductFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ConflictException;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.OptionEnum;
import com.teamcomputers.model.Product;
import com.teamcomputers.model.ProductCategory;
import com.teamcomputers.repository.ProductCategoryRepository;
import com.teamcomputers.repository.ProductRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Component
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private UserRepository userRepository;

//	public String UPLOAD_DIR;
	public String UPLOAD_DIR = "/home/ubuntu/CDC/productUploads/";
	
	
//	new product creart with file

	public ResponseEntity<?> create(MultipartFile file, String userData) throws IOException {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTimeString = currentDateTime.format(formatter);
		currentDateTimeString = currentDateTimeString.replace(":", "").replace(" ", "").replace("-", "");

		ProductDto products = mapper.readValue(userData, ProductDto.class);
//		ProductDto productDto = new ProductDto(products.getProductId(), products.getSerialNo(),
//				products.getProductName(), products.getProductDescription(), products.getProductPrice(),
//				products.getKeys(), file.getBytes(), products.getCreatedBy(), products.getCreatedDate(),
//				products.getUpdatedBy(), products.getUpdatedDate(), products.isStatus(), products.getFilename(),
//				products.getFilePath(), products.getFileUrl(), products.getProductCategoryId());

		String fileName = currentDateTimeString+file.getOriginalFilename();
		// remove space from file url.
		fileName = fileName.replaceAll(" ", "");
//		UPLOAD_DIR = new ClassPathResource("productUploads/").getFile().getAbsolutePath();

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

		Product userDup = productRepository.findByProductName(products.getProductName().trim());
		if (userDup != null) {
			throw new DuplicateUserException("Product name already exists");
		}

		Product product = new Product();

		product.setOptions(products.getOptions());

		product.setIscheckboxGM250(products.isIscheckboxGM250());
		product.setIscheckboxGM500(products.isIscheckboxGM500());
		product.setIscheckboxKG1(products.isIscheckboxKG1());
		product.setIscheckboxLT1(products.isIscheckboxLT1());
		product.setIscheckboxPiece(products.isIscheckboxPiece());

		Map<String, Double> productPrices = new HashMap<>();
		Map<String, Double> productPrise = new HashMap<>(products.getProductPrices());

//		if (products.getOptions() == OptionEnum.PIECE) {
//			product.setIscheckboxPiece(products.isIscheckboxPiece());
//			// Store prices for PIECE option
//			for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
//				if (products.isIscheckboxPiece() && entry.getKey().contains("PiecePrice")) {
//					productPrices.put(entry.getKey(), entry.getValue());
//				}
//			}
//
//		}
//		if (products.getOptions() == OptionEnum.GM) {
//			product.setIscheckboxGM250(products.isIscheckboxGM250());
//			product.setIscheckboxGM500(products.isIscheckboxGM500());
//			product.setIscheckboxKG1(products.isIscheckboxKG1());
//
//			// Store prices for GM option
//			for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
//				if (products.isIscheckboxGM250() && entry.getKey().contains("GM250Price")) {
//					productPrices.put(entry.getKey(), entry.getValue());
//				}
//				if (products.isIscheckboxGM500() && entry.getKey().contains("GM500Price")) {
//					productPrices.put(entry.getKey(), entry.getValue());
//				}
//				if (products.isIscheckboxKG1() && entry.getKey().contains("KG1Price")) {
//					productPrices.put(entry.getKey(), entry.getValue());
//				}
//			}
//		}
//		if (products.getOptions() == OptionEnum.LITRE) {
//			product.setIscheckboxLT1(products.isIscheckboxLT1());
//			// Store prices for LITRE option
//			for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
//				if (products.isIscheckboxLT1() && entry.getKey().toLowerCase().contains("lt1price")) {
//					productPrices.put(entry.getKey(), entry.getValue());
//				}
//			}
//
//		}

		// FOR AFTER DOT MANY DIGITS ARE NOT AVAILABLE & Zero(0) price is not allowed

		for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
			double price = entry.getValue();
//			if (price == 0) {
//				throw new IllegalArgumentException("Zero price is not allowed");
//			}

			// Round the price to two decimal places
			double roundedPrice = Math.round(price * 100.0) / 100.0;

			// Check if the rounded price has more than 2 digits after the decimal point
			String priceString = Double.toString(roundedPrice);
			int indexOfDecimal = priceString.indexOf(".");
			int digitsAfterDecimal = priceString.length() - indexOfDecimal - 1;
			if (digitsAfterDecimal > 2) {
				throw new IllegalArgumentException("Price should have maximum 2 digits after the decimal point");
			}

			// Store prices for PIECE option
			if (products.getOptions() == OptionEnum.PIECE && products.isIscheckboxPiece()
					&& entry.getKey().contains("PiecePrice")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}

			// Store prices for GM option
			if (products.getOptions() == OptionEnum.GM) {
				if (products.isIscheckboxGM250() && entry.getKey().contains("GM250Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxGM500() && entry.getKey().contains("GM500Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxKG1() && entry.getKey().contains("KG1Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
			}

			// Store prices for LITRE option
			if (products.getOptions() == OptionEnum.LITRE && products.isIscheckboxLT1()
					&& entry.getKey().toLowerCase().contains("lt1price")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}
		}

		product.setProductPrices(productPrices);
		String productName = products.getProductName().trim();
		product.setProductName(productName);
		product.setProductDescription(products.getProductDescription());

		product.setStatus(products.isStatus());
		product.setCreatedBy(userRepository.findById((int) (products.getCreatedBy())).orElse(null));
		product.setCreatedDate(products.getCreatedDate());
		product.setUpdatedBy(userRepository.findById((int) (products.getUpdatedBy())).orElse(null));
		product.setProductCategory(productCategoryRepository.findById(products.getProductCategoryId()).orElse(null));
		product.setUpdatedDate(products.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			product.setImage(file.getBytes());
			product.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productUploads/").path(fileName)
					.toUriString());
			product.setFilePath(UPLOAD_DIR + File.separator + fileName);
			product.setFilename(fileName);

		} else {

			product.setImage(null);
			product.setFileUrl(null);
			product.setFilePath(null);
			product.setFilename(null);
		}

		productRepository.save(product);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product successfully saved", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	public ResponseEntity<?> create(String userData) throws IOException {

		ProductDto products = mapper.readValue(userData, ProductDto.class);
		ProductDto productDto = new ProductDto(products.getProductId(), products.getSerialNo(),
				products.getProductName(), products.getProductDescription(), products.getProductPrices(),
				products.isIscheckboxGM250(), products.isIscheckboxGM500(), products.isIscheckboxKG1(),
				products.isIscheckboxLT1(), products.isIscheckboxPiece(), products.getOptions(),
				products.getCreatedBy(), products.getCreatedDate(), products.getUpdatedBy(), products.getUpdatedDate(),
				products.isStatus(), products.getProductCategoryId());

		Product userDup = productRepository.findByProductName(productDto.getProductName().trim());
		if (userDup != null) {
			throw new DuplicateUserException("Product name already exists");
		}

		Product product1 = new Product();

		product1.setOptions(products.getOptions());
		product1.setIscheckboxGM250(products.isIscheckboxGM250());
		product1.setIscheckboxGM500(products.isIscheckboxGM500());
		product1.setIscheckboxKG1(products.isIscheckboxKG1());
		product1.setIscheckboxLT1(products.isIscheckboxLT1());
		product1.setIscheckboxPiece(products.isIscheckboxPiece());

		Map<String, Double> productPrices = new HashMap<>();
		Map<String, Double> productPrise = new HashMap<>(products.getProductPrices());

		for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
			double price = entry.getValue();
//			if (price == 0) {
//				throw new IllegalArgumentException("Zero price is not allowed");
//			}

			// Round the price to two decimal places
			double roundedPrice = Math.round(price * 100.0) / 100.0;

			// Check if the rounded price has more than 2 digits after the decimal point
			String priceString = Double.toString(roundedPrice);
			int indexOfDecimal = priceString.indexOf(".");
			int digitsAfterDecimal = priceString.length() - indexOfDecimal - 1;
			if (digitsAfterDecimal > 2) {
				throw new IllegalArgumentException("Price should have maximum 2 digits after the decimal point");
			}

			// Store prices for PIECE option
			if (products.getOptions() == OptionEnum.PIECE && products.isIscheckboxPiece()
					&& entry.getKey().contains("PiecePrice")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}

			// Store prices for GM option
			if (products.getOptions() == OptionEnum.GM) {
				if (products.isIscheckboxGM250() && entry.getKey().contains("GM250Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxGM500() && entry.getKey().contains("GM500Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxKG1() && entry.getKey().contains("KG1Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
			}

			// Store prices for LITRE option
			if (products.getOptions() == OptionEnum.LITRE && products.isIscheckboxLT1()
					&& entry.getKey().toLowerCase().contains("lt1price")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}
		}

		product1.setProductPrices(productPrices);
		String productName = products.getProductName().trim();
		product1.setProductName(productName);
		product1.setProductDescription(productDto.getProductDescription());

		product1.setStatus(productDto.isStatus());
		product1.setCreatedBy(userRepository.findById((int) (productDto.getCreatedBy())).orElse(null));
		product1.setCreatedDate(productDto.getCreatedDate());
		product1.setUpdatedBy(userRepository.findById((int) (productDto.getUpdatedBy())).orElse(null));
		product1.setProductCategory(productCategoryRepository.findById(productDto.getProductCategoryId()).orElse(null));
		product1.setUpdatedDate(productDto.getUpdatedDate());

		productRepository.save(product1);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product successfully saved", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	public ResponseEntity<?> getById(long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product category not found with ID: " + productId));

		// Create the response body
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(product);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getAll() {
		List<Product> product = productRepository.findAll();

		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(product);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// FOR PAGINATION
//	public ResponseEntity<ApiResponseGetAll<Product>> getAll(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<Product> product = productRepository.findAll();
//		Page<Product> productPage = productRepository.findAll(pageable);
//		List<Product> productList = productPage.getContent();
//
//		ApiResponseGetAll<Product> response = new ApiResponseGetAll<>();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(productList);
//		response.setTotalPages(productPage.getTotalPages());
//		response.setCurrentPage(productPage.getNumber());
//		response.setTotalRecords((int) productPage.getTotalElements());
//		response.setPerPage(productPage.getSize());
//
//		if (product.isEmpty()) {
//			response.setMessage("Data not found");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		if (page + 1 > productPage.getTotalPages()) {
//			throw new IllegalArgumentException("Invalid page number");
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	public ResponseEntity<?> update(long productId, MultipartFile file, String userData) throws IOException {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTimeString = currentDateTime.format(formatter);
		currentDateTimeString = currentDateTimeString.replace(":", "").replace(" ", "").replace("-", "");
		
		Optional<Product> userDup2 = productRepository.findById(productId);
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Product Id does not exist");
		}

		ProductDto products = mapper.readValue(userData, ProductDto.class);
//		ProductDto productDto = new ProductDto(products.getProductId(), products.getSerialNo(),
//				products.getProductName(), products.getProductDescription(), products.getProductPrice(),
//				products.getKeys(), file.getBytes(), products.getCreatedBy(), products.getCreatedDate(),
//				products.getUpdatedBy(), products.getUpdatedDate(), products.isStatus(), products.getFilename(),
//				products.getFilePath(), products.getFileUrl(), products.getProductCategoryId());

		Product userDup = productRepository.findByProductNameAndProductIdNot(products.getProductName().trim(),
				productId);
		if (userDup != null) {
			throw new DuplicateUserException("Product name already exists");
		}

		String fileName = currentDateTimeString+file.getOriginalFilename();

//		UPLOAD_DIR = new ClassPathResource("productUploads/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		Product product = new Product();
		product.setProductId(productId);
		product.setSerialNo(fileName);

		product.setOptions(products.getOptions());

		product.setIscheckboxGM250(products.isIscheckboxGM250());
		product.setIscheckboxGM500(products.isIscheckboxGM500());
		product.setIscheckboxKG1(products.isIscheckboxKG1());
		product.setIscheckboxLT1(products.isIscheckboxLT1());
		product.setIscheckboxPiece(products.isIscheckboxPiece());

		Map<String, Double> productPrices = new HashMap<>();
		Map<String, Double> productPrise = new HashMap<>(products.getProductPrices());

		for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
			double price = entry.getValue();
//			if (price == 0) {
//				throw new IllegalArgumentException("Zero price is not allowed");
//			}

			// Round the price to two decimal places
			double roundedPrice = Math.round(price * 100.0) / 100.0;

			// Check if the rounded price has more than 2 digits after the decimal point
			String priceString = Double.toString(roundedPrice);
			int indexOfDecimal = priceString.indexOf(".");
			int digitsAfterDecimal = priceString.length() - indexOfDecimal - 1;
			if (digitsAfterDecimal > 2) {
				throw new IllegalArgumentException("Price should have maximum 2 digits after the decimal point");
			}

			// Store prices for PIECE option
			if (products.getOptions() == OptionEnum.PIECE && products.isIscheckboxPiece()
					&& entry.getKey().contains("PiecePrice")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}

			// Store prices for GM option
			if (products.getOptions() == OptionEnum.GM) {
				if (products.isIscheckboxGM250() && entry.getKey().contains("GM250Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxGM500() && entry.getKey().contains("GM500Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxKG1() && entry.getKey().contains("KG1Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
			}

			// Store prices for LITRE option
			if (products.getOptions() == OptionEnum.LITRE && products.isIscheckboxLT1()
					&& entry.getKey().toLowerCase().contains("lt1price")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}
		}

		product.setProductPrices(productPrices);
		String productName = products.getProductName().trim();
		product.setProductName(productName);
		product.setProductDescription(products.getProductDescription());

		product.setStatus(products.isStatus());
		product.setCreatedDate(products.getCreatedDate());
		product.setUpdatedBy(userRepository.findById((int) (products.getUpdatedBy())).orElse(null));
		product.setProductCategory(productCategoryRepository.findById(products.getProductCategoryId()).orElse(null));
		product.setUpdatedDate(products.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			product.setImage(file.getBytes());
			product.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productUploads/").path(fileName)
					.toUriString());
			product.setFilePath(UPLOAD_DIR + File.separator + fileName);
			product.setFilename(fileName);

		} else {
			Optional<Product> fileDetails = productRepository.findById(productId);

			Product med = null;
			if (fileDetails.isPresent()) {
				med = fileDetails.get();
			}

			product.setImage(product.getImage());
			product.setFileUrl(product.getFileUrl());
			product.setFilePath(product.getFilePath());
			product.setFilename(product.getFilename());
		}

		productRepository.save(product);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product successfully updated", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> update(long productId, String userData) throws IOException {

		Optional<Product> userDup1 = productRepository.findById(productId);

		Optional<Product> userDup2 = productRepository.findById(productId);
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Product Id does not exist");
		}

		ProductDto products = mapper.readValue(userData, ProductDto.class);
//		ProductDto productDto = new ProductDto(product.getProductId(), product.getSerialNo(), product.getProductName(),
//				product.getProductDescription(), product.getProductPrice(), product.getKeys(), product.getCreatedBy(),
//				product.getCreatedDate(), product.getUpdatedBy(), product.getUpdatedDate(), product.isStatus(),
//				product.getProductCategoryId());
////
//		Optional<Product> userDup2 = productRepository.findById(productId);
//		if (!userDup2.isPresent()) {
//			throw new ResourceNotFoundException("Product Id does not exist");
//		}

		Product userDup = productRepository.findByProductNameAndProductIdNot(products.getProductName().trim(), productId);
		if (userDup != null) {
			throw new DuplicateUserException("Product name already exists");
		}

		Product product1 = new Product();

		product1.setImage(userDup1.get().getImage());
		product1.setFileUrl(userDup1.get().getFileUrl());
		product1.setFilePath(userDup1.get().getFilePath());
		product1.setFilename(userDup1.get().getFilename());

		product1.setProductId(productId);
//		product1.setSerialNo(serialNo);
		product1.setProductDescription(products.getProductDescription());

		product1.setOptions(products.getOptions());
		product1.setIscheckboxGM250(products.isIscheckboxGM250());
		product1.setIscheckboxGM500(products.isIscheckboxGM500());
		product1.setIscheckboxKG1(products.isIscheckboxKG1());
		product1.setIscheckboxLT1(products.isIscheckboxLT1());
		product1.setIscheckboxPiece(products.isIscheckboxPiece());

		Map<String, Double> productPrices = new HashMap<>();
		Map<String, Double> productPrise = new HashMap<>(products.getProductPrices());

		for (Map.Entry<String, Double> entry : productPrise.entrySet()) {
			double price = entry.getValue();
//			if (price == 0) {
//				throw new IllegalArgumentException("Zero price is not allowed");
//			}

			// Round the price to two decimal places
			double roundedPrice = Math.round(price * 100.0) / 100.0;

			// Check if the rounded price has more than 2 digits after the decimal point
			String priceString = Double.toString(roundedPrice);
			int indexOfDecimal = priceString.indexOf(".");
			int digitsAfterDecimal = priceString.length() - indexOfDecimal - 1;
			if (digitsAfterDecimal > 2) {
				throw new IllegalArgumentException("Price should have maximum 2 digits after the decimal point");
			}

			// Store prices for PIECE option
			if (products.getOptions() == OptionEnum.PIECE && products.isIscheckboxPiece()
					&& entry.getKey().contains("PiecePrice")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}

			// Store prices for GM option
			if (products.getOptions() == OptionEnum.GM) {
				if (products.isIscheckboxGM250() && entry.getKey().contains("GM250Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxGM500() && entry.getKey().contains("GM500Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
				if (products.isIscheckboxKG1() && entry.getKey().contains("KG1Price")) {
					productPrices.put(entry.getKey(), roundedPrice);
				}
			}

			// Store prices for LITRE option
			if (products.getOptions() == OptionEnum.LITRE && products.isIscheckboxLT1()
					&& entry.getKey().toLowerCase().contains("lt1price")) {
				productPrices.put(entry.getKey(), roundedPrice);
			}
		}

		product1.setProductPrices(productPrices);
		String productName = products.getProductName().trim();
		product1.setProductName(productName);
		product1.setStatus(products.isStatus());
		product1.setCreatedDate(products.getCreatedDate());
		product1.setUpdatedBy(userRepository.findById((int) (products.getUpdatedBy())).orElse(null));
		product1.setProductCategory(productCategoryRepository.findById(products.getProductCategoryId()).orElse(null));
		product1.setUpdatedDate(products.getUpdatedDate());

		productRepository.save(product1);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Product successfully updated", ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public List<ProductFilterDto> getActiveProduct() {
		List<Product> product = productRepository.findByStatusOrderByProductNameAsc(true);
		List<ProductFilterDto> productFilterDto = new ArrayList<>();

		for (Product productTitle : product) {
			ProductFilterDto productservice = new ProductFilterDto();

			productservice.setProductId(productTitle.getProductId());
			productservice.setProductName(productTitle.getProductName());
			productFilterDto.add(productservice);
		}

		return productFilterDto;
	}
	
	
	public ResponseEntity<?> getActiveProductByProductCategory(long productCategoryId) {
		
		ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(
				() -> new ResourceNotFoundException("Product category not found with ID: " + productCategoryId));
		
		List<Product> product = productRepository.findByProductCategoryAndStatusOrderByProductNameAsc(productCategory,true);
		
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(product);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	

	public ResponseEntity<ErrorResponse> deleteById(long productId) throws NotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("Order_Product Id : " + productId + " is Not Present in DataBase"));
		if (product.isStatus() == true) {
			product.setStatus(false); // Update status to false
			productRepository.save(product);
			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
					"Product successfully deleted", ErrorCode.Success.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFoundException("Data not found from this Id");
		}
	}

//	public byte[] downloadImageFromFileSystem(long productId) throws IOException {
//		Optional<Product> fileDetails = productRepository.findById(productId);
//		String filePath = fileDetails.get().getFilePath();
//		byte[] images = Files.readAllBytes(new File(filePath).toPath());
//		return images;
//	}

	public byte[] downloadImageFromFileSystem(String filename) throws IOException {
		Product fileDetails = productRepository.findByFilename(filename);
		String filePath = fileDetails.getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}
	
//	public byte[] downloadImageFromFileSystem(String filename) throws IOException {
//		ProductCategory fileDetails = productCategoryRepository.findByFilename(filename);
//		String filePath = fileDetails.getFilePath();
//		byte[] images = Files.readAllBytes(new File(filePath).toPath());
//		return images;
//	}
	

}
