package com.teamcomputers.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.OrderDto;
import com.teamcomputers.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	String message = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody OrderDto orderDto) throws MessagingException, IOException {
		return this.orderService.add(orderDto);
	}
	
//	@PostMapping
//	public ResponseEntity<ResponseMessage> add(@RequestBody OrderDto orderDto) {
//
//		// try {
//
//		orderService.add(orderDto);
//		// message = "Order successfully saved !!";
//		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//	}

//		catch (Exception e) {
//			message = "Order not saved";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}

//	@GetMapping("/{orderId}")
//	private ResponseEntity<?> getById(@PathVariable long orderId) {
//		return orderService.getById(orderId);
//	}
//
//	@GetMapping("/active")
//	public List<OrderFilterDto> getActiveUsers() {
//		return orderService.getActiveUsers();
//	}
//
	@GetMapping
	public ResponseEntity<?> getAll() {
		return orderService.getAll();
	}

	// FOR PAGINATION
//	@GetMapping
//	public ResponseEntity<ApiResponseGetAll<Order>> getAll(@RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		return orderService.getAll(page - 1, size);
//	}

//	@PutMapping("/{orderId}")
//	private ResponseEntity<?> update(@PathVariable long orderId, @RequestBody OrderDto orderDto) {
//		orderDto.setOrderId(orderId);
//		return orderService.update(orderDto);
//	}
	
	
//	private ResponseEntity<ResponseMessage> update(@PathVariable long orderId, @RequestBody OrderDto orderDto) {
//
//		String message = "";
//
//		// try {
//		orderDto.setOrderId(orderId);
//		this.orderService.update(orderId, orderDto);
//		// message = "Order successfully Updated !!";
//		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//	}
////		} catch (Exception e) {
////			message = "Order are not updated";
////			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
////		}
////
////	}

//	@DeleteMapping("/{orderId}")
//	private ResponseEntity<ErrorResponse> delete(@PathVariable long orderId)
//			throws NotFoundException, javassist.NotFoundException {
//
//		return orderService.deleteById(orderId);
//
//	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("ORDER NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}

}
