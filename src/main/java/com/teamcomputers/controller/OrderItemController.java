//package com.teamcomputers.controller;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.teamcomputers.dto.OrderDto;
//import com.teamcomputers.dto.OrderItemDto;
//import com.teamcomputers.exception.ResourceNotFoundException;
//import com.teamcomputers.message.ErrorMessage;
//import com.teamcomputers.message.ResponseMessage;
//import com.teamcomputers.model.Order;
//import com.teamcomputers.model.OrderItem;
//import com.teamcomputers.service.OrderItemService;
//import com.teamcomputers.service.OrderService;
//
//@RestController
//@RequestMapping("/orderItem")
//public class OrderItemController {
//
//	@Autowired 
//	private OrderItemService orderItemService;
//
//	String message = "";
//
//	@PostMapping
//	public ResponseEntity<ResponseMessage> add(@RequestBody OrderItemDto orderItemDto) {
//
//		try {
//
//			this.orderItemService.add(orderItemDto);
//			message = "Order items successfully saved !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		}
//
//		catch (Exception e) {
//			message = "Order items not saved";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//
//	@GetMapping("/{orderItemId}")
//	private OrderItem getById(@PathVariable long orderItemId) {
//		return orderItemService.getById(orderItemId);
//	}
//	
//	@GetMapping
//	private List<OrderItem> getAll() {
//		return orderItemService.getAll();
//	}
//
//	@PutMapping("/{orderItemId}")
//	private ResponseEntity<ResponseMessage> update(@PathVariable long orderItemId,
//			@RequestBody OrderItemDto orderItemDto) {
//
//		String message = "";
//
//		try {
//			orderItemDto.setOrderItemId(orderItemId);
//			this.orderItemService.update(orderItemDto);
//			message = "Order items successfully Updated !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Order items are not updated";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//	
//	@DeleteMapping("/{orderItemId}")
//	private ResponseEntity<ResponseMessage> delete(@PathVariable long orderItemId) {
//		try {
//			orderItemService.deleteById(orderItemId);
//			message = "Order items successfully deleted !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Order items are not deleted" + e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("ORDER ITEMS NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}		
//		
//}
