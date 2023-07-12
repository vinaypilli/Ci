package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.WebhookDelivery;
import com.teamcomputers.service.WebhookDeliveryService;

@RestController
@RequestMapping("/deliverymessage")
public class WebhookDeliveryController {

	@Autowired
	private WebhookDeliveryService webhookDeliveryService;

	@PostMapping
	public ApiResponse add(@RequestBody WebhookDelivery webhookDelivery) {
		return webhookDeliveryService.save(webhookDelivery);
	}

	@GetMapping
	public List<WebhookDelivery> getAllUsers() {
		return webhookDeliveryService.getAll();
	}
}
