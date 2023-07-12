package com.teamcomputers.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.controller.OutgoingMessageResponseController;
import com.teamcomputers.dto.OrderDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.Customer;
import com.teamcomputers.model.Order;
import com.teamcomputers.model.OrderDetails;
import com.teamcomputers.model.OutgoingMessageFormat;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.OrderRepository;
import com.teamcomputers.repository.UserRepository;

@Component
@Service
public class OrderService<T> {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private OutgoingMessageResponseController<T> outgoingMessageResponseController;

	@Autowired
	private CustomerRepository customerRepository;

	public ResponseEntity<?> add(OrderDto orderDto) throws MessagingException, IOException {
		Order order = new Order();
		// Set the properties of the order based on the OrderDto
			UserDao createdBy = null;

		if (orderDto.getCreatedBy() != null) {
			createdBy = userRepository.findById(orderDto.getCreatedBy().intValue()).orElseThrow(
					() -> new ResourceNotFoundException("createdBy Id : " + orderDto.getCreatedBy() + " Unavailable"));
		}

		Customer customer = customerRepository.findByContact(orderDto.getCustomerMob());
		if (customer == null) {
			throw new ResourceNotFoundException("Mobile number does not exist: " + orderDto.getCustomerMob());
		}
		order.setCustomer(customer);
		
		order.setOrderId(orderDto.getOrderId());
		order.setSubtotal(orderDto.getSubtotal());
		order.setCgst(orderDto.getCgst());
		order.setSgst(orderDto.getSgst());
		order.setService(orderDto.getService());
		order.setTotaltax(orderDto.getTotaltax());
		order.setDiscount(orderDto.getDiscount());		
		order.setGrand(orderDto.getGrand());
		order.setOrderDetails(orderDto.getOrderDetails());
		order.setCreatedBy(createdBy);
		order.setPaymentType(orderDto.getPaymentType());
		order.setStatus(orderDto.isStatus());

		Order order2 = orderRepository.save(order);

		byte[] invoice = invoiceService.generateInvoice(order);

		// Save the invoice
		String id = String.valueOf(order2.getOrderId()); // Assuming the order has an ID
		invoiceService.saveInvoice(invoice, id);

		// Send the invoice by email (optional)
		String recipientEmail = order2.getCustomer().getEmail(); // Specify the recipient email address
		invoiceService.sendInvoiceByEmail(invoice, recipientEmail);

	    String message = this.message(order2);

		OutgoingMessageFormat outgoingMessageFormat = new OutgoingMessageFormat();
		outgoingMessageFormat.setRecipient_whatsapp(order2.getCustomer().getContact());
		outgoingMessageFormat.setMessage_type("text");
		outgoingMessageFormat.setContent(message);
		outgoingMessageFormat.setTemplateName(null);
		outgoingMessageFormat.setFormId((long) orderDto.getCreatedBy());

		String[] attribute = { null };
		String responseData1 = mapper.writeValueAsString(outgoingMessageFormat);
		outgoingMessageResponseController.createWebhookSentMessage(responseData1);

		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
				"Order successfully saved", ErrorCode.Success.getMessage());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public String message(Order order2) {

		String message = "Order Confirmation\n\n";
		message += "Thank you for placing your order with us. We are pleased to confirm that your order has been received and is being processed.\n\n";
		message += "Order Details:\n";

		// Add product names
		List<OrderDetails> orderDetailsList = order2.getOrderDetails();
		for (OrderDetails orderDetails : orderDetailsList) {
			message += "- " + orderDetails.getProductName() + "\n";
		}

		// Add total amount
		message += "\nTotal Amount: " + order2.getGrand() + "\n\n";

		// Add payment method prompt
		message += "Please select a payment method:\n";
		message += "1. Credit Card\n";
		message += "2. Debit Card\n";
		message += "3. Net Banking\n";
		message += "4. Cash on Delivery\n";
		message += "\nPlease reply with the corresponding number to confirm your payment method.\n";

		return message;

	}

//	public ResponseEntity<?> add(OrderDto orderDto) {
//
//		Order order = new Order();
//		order.setOrderId(orderDto.getOrderId());
//		order.setOrderQuantity(orderDto.getOrderQuantity());
//		order.setOrderDescription(orderDto.getOrderDescription());
//		order.setCreatedBy(userRepository.findById((int) (orderDto.getCreatedBy())).orElse(null));
//		order.setCreatedDate(orderDto.getCreatedDate());
//		order.setUpdatedBy(userRepository.findById((int) (orderDto.getUpdatedBy())).orElse(null));
//		order.setUpdatedDate(orderDto.getUpdatedDate());
//		order.setStatus(orderDto.isStatus());
//		order.setCustomer(customerRepository.findById((int) orderDto.getCustomerId()).orElse(null));
//
//		orderRepository.save(order);
//		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "Order successfully saved",
//				ErrorCode.Success.getMessage());
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
//
//	public ResponseEntity<?> getById(long orderId) {
//		Order order = orderRepository.findById(orderId)
//				.orElseThrow(() -> new ResourceNotFoundException("Order id not found with ID: " + orderId));
//
//		// Create the response body
//		ApiResponseFormat response = new ApiResponseFormat();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(order);
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
	public ResponseEntity<?> getAll() {
		List<Order> order = orderRepository.findAll();

		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(order);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
//
////	For Pagination
////	public ResponseEntity<ApiResponseGetAll<Order>> getAll(int page, int size) {
////		Pageable pageable = PageRequest.of(page, size);
////		List<Order> order = orderRepository.findAll();
////		Page<Order> orderPage = orderRepository.findAll(pageable);
////		List<Order> orderList = orderPage.getContent();
////
////		ApiResponseGetAll<Order> response = new ApiResponseGetAll<>();
////		response.setStatus("success");
////		response.setCode(HttpStatus.OK.value());
////		response.setMessage("Request successful");
////		response.setData(orderList);
////		response.setTotalPages(orderPage.getTotalPages());
////		response.setCurrentPage(orderPage.getNumber());
////		response.setTotalRecords((int) orderPage.getTotalElements());
////		response.setPerPage(orderPage.getSize());
////
////		if (order.isEmpty()) {
////			response.setMessage("Data not found");
////			return new ResponseEntity<>(response, HttpStatus.OK);
////		}
////
////		if (page + 1 > orderPage.getTotalPages()) {
////			throw new IllegalArgumentException("Invalid page number");
////		}
////
////		return new ResponseEntity<>(response, HttpStatus.OK);
////	}
//
//	public ResponseEntity<?> update(OrderDto orderDto) {
//
//		Optional<Order> userDup2 = orderRepository.findById(orderDto.getOrderId());
//		if (!userDup2.isPresent()) {
//			throw new ResourceNotFoundException("Order Id does not exist");
//		}
//
//		Order order = new Order();
//		order.setOrderId(orderDto.getOrderId());
//		order.setOrderQuantity(orderDto.getOrderQuantity());
//		order.setOrderDescription(orderDto.getOrderDescription());
//		order.setCreatedDate(orderDto.getCreatedDate());
//		order.setUpdatedBy(userRepository.findById((int) (orderDto.getUpdatedBy())).orElse(null));
//		order.setUpdatedDate(orderDto.getUpdatedDate());
//		order.setStatus(orderDto.isStatus());
//		order.setCustomer(customerRepository.findById((int) orderDto.getCustomerId()).orElse(null));
//
//		orderRepository.save(order);
//		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//				"Order successfully updated", ErrorCode.Success.getMessage());
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
//
//	public List<OrderFilterDto> getActiveUsers() {
//		List<Order> order = orderRepository.findByStatusOrderByOrderDescriptionAsc(true);
//		List<OrderFilterDto> orderFilterDto = new ArrayList<>();
//
//		for (Order orderTitle : order) {
//			OrderFilterDto orderservice = new OrderFilterDto();
//
//			orderservice.setOrderId(orderTitle.getOrderId());
//			orderservice.setOrderDescription(orderTitle.getOrderDescription());
//			orderFilterDto.add(orderservice);
//		}
//
//		return orderFilterDto;
//	}
//
//	public ResponseEntity<ErrorResponse> deleteById(long orderId) throws NotFoundException {
//		Order order = orderRepository.findById(orderId).orElseThrow(
//				() -> new ResourceNotFoundException("Order Id : " + orderId + " is Not Present in DataBase"));
//		if (order.isStatus() == true) {
//			order.setStatus(false); // Update status to false
//			orderRepository.save(order);
//			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//					"Order successfully deleted", ErrorCode.Success.getMessage());
//			return ResponseEntity.status(HttpStatus.OK).body(response);
//		} else {
//			throw new ResourceNotFoundException("Data not found from this Id");
//		}
//	}

}
