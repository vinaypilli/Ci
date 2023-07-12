package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.WebhookDelivery;
import com.teamcomputers.repository.WebhookDeliveryRepository;

@Service
public class WebhookDeliveryService {

	@Autowired
	private WebhookDeliveryRepository webhookDeliveryRepository;

	public ApiResponse save(WebhookDelivery WebhookDelivery) {
		ApiResponse response = new ApiResponse();
		 webhookDeliveryRepository.save(WebhookDelivery);
		 response.setStatus("success");
			response.setMessage("Delivery Message processed and saved successfully");
			return response;
	}

	public List<WebhookDelivery> getAll() {
		return webhookDeliveryRepository.findAll();
	}
}
