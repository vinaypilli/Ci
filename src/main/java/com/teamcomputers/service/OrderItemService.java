//package com.teamcomputers.service;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.teamcomputers.dto.OrderItemDto;
//import com.teamcomputers.exception.ResourceNotFoundException;
//import com.teamcomputers.model.OrderItem;
//import com.teamcomputers.repository.OrderItemRepository;
//import com.teamcomputers.repository.OrderRepository;
//import com.teamcomputers.repository.ProductRepository;
//import com.teamcomputers.repository.UserRepository;
//import javassist.NotFoundException;
//
//@Service
//public class OrderItemService {
//
//	@Autowired
//	private OrderItemRepository orderItemRepository;
//	
//	@Autowired
//	private OrderRepository orderRepository;
//	
//	@Autowired
//	private ProductRepository productRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	public OrderItem add(OrderItemDto orderItemDto) {
//
//		OrderItem orderItem = new OrderItem();
//		orderItem.setOrderItemId(orderItemDto.getOrderItemId());
//		orderItem.setQuantity(orderItemDto.getQuantity());
//		orderItem.setSpecialInstruction(orderItemDto.getSpecialInstruction());
//		orderItem.setCreatedBy(userRepository.findById((int)(orderItemDto.getCreatedBy())).orElse(null));
//		orderItem.setCreatedDate(orderItemDto.getCreatedDate());
//		orderItem.setUpdatedBy(userRepository.findById((int)(orderItemDto.getUpdatedBy())).orElse(null));
//		orderItem.setUpdatedDate(orderItemDto.getUpdatedDate());
//		orderItem.setOrder_details(orderRepository.findById((long) orderItemDto.getOrderId()).orElse(null));
//		orderItem.setProductdetails(productRepository.findById((long) orderItemDto.getProductId()).orElse(null));
//
//		return orderItemRepository.save(orderItem);
//	}
//
//	public OrderItem getById(long orderItemId) {
//		return orderItemRepository.findById(orderItemId)
//				.orElseThrow(() -> new ResourceNotFoundException("Order Item Id : " + orderItemId + " Unavailable"));
//	}
//
//	public List<OrderItem> getAll() {
//		return orderItemRepository.findAll();
//	}
//
//	public OrderItem update(OrderItemDto orderItemDto) {
//
//		OrderItem orderItem = new OrderItem();
//		orderItem.setOrderItemId(orderItemDto.getOrderItemId());
//		orderItem.setQuantity(orderItemDto.getQuantity());
//		orderItem.setSpecialInstruction(orderItemDto.getSpecialInstruction());
//		orderItem.setCreatedBy(userRepository.findById((int)(orderItemDto.getCreatedBy())).orElse(null));
//		orderItem.setCreatedDate(orderItemDto.getCreatedDate());
//		orderItem.setUpdatedBy(userRepository.findById((int)(orderItemDto.getUpdatedBy())).orElse(null));
//		orderItem.setUpdatedDate(orderItemDto.getUpdatedDate());
//		orderItem.setOrder_details(orderRepository.findById((long) orderItemDto.getOrderId()).orElse(null));
//		orderItem.setProductdetails(productRepository.findById((long) orderItemDto.getProductId()).orElse(null));
//
//		return orderItemRepository.save(orderItem);
//	}
//   
//	 public boolean deleteById(long orderItemId) throws NotFoundException {
////		 OrderItem orderItem = 
//				 orderItemRepository.deleteById(orderItemId);
//		return true;
//	}	
//	
//}
