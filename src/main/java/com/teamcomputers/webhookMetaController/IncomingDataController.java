package com.teamcomputers.webhookMetaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.MetaResponse;
import com.teamcomputers.webhookIncomingRequestDto.WebhookPayload;
import com.teamcomputers.webhookMetaService.IncomingDataService;

@RestController
@RequestMapping("/webhook")
public class IncomingDataController {

	@Autowired
	private IncomingDataService incomingDataService;

	@PostMapping
	public ResponseEntity<?> handleWebhookData(@RequestBody WebhookPayload payload) {

		System.out.println(payload);
		incomingDataService.saveWebhookData(payload);
//		MetaResponse errorMessage = new MetaResponse("200",token);
		return ResponseEntity.status(HttpStatus.OK).body("OK");

	}

	@GetMapping
	public String verifyWebhook(@RequestParam("hub.mode") String mode, @RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String verifyToken) {
		// Verify the hub.verify_token
		String expectedToken = "meat999"; // Set your verify token here

		if ("subscribe".equals(mode) && expectedToken.equals(verifyToken)) {
			// Respond with the hub.challenge value to complete verification
			return challenge;

		} else {
			// Token mismatch, return an error message or throw an exception
			return "Invalid verify_token";
		}
	}

}
