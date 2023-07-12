package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.OutgoingType_text;
import com.teamcomputers.repository.OutgoingType_textRepository;

@Service
public class OutgoingType_textService {
    @Autowired
    private OutgoingType_textRepository outgoingType_textRepository;

    public OutgoingType_text saveTypeText(OutgoingType_text outgoingType_text) {
        return outgoingType_textRepository.save(outgoingType_text);
    }

    // Add other service methods as required
}
