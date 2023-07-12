//package com.teamcomputers.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.teamcomputers.dto.OrderProductDto;
//import com.teamcomputers.dto.OrderProductFilterDto;
//import com.teamcomputers.errorCode.ErrorCode;
//import com.teamcomputers.exception.ConflictException;
//import com.teamcomputers.exception.DuplicateUserException;
//import com.teamcomputers.exception.ResourceNotFoundException;
//import com.teamcomputers.message.ApiResponseFormat;
//import com.teamcomputers.message.ApiResponseGetAll;
//import com.teamcomputers.message.ErrorResponse;
//import com.teamcomputers.model.Order;
//import com.teamcomputers.model.Order_Product;
//import com.teamcomputers.model.Product;
//import com.teamcomputers.model.UnitOfMeasureEnnum;
//import com.teamcomputers.repository.OrderProductRepository;
//import com.teamcomputers.repository.OrderRepository;
//import com.teamcomputers.repository.ProductRepository;
//import com.teamcomputers.repository.UserRepository;
//
//import javassist.NotFoundException;
//
//@Service
//public class OrderProductService {
//
//	@Autowired
//	private OrderProductRepository orderProductRepository;
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	public ResponseEntity<?> add(OrderProductDto orderProductDto) {
//
//		Order_Product orderProduct = new Order_Product();
//		orderProduct.setOpId(orderProductDto.getOpId());
//		orderProduct.setOrderProductName(orderProductDto.getOrderProductName());
////		orderProduct.setOrderProductQuantity(orderProductDto.getOrderProductQuantity());
//
////		orderProduct.setGst(orderProductDto.getGst());
////		orderProduct.setTotalPrice(orderProductDto.getTotalPrice());
////		orderProduct.setPaymentMethod(orderProductDto.getPaymentMethod());
//		orderProduct.setCreatedBy(userRepository.findById((int) (orderProductDto.getCreatedBy())).orElse(null));
//		orderProduct.setCreatedDate(orderProductDto.getCreatedDate());
//		orderProduct.setUpdatedBy(userRepository.findById((int) (orderProductDto.getUpdatedBy())).orElse(null));
//		orderProduct.setUpdatedDate(orderProductDto.getUpdatedDate());
//		orderProduct.setStatus(orderProductDto.isStatus());
//		orderProduct.setProduct(productRepository.findById(orderProductDto.getProductId()).orElse(null));
//		orderProduct.setOrders(orderRepository.findById(orderProductDto.getOrderId()).orElse(null));
//
//		Product product = productRepository.findById(orderProductDto.getProductId())
//				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//		Order order = orderRepository.findById(orderProductDto.getOrderId())
//				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//
//		String qty = orderProductDto.getQty();
//
//		Map<String, Double> productPrices = product.getProductPrices();
//
//		double price = productPrices.get(qty);
//		double amount = price * order.getOrderQuantity();
//		orderProduct.setAmount(amount);
//
//		double cgst = amount * 0.09; // 9% CGST
//		double sgst = amount * 0.09; // 9% SGST
//		double totalAmount = amount + cgst + sgst;
//
////    orderProduct.setAmount(amount);
//		orderProduct.setCgst(cgst);
//		orderProduct.setSgst(sgst);
//		orderProduct.setTotalAmount(totalAmount);
//
//		Order_Product userDup = orderProductRepository.findByOrderProductName(orderProductDto.getOrderProductName());
//		if (userDup != null) {
//			throw new DuplicateUserException("Order_Product name already exists");
//		}
//
//		orderProductRepository.save(orderProduct);
////		         message = "request successful" ;
////		         status=ErrorCode.Success.getMessage();
//		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//				"Order product successfully saved", ErrorCode.Success.getMessage());
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
//
//	public ResponseEntity<?> getById(long opId) {
//		Order_Product orderProduct = orderProductRepository.findById(opId)
//				.orElseThrow(() -> new ResourceNotFoundException("Order_Product ID: " + opId + " Unavailable"));
//
//		// Create the response body
//		ApiResponseFormat response = new ApiResponseFormat();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(orderProduct);
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	public ResponseEntity<ApiResponseGetAll<Order_Product>> getAll(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		List<Order_Product> orderProduct = orderProductRepository.findAll();
//		Page<Order_Product> orderProductPage = orderProductRepository.findAll(pageable);
//		List<Order_Product> orderList = orderProductPage.getContent();
//
//		ApiResponseGetAll<Order_Product> response = new ApiResponseGetAll<>();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(orderList);
//		response.setTotalPages(orderProductPage.getTotalPages());
//		response.setCurrentPage(orderProductPage.getNumber());
//		response.setTotalRecords((int) orderProductPage.getTotalElements());
//		response.setPerPage(orderProductPage.getSize());
//
//		if (orderProduct.isEmpty()) {
//			response.setMessage("Data not found");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		if (page + 1 > orderProductPage.getTotalPages()) {
//			throw new IllegalArgumentException("Invalid page number");
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	public ResponseEntity<ErrorResponse> update(OrderProductDto orderProductDto) {
//
//		Order_Product orderProduct = new Order_Product();
//		orderProduct.setOpId(orderProductDto.getOpId());
//		orderProduct.setOrderProductName(orderProductDto.getOrderProductName());
//
////		orderProduct.setGst(orderProductDto.getGst());
////		orderProduct.setTotalPrice(orderProductDto.getTotalPrice());
////		orderProduct.setPaymentMethod(orderProductDto.getPaymentMethod());
//		orderProduct.setCreatedDate(orderProductDto.getCreatedDate());
//		orderProduct.setUpdatedBy(userRepository.findById((int) (orderProductDto.getUpdatedBy())).orElse(null));
//		orderProduct.setUpdatedDate(orderProductDto.getUpdatedDate());
//		orderProduct.setStatus(orderProductDto.isStatus());
//		orderProduct.setProduct(productRepository.findById(orderProductDto.getProductId()).orElse(null));
//		orderProduct.setOrders(orderRepository.findById(orderProductDto.getOrderId()).orElse(null));
//
//		Product product = productRepository.findById(orderProductDto.getProductId())
//				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//
//		Order order = orderRepository.findById(orderProductDto.getOrderId())
//				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
//
//		// Calculate the total price based on the product's price and quantity
////		double totalPrice = product.getProductPrice() * order.getOrderQuantity();
////		orderProduct.setTotalAmount(totalPrice);
//
//		String qty = orderProductDto.getQty();
//
//		Map<String, Double> productPrices = product.getProductPrices();
//
//		double price = productPrices.get(qty);
//		double amount = price * order.getOrderQuantity();
//		orderProduct.setAmount(amount);
//
//		double cgst = amount * 0.09; // 9% CGST
//		double sgst = amount * 0.09; // 9% SGST
//		double totalAmount = amount + cgst + sgst;
//
////    orderProduct.setAmount(amount);
//		orderProduct.setCgst(cgst);
//		orderProduct.setSgst(sgst);
//		orderProduct.setTotalAmount(totalAmount);
//
//		Optional<Order_Product> userDup2 = orderProductRepository.findById(orderProductDto.getOpId());
//		if (!userDup2.isPresent()) {
//			throw new ResourceNotFoundException("Id does not exist");
//		}
//
//		Order_Product userDup = orderProductRepository
//				.findByOrderProductNameAndOpIdNot(orderProductDto.getOrderProductName(), orderProductDto.getOpId());
//		if (userDup != null) {
//			throw new ConflictException("Order_Product name already exists");
//		}
//
//		orderProductRepository.save(orderProduct);
//		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//				"Order product succesfully updated", ErrorCode.Success.getMessage());
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//
//	}
//
//	public List<OrderProductFilterDto> getActiveUsers() {
//		List<Order_Product> orderProduct = orderProductRepository.findByStatusOrderByOrderProductNameAsc(true);
//		List<OrderProductFilterDto> orderProductFilterDto = new ArrayList<>();
//
//		for (Order_Product orderProductTitle : orderProduct) {
//			OrderProductFilterDto orderProductService = new OrderProductFilterDto();
//
//			orderProductService.setOpId(orderProductTitle.getOpId());
//			orderProductService.setOrderProductName(orderProductTitle.getOrderProductName());
//			orderProductFilterDto.add(orderProductService);
//		}
//
//		return orderProductFilterDto;
//	}
//
//	public ResponseEntity<ErrorResponse> deleteById(long opId) throws NotFoundException {
//		Order_Product orderProduct = orderProductRepository.findById(opId).orElseThrow(
//				() -> new ResourceNotFoundException("Order_Product Id : " + opId + " is Not Present in DataBase"));
//		if (orderProduct.isStatus() == true) {
//			orderProduct.setStatus(false); // Update status to false
//			orderProductRepository.save(orderProduct);
//			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//					"Order product successfully deleted", ErrorCode.Success.getMessage());
//			return ResponseEntity.status(HttpStatus.OK).body(response);
//		} else {
//			throw new ResourceNotFoundException("Data not found from this Id");
//		}
//	}
//
//}
