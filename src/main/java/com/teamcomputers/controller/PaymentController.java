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
//import com.teamcomputers.dto.PaymentDto;
//import com.teamcomputers.exception.ResourceNotFoundException;
//import com.teamcomputers.message.ErrorMessage;
//import com.teamcomputers.message.ResponseMessage;
//import com.teamcomputers.model.Payment;
//import com.teamcomputers.service.PaymentService;
//
//@RestController
//@RequestMapping("/payment")
//public class PaymentController {
//
//	@Autowired
//	private PaymentService paymentService;
//
//	String message = "";
//
//	@PostMapping
//	public ResponseEntity<ResponseMessage> add(@RequestBody PaymentDto paymentDto) {
//
//		try {
//
//			this.paymentService.add(paymentDto);
//			message = "Payment successfull !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		}
//
//		catch (Exception e) {
//			message = "Payment not successfull";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//
//	@GetMapping("/{paymentId}")
//	private Payment getById(@PathVariable long paymentId) {
//		return paymentService.getById(paymentId);
//	}
//
//	@GetMapping
//	private List<Payment> getAll() {
//		return paymentService.getAll();
//	}
//
//	@PutMapping("/{paymentId}")
//	private ResponseEntity<ResponseMessage> update(@PathVariable long paymentId, @RequestBody PaymentDto paymentDto) {
//
//		String message = "";
//
//		try {
//			paymentDto.setPaymentId(paymentId);
//			this.paymentService.update(paymentDto);
//			message = "Payment successfully Updated !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Payment are not updated";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//
//	@DeleteMapping("/{paymentId}")
//	private ResponseEntity<ResponseMessage> delete(@PathVariable long paymentId) {
//		try {
//			paymentService.deleteById(paymentId);
//			message = "Payment successfully deleted !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Payment are not deleted" + e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("PAYMENT NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}
//
//}
