package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.OutgoingTemplateLanguageDTO;
import com.teamcomputers.dto.OutgoingTemplateMessageDto;
import com.teamcomputers.dto.OutgoingTemplateMessageDtoWithoutTemplate;
import com.teamcomputers.dto.OutgoingTypeTemplateDTO;
import com.teamcomputers.dto.OutgoingTypeTemplateWithoutAttributeDTO;
import com.teamcomputers.model.Language;
import com.teamcomputers.model.OutgoingData;
import com.teamcomputers.model.OutgoingMessageData;
import com.teamcomputers.model.OutgoingMessageResponce;
import com.teamcomputers.model.OutgoingResponseStatus;
import com.teamcomputers.model.OutgoingStatus;
import com.teamcomputers.model.OutgoingTypeTemplate;
import com.teamcomputers.repository.OutgoingMessageResponceRepository;

@Service
public class OutgoingMessageResponceService {

	@Autowired
	private OutgoingMessageResponceRepository outgoingMessageResponceRepository;

	public OutgoingMessageResponce saveOutgoingMessage(OutgoingMessageData md, OutgoingStatus os, OutgoingData outgoingData) {

		OutgoingMessageResponce outgoingMessageResponce = new OutgoingMessageResponce();
		outgoingMessageResponce.setRecipientwhatsapp(md.getRecipient_whatsapp());
		outgoingMessageResponce.setRecipient_type(md.getRecipient_type());
		outgoingMessageResponce.setMessage_type(md.getMessage_type());
		outgoingMessageResponce.setSource(md.getSource());
		outgoingMessageResponce.setX_apiheader(md.getX_apiheader());
		outgoingMessageResponce.setType_text(md.getType_text());

		OutgoingResponseStatus og = new OutgoingResponseStatus();
		og.setMessage(os.getMessage());
		og.setStatus(os.getStatus());
		OutgoingData dt1 = new OutgoingData();
		dt1.setId(outgoingData.getId());
		List<OutgoingData> dt = new ArrayList<>();
		dt.add(dt1);
		og.setData(dt);
		List<OutgoingResponseStatus> rs1 = new ArrayList<>();
		rs1.add(og);

		outgoingMessageResponce.setResponseStatus(rs1);
		return outgoingMessageResponceRepository.save(outgoingMessageResponce);
	}
	
	public OutgoingMessageResponce saveOutgoingMessage(OutgoingTemplateMessageDto outgoingMessageData, OutgoingStatus os, OutgoingData outgoingData) {

		OutgoingMessageResponce outgoingMessageResponce = new OutgoingMessageResponce();
		List<OutgoingTypeTemplateDTO> mr = outgoingMessageData.getType_template();
		OutgoingTypeTemplateDTO mb = mr.get(0);
		List<OutgoingTypeTemplate> outgoingTypeTemplates = new ArrayList<>();

		for (OutgoingTypeTemplateDTO dto : mr) {
		    OutgoingTypeTemplate assigned = new OutgoingTypeTemplate();
		    assigned.setName(dto.getName());
		    assigned.setAttributes(dto.getAttributes());
		    OutgoingTemplateLanguageDTO languageDTO = dto.getLanguage();
	    Language language = new Language(languageDTO.getLocale(), languageDTO.getPolicy());
    assigned.setLanguage(language);
		    outgoingTypeTemplates.add(assigned);
		}		

		outgoingMessageResponce.setRecipientwhatsapp(outgoingMessageData.getRecipient_whatsapp());
		outgoingMessageResponce.setRecipient_type(outgoingMessageData.getRecipient_type());
		outgoingMessageResponce.setMessage_type(outgoingMessageData.getMessage_type());
		outgoingMessageResponce.setOutgoingTypeTemplate(outgoingTypeTemplates);

		OutgoingResponseStatus og = new OutgoingResponseStatus();
		og.setMessage(os.getMessage());
		og.setStatus(os.getStatus());
		OutgoingData dt1 = new OutgoingData();
		dt1.setId(outgoingData.getId());
		List<OutgoingData> dt = new ArrayList<>();
		dt.add(dt1);
		og.setData(dt);
		List<OutgoingResponseStatus> rs1 = new ArrayList<>();
		rs1.add(og);

		outgoingMessageResponce.setResponseStatus(rs1);
		return outgoingMessageResponceRepository.save(outgoingMessageResponce);
	}

	public OutgoingMessageResponce saveOutgoingMessage(OutgoingTemplateMessageDtoWithoutTemplate outgoingMessageData, OutgoingStatus os, OutgoingData outgoingData) {

		OutgoingMessageResponce outgoingMessageResponce = new OutgoingMessageResponce();
		List<OutgoingTypeTemplateWithoutAttributeDTO> mr = outgoingMessageData.getType_template();
		OutgoingTypeTemplateWithoutAttributeDTO mb = mr.get(0);
		List<OutgoingTypeTemplate> outgoingTypeTemplates = new ArrayList<>();

		for (OutgoingTypeTemplateWithoutAttributeDTO dto : mr) {
		    OutgoingTypeTemplate assigned = new OutgoingTypeTemplate();
		    assigned.setName(dto.getName());
		    OutgoingTemplateLanguageDTO languageDTO = dto.getLanguage();
	    Language language = new Language(languageDTO.getLocale(), languageDTO.getPolicy());
    assigned.setLanguage(language);
		    outgoingTypeTemplates.add(assigned);
		}		

		outgoingMessageResponce.setRecipientwhatsapp(outgoingMessageData.getRecipient_whatsapp());
		outgoingMessageResponce.setRecipient_type(outgoingMessageData.getRecipient_type());
		outgoingMessageResponce.setMessage_type(outgoingMessageData.getMessage_type());
		outgoingMessageResponce.setOutgoingTypeTemplate(outgoingTypeTemplates);

		OutgoingResponseStatus og = new OutgoingResponseStatus();
		og.setMessage(os.getMessage());
		og.setStatus(os.getStatus());
		OutgoingData dt1 = new OutgoingData();
		dt1.setId(outgoingData.getId());
		List<OutgoingData> dt = new ArrayList<>();
		dt.add(dt1);
		og.setData(dt);
		List<OutgoingResponseStatus> rs1 = new ArrayList<>();
		rs1.add(og);

		outgoingMessageResponce.setResponseStatus(rs1);
		return outgoingMessageResponceRepository.save(outgoingMessageResponce);
	}

	
	public List<OutgoingMessageResponce> getAll() {
		return outgoingMessageResponceRepository.findAll();
	}

}
