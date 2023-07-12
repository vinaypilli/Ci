package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Context;
import com.teamcomputers.repository.ContextRepository;

	
@Service
public class ContextService {
	
	@Autowired
	private ContextRepository contextRepository;
	
	public Context saveTypeText(Context context) {
        return contextRepository.save(context);
    }
}
