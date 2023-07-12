package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.OutgoingData;
import com.teamcomputers.repository.OutgoingDatasRepository;

@Service
public class OutgoingDatasService {
    @Autowired
    private OutgoingDatasRepository outgoingDatasRepository;

    public OutgoingData saveDatas(OutgoingData outgoingData) {
        return outgoingDatasRepository.save(outgoingData);
    }

    // Add other service methods as required
}