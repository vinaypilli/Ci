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

import com.teamcomputers.dto.ProductFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	String message = "";
//	String status = "";

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
				return productService.create(file, userData);
			} else {

				throw new ResourceNotFoundException(
						"Invalid file format. Only image files (jpg, jpeg, png) are allowed.");
			}
		} else if (file == null) {
			// Handle the case where the file is not provided
			return productService.create(userData);
		}

		throw new ResourceNotFoundException("error occured");
	}

	@GetMapping("/{productId}")
	private ResponseEntity<?> getById(@PathVariable long productId) {
		return productService.getById(productId);
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return productService.getAll();
	}

//	@GetMapping
//	public List<Product> getAlls() {
//		return productService.getAlls();
//	}

	@GetMapping("/active")
	public List<ProductFilterDto> getActiveProduct() {
		return productService.getActiveProduct();
	}
	
	@GetMapping("/active/productCategoryId/{productCategoryId}")
	private ResponseEntity<?> getByproductcategory(@PathVariable long productCategoryId) {
		return productService.getActiveProductByProductCategory(productCategoryId);
	}

//	@GetMapping("/file/{productId}")
//	public ResponseEntity<?> downloadImage(@PathVariable Long productId) throws IOException {
//		byte[] imageData = productService.downloadImageFromFileSystem(productId);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
//	}

	// FOR PAGINATION
//	@GetMapping
//	public ResponseEntity<ApiResponseGetAll<Product>> getAll(@RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		return productService.getAll(page - 1, size);
//	}

	@PutMapping("/{productId}")
	private ResponseEntity<?> uploadFile(@PathVariable long productId,
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
				return productService.update(productId, file, userData);
			} else {

				throw new ResourceNotFoundException(
						"Invalid file format. Only image files (jpg, jpeg, png) are allowed.");
			}

		} else if (file == null) {
			// Handle the case where the file is not provided
			return productService.update(productId, userData);
		}

		throw new ResourceNotFoundException("error occured");

	}

	@DeleteMapping("/{productId}")
	private ResponseEntity<ErrorResponse> delete(@PathVariable long productId)
			throws NotFoundException, javassist.NotFoundException {

		return productService.deleteById(productId);

	}
//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("PRODUCT NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}
}
