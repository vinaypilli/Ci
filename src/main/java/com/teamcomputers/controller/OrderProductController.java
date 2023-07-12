//package com.teamcomputers.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.teamcomputers.dto.OrderProductDto;
//import com.teamcomputers.dto.OrderProductFilterDto;
//import com.teamcomputers.message.ApiResponseGetAll;
//import com.teamcomputers.message.ErrorResponse;
//import com.teamcomputers.model.Order_Product;
//import com.teamcomputers.service.OrderProductService;
//
//@RestController
//@RequestMapping("/orderProduct")
//public class OrderProductController {
//
//	@Autowired
//	private OrderProductService orderProductService;
//
//	String message = "";
//	String status = "";
//
//	@PostMapping
//	public ResponseEntity<?> add(@RequestBody OrderProductDto orderProductDto) {
//		return this.orderProductService.add(orderProductDto);
//	}
//
//	@GetMapping("/{opId}")
//	private ResponseEntity<?> getById(@PathVariable long opId) {
//		return orderProductService.getById(opId);
//	}
//
//	@GetMapping("/active")
//	public List<OrderProductFilterDto> getActiveUsers() {
//		return orderProductService.getActiveUsers();
//	}
//
//	@GetMapping
//	public ResponseEntity<ApiResponseGetAll<Order_Product>> getAll(@RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		return orderProductService.getAll(page - 1, size);
//	}
//
//	@PutMapping("/{opId}")
//	private ResponseEntity<ErrorResponse> update(@PathVariable long opId,
//			@RequestBody OrderProductDto orderProductDto) {
//		orderProductDto.setOpId(opId);
//		return orderProductService.update(orderProductDto);
//
//	}
//
//	@DeleteMapping("/{opId}")
//	private ResponseEntity<ErrorResponse> delete(@PathVariable long opId)
//			throws NotFoundException, javassist.NotFoundException {
//
//		return orderProductService.deleteById(opId);
//
//	}
//
////	@ExceptionHandler(ResourceNotFoundException.class)
////	@ResponseStatus(HttpStatus.NOT_FOUND)
////	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
////		ErrorMessage errorMessage = new ErrorMessage("ORDER NOT FOUND", rx.getMessage());
////		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
////	}
//
//}
