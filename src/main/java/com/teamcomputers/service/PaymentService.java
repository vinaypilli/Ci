//package com.teamcomputers.service;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.teamcomputers.dto.PaymentDto;
//import com.teamcomputers.exception.ResourceNotFoundException;
//import com.teamcomputers.model.Payment;
//import com.teamcomputers.repository.PaymentRepository;
//import com.teamcomputers.repository.UserRepository;
//import javassist.NotFoundException;
//
//@Service
//public class PaymentService {
//
//	@Autowired
//	private PaymentRepository paymentRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	public Payment add(PaymentDto paymentDto) {
//
//		Payment payment = new Payment();
//		payment.setPaymentId(paymentDto.getPaymentId());
//		payment.setPaymentAmount(paymentDto.getPaymentAmount());
//		payment.setPaymentMethod(paymentDto.getPaymentMethod());
//		payment.setTimestamp(paymentDto.getTimestamp());
//		payment.setCreatedBy(userRepository.findById((int) (paymentDto.getCreatedBy())).orElse(null));
//		payment.setCreatedDate(paymentDto.getCreatedDate());
//		payment.setUpdatedBy(userRepository.findById((int) (paymentDto.getUpdatedBy())).orElse(null));
//		payment.setUpdatedDate(paymentDto.getUpdatedDate());
//
//		return paymentRepository.save(payment);
//	}
//
//	public Payment getById(long paymentId) {
//		return paymentRepository.findById(paymentId)
//				.orElseThrow(() -> new ResourceNotFoundException("Payment Id : " + paymentId + " Unavailable"));
//	}
//
//	public List<Payment> getAll() {
//		return paymentRepository.findAll();
//	}
//
//	public Payment update(PaymentDto paymentDto) {
//
//		Payment payment = new Payment();
//		payment.setPaymentId(paymentDto.getPaymentId());
//		payment.setPaymentAmount(paymentDto.getPaymentAmount());
//		payment.setPaymentMethod(paymentDto.getPaymentMethod());
//		payment.setTimestamp(paymentDto.getTimestamp());
//		payment.setCreatedBy(userRepository.findById((int) (paymentDto.getCreatedBy())).orElse(null));
//		payment.setCreatedDate(paymentDto.getCreatedDate());
//		payment.setUpdatedBy(userRepository.findById((int) (paymentDto.getUpdatedBy())).orElse(null));
//		payment.setUpdatedDate(paymentDto.getUpdatedDate());
//
//		return paymentRepository.save(payment);
//	}
//
//	public boolean deleteById(long paymentId) throws NotFoundException {
////		 Order order = 
//		paymentRepository.deleteById(paymentId);
//		return true;
//	}
//
//}
