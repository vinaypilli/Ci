package com.teamcomputers.webhookMetaServiceRetrieve;

import org.springframework.stereotype.Service;

import com.teamcomputers.webhookIncomingRequestDto.WebhookChange;
import com.teamcomputers.webhookIncomingRequestDto.WebhookContact;
import com.teamcomputers.webhookIncomingRequestDto.WebhookEntry;
import com.teamcomputers.webhookIncomingRequestDto.WebhookMessage;
import com.teamcomputers.webhookIncomingRequestDto.WebhookPayload;
import com.teamcomputers.webhookIncomingRequestDto.WebhookValue;
import com.teamcomputers.webhookMetaModel.IncomingModel;

@Service
public class IncomingDataRetrieveService {

	public IncomingModel saveWebhookData(WebhookPayload payload) {
		IncomingModel incomingModel = new IncomingModel();
		if (payload != null && payload.getEntry() != null) {
			for (WebhookEntry entry : payload.getEntry()) {
				if (entry.getChanges() != null) {
					for (WebhookChange change : entry.getChanges()) {
						if (change.getValue() != null) {
							WebhookValue value = change.getValue();

							incomingModel.setObject(payload.getObject());
							incomingModel.setEntryId(entry.getId());
							incomingModel.setMessagingProduct(value.getMessaging_product());
							incomingModel.setChangedField(change.getField());

							if (value.getMetadata() != null) {
								incomingModel.setDisplayPhoneNumber(value.getMetadata().getDisplay_phone_number());
								incomingModel.setPhoneNumberId(value.getMetadata().getPhone_number_id());
							}

							if (value.getContacts() != null && !value.getContacts().isEmpty()) {
								WebhookContact contact = value.getContacts().get(0);
								if (contact.getProfile() != null) {
									incomingModel.setContactName(contact.getProfile().getName());
								}
								incomingModel.setWaId(contact.getWa_id());
							}

							if (value.getMessages() != null && !value.getMessages().isEmpty()) {
								WebhookMessage message = value.getMessages().get(0);
								incomingModel.setMessageFrom(message.getFrom());
								incomingModel.setMessageId(message.getId());
								incomingModel.setMessageTimestamp(message.getTimestamp());
								incomingModel.setMessageType(message.getType());
								incomingModel.setMessageBody(message.getText().getBody());
							}

//	                            webhookDataRepository.save(incomingModel);
						}
					}
				}
			}
		}
		return incomingModel;
	}

}
