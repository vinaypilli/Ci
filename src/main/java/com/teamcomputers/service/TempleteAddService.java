package com.teamcomputers.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.TempleteAddDTO;
import com.teamcomputers.dto.TicketDto;
import com.teamcomputers.model.TemplateButtonsEnum;
import com.teamcomputers.model.TemplateHeaderEnum;
import com.teamcomputers.model.TempleteAdd;
import com.teamcomputers.repository.TempleteAddRepository;

import javassist.NotFoundException;

@Service
public class TempleteAddService {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private TempleteAddRepository templeteAddRepository;

//	public TempleteAddDTO createTempleteAdd(TempleteAddDTO templeteAddDTO) {
//		TempleteAdd templeteAdd = convertToEntity(templeteAddDTO);
//		TempleteAdd savedTempleteAdd = templeteAddRepository.save(templeteAdd);
//		return convertToDTO(savedTempleteAdd);
//	}

	public TempleteAdd saveTemplate(MultipartFile file,String templateAdd1) throws IOException {
		// Step 1: Check the Header condition and save file if required

		TempleteAddDTO templeteAddDTO = mapper.readValue(templateAdd1, TempleteAddDTO.class);

		TempleteAdd templateAdd = new TempleteAdd();

		templateAdd.setTemplateName(templeteAddDTO.getTemplateName());
		templateAdd.setCategory(templeteAddDTO.getCategory());
		templateAdd.setLanguage(templeteAddDTO.getLanguage());
		templateAdd.setHeader(templeteAddDTO.getHeader());

		if (templateAdd.getHeader() != TemplateHeaderEnum.None && templateAdd.getHeader() != TemplateHeaderEnum.Text) {
			templateAdd.setFiles(file.getBytes());

		} else if (templateAdd.getHeader() != TemplateHeaderEnum.Text) {
			templateAdd.setText(templeteAddDTO.getText());
		}

		templateAdd.setBody(templeteAddDTO.getBody());
		templateAdd.setFooter(templeteAddDTO.getFooter());

		templateAdd.setButtons(templeteAddDTO.getButtons());

		if (templeteAddDTO.getButtons() == TemplateButtonsEnum.Quick_Replies) {
			Map<String, String> buttonData = new HashMap<>();
			buttonData.put("button1", "Button 1 Label");
			buttonData.put("button2", "Button 2 Label");
			templateAdd.setButtonData(buttonData);
		} else if (templeteAddDTO.getButtons() == TemplateButtonsEnum.Call_To_Action) {

		}
		return templeteAddRepository.save(templateAdd);
	}
	
	public List<TempleteAdd> getAllTempleteAdds() {
        return templeteAddRepository.findAll();
    }

//	public TempleteAddDTO getTempleteAddById(Long id) throws NotFoundException {
//		Optional<TempleteAdd> optionalTempleteAdd = templeteAddRepository.findById(id);
//		if (optionalTempleteAdd.isPresent()) {
//			TempleteAdd templeteAdd = optionalTempleteAdd.get();
//			return convertToDTO(templeteAdd);
//		}
//		throw new NotFoundException("TempleteAdd not found with id: " + id);
//	}
//
//	public List<TempleteAddDTO> getAllTempleteAdds() {
//		List<TempleteAdd> templeteAdds = templeteAddRepository.findAll();
//		return convertToDTOList(templeteAdds);
//	}
//
//	public TempleteAddDTO updateTempleteAdd(Long id, TempleteAddDTO templeteAddDTO) throws NotFoundException {
//		Optional<TempleteAdd> optionalTempleteAdd = templeteAddRepository.findById(id);
//		if (optionalTempleteAdd.isPresent()) {
//			TempleteAdd templeteAdd = optionalTempleteAdd.get();
//			templeteAdd.setTemplateName(templeteAddDTO.getTemplateName());
//			templeteAdd.setLanguage(templeteAddDTO.getLanguage());
//			templeteAdd.setBody(templeteAddDTO.getBody());
//			templeteAdd.setCategory(templeteAddDTO.getCategory());
//			templeteAdd.setHeader(templeteAddDTO.getHeader());
//			templeteAdd.setText(templeteAddDTO.getText());
//			templeteAdd.setFiles(templeteAddDTO.getFiles());
//			templeteAdd.setButtons(templeteAddDTO.getButtons());
//			templeteAdd.setButtonData(templeteAddDTO.getButtonData());
//			TempleteAdd updatedTempleteAdd = templeteAddRepository.save(templeteAdd);
//			return convertToDTO(updatedTempleteAdd);
//		}
//		throw new NotFoundException("TempleteAdd not found with id: " + id);
//	}
//
//	public void deleteTempleteAdd(Long id) throws NotFoundException {
//		Optional<TempleteAdd> optionalTempleteAdd = templeteAddRepository.findById(id);
//		if (optionalTempleteAdd.isPresent()) {
//			templeteAddRepository.deleteById(id);
//		} else {
//			throw new NotFoundException("TempleteAdd not found with id: " + id);
//		}
//	}
//
//	private TempleteAddDTO convertToDTO(TempleteAdd templeteAdd) {
//		TempleteAddDTO templeteAddDTO = new TempleteAddDTO();
//		templeteAddDTO.setTemplateName(templeteAdd.getTemplateName());
//		templeteAddDTO.setLanguage(templeteAdd.getLanguage());
//		templeteAddDTO.setBody(templeteAdd.getBody());
//		templeteAddDTO.setCategory(templeteAdd.getCategory());
//		templeteAddDTO.setHeader(templeteAdd.getHeader());
//		templeteAddDTO.setText(templeteAdd.getText());
//		templeteAddDTO.setFiles(templeteAdd.getFiles());
//		templeteAddDTO.setButtons(templeteAdd.getButtons());
//		templeteAddDTO.setButtonData(templeteAdd.getButtonData());
//		return templeteAddDTO;
//	}
//
//	private List<TempleteAddDTO> convertToDTOList(List<TempleteAdd> templeteAdds) {
//		return templeteAdds.stream().map(this::convertToDTO).collect(Collectors.toList());
//	}
//
//	private TempleteAdd convertToEntity(TempleteAddDTO templeteAddDTO) {
//		TempleteAdd templeteAdd = new TempleteAdd();
//		templeteAdd.setTemplateName(templeteAddDTO.getTemplateName());
//		templeteAdd.setLanguage(templeteAddDTO.getLanguage());
//		templeteAdd.setBody(templeteAddDTO.getBody());
//		templeteAdd.setCategory(templeteAddDTO.getCategory());
//		templeteAdd.setHeader(templeteAddDTO.getHeader());
//		templeteAdd.setText(templeteAddDTO.getText());
//		templeteAdd.setFiles(templeteAddDTO.getFiles());
//		templeteAdd.setButtons(templeteAddDTO.getButtons());
//		templeteAdd.setButtonData(templeteAddDTO.getButtonData());
//		return templeteAdd;
//	}
}
