package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.TextType;
import com.teamcomputers.repository.TextTypeRepository;
@Service
public class TextTypeService {

	@Autowired
	private TextTypeRepository textTypeRepository;

	public TextType saveTypeText(TextType context) {
		return textTypeRepository.save(context);
	}
}
