package com.teamcomputers.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamcomputers.dto.ProductCategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.service.ProductCategoryService;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;

	String message = "";

	@PostMapping
	public ResponseEntity<?> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("userData") String userData) throws IOException {

		if (userData.isEmpty()) {
			throw new ResourceNotFoundException("User data parameter is empty");
		}

		if (file != null && !file.isEmpty()) {
			// Handle the file if it's provided
			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

			if (extension != null && ((extension.equalsIgnoreCase("jpg") || (extension.equalsIgnoreCase("jpeg"))
					|| (extension.equalsIgnoreCase("png"))))) {
				return productCategoryService.create(file, userData);
			} else {

				throw new ResourceNotFoundException(
						"Invalid file format. Only image files (jpg, jpeg, png) are allowed.");
			}
		} else if (file == null) {
			// Handle the case where the file is not provided
			return productCategoryService.create(userData);
		}

		throw new ResourceNotFoundException("error occured");
	}

	@GetMapping("/{productCategoryId}")
	private ResponseEntity<?> getById(@PathVariable long productCategoryId) {
		return productCategoryService.getById(productCategoryId);
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return productCategoryService.getAll();
	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveProductCategory() {
		return productCategoryService.getActiveProductCategory();
	}

//	@GetMapping("/file/{productCategoryId}")
//	public ResponseEntity<?> downloadImage(@PathVariable Long productCategoryId) throws IOException {
//		byte[] imageData = productCategoryService.downloadImageFromFileSystem(productCategoryId);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
//	}

	// FOR PAGINATION
//	@GetMapping
//	public ResponseEntity<ApiResponseGetAll<ProductCategory>> getAll(@RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		return productCategoryService.getAll(page - 1, size);
//	}

	@PutMapping("/{productCategoryId}")
	private ResponseEntity<?> uploadFile(@PathVariable long productCategoryId,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("userData") String userData) throws IOException {

		if (userData.isEmpty()) {
			throw new ResourceNotFoundException("User data parameter is empty");
		}

		if (file != null && !file.isEmpty()) {

			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

			// Handle the file if it's provided
			if (extension != null && ((extension.equalsIgnoreCase("jpg") || (extension.equalsIgnoreCase("jpeg"))
					|| (extension.equalsIgnoreCase("png"))))) {
				return productCategoryService.update(productCategoryId, file, userData);
			} else {

				throw new ResourceNotFoundException(
						"Invalid file format. Only image files (jpg, jpeg, png) are allowed.");
			}

		} else if (file == null) {
			// Handle the case where the file is not provided
			return productCategoryService.update(productCategoryId, userData);
		}

		throw new ResourceNotFoundException("error occured");
	}

	@DeleteMapping("/{productCategoryId}")
	private ResponseEntity<ErrorResponse> delete(@PathVariable long productCategoryId)
			throws NotFoundException, javassist.NotFoundException {

		return productCategoryService.deleteById(productCategoryId);

	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("PRODUCT CATEGORY NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}

}
