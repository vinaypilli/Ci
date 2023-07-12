package com.teamcomputers.webhookMetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamcomputers.SpringBootJwtApplication;
import com.teamcomputers.config.JwtTokenUtil;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.MetaResponse;
import com.teamcomputers.webhookIncomingRequestDto.WebhookPayload;
import com.teamcomputers.webhookMetaModel.IncomingModel;
import com.teamcomputers.webhookMetaRepository.IncomingDataRepository;
import com.teamcomputers.webhookMetaServiceRetrieve.IncomingDataRetrieveService;

@Service
public class IncomingDataService {

	@Autowired
	private IncomingDataRepository incomingDataRepository;

	@Autowired
	private IncomingDataRetrieveService incomingDataRetrieveService;
	 String token="meatyhamhock";
//	public static String token() {
//		token = JwtTokenUtil.generateToken();
//		return token;
//	}
	
	public MetaResponse saveWebhookData(WebhookPayload payload) {

		IncomingModel incomingModel = incomingDataRetrieveService.saveWebhookData(payload);
		incomingDataRepository.save(incomingModel);
		System.out.println(incomingModel.getMessageBody()+"  "+incomingModel.getContactName());
		MetaResponse errorMessage = new MetaResponse("200",token);
		return errorMessage;

	}
}
