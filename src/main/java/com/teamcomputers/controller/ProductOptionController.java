package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.ProductOptionDto;
import com.teamcomputers.dto.ProductOptionFilterDto;
import com.teamcomputers.message.ApiResponseGetAll;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.ProductOption;
import com.teamcomputers.service.ProductOptionService;

@RestController
@RequestMapping("/productoption")
public class ProductOptionController {

	@Autowired
	private ProductOptionService productOptionService;

	String message = "";
	String status = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody ProductOptionDto productOptionDto) {
		return this.productOptionService.add(productOptionDto);
	}

	@GetMapping("/{id}")
	private ResponseEntity<?> getById(@PathVariable long id) {
		return productOptionService.getById(id);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}

	@GetMapping
	public ResponseEntity<ApiResponseGetAll<ProductOption>> getAll(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		return productOptionService.getAll(page - 1, size);
	}

	@PutMapping("/{id}")
	private ResponseEntity<ErrorResponse> update(@PathVariable long id,
			@RequestBody ProductOptionDto productOptionDto) {
		productOptionDto.setId(id);
		return productOptionService.update(productOptionDto);

	}

	@DeleteMapping("/{id}")
	private ResponseEntity<ErrorResponse> delete(@PathVariable long id) throws NotFoundException {

		return productOptionService.deleteById(id);

	}

	@GetMapping("/active")
	public List<ProductOptionFilterDto> getActiveUsers() {
		return productOptionService.getActiveProductOption();
	}

}
