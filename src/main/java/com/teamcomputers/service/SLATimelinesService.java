package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.SLAFilterDto;
import com.teamcomputers.dto.SLATimelinesDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.SLATimelines;
import com.teamcomputers.repository.SLATimelinesRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class SLATimelinesService {

	@Autowired
	private SLATimelinesRepository slaTimelinesRepository;

	@Autowired 
	private UserRepository userRepository;
	public SLATimelines add(SLATimelinesDto sLATimelinesDto) {

		SLATimelines sLATimelines = new SLATimelines();

		sLATimelines.setPriorityName(sLATimelinesDto.getPriorityName());
		sLATimelines.setSlaTimeInHours(sLATimelinesDto.getSlaTimeInHours());
		sLATimelines.setCreatedDate(sLATimelinesDto.getCreatedDate());
		sLATimelines.setCreatedBy(userRepository.findById((int) (sLATimelinesDto.getCreatedBy())).orElse(null));
		sLATimelines.setUpdatedBy(userRepository.findById((int) (sLATimelinesDto.getUpdatedBy())).orElse(null));
		sLATimelines.setUpdatedDate(sLATimelinesDto.getUpdatedDate());
		sLATimelines.setStatus(sLATimelinesDto.isStatus());

		return slaTimelinesRepository.save(sLATimelines);
	}

	public SLATimelines getById(long id) {
		return slaTimelinesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SLA Id : " + id + " Unavailable"));
	}

	public List<SLATimelines> getAll() {
		return slaTimelinesRepository.findAll();
	}

	public SLATimelines update(SLATimelinesDto sLATimelinesDto) {

		SLATimelines sLATimelines = new SLATimelines();

		sLATimelines.setId(sLATimelinesDto.getId());
		sLATimelines.setPriorityName(sLATimelinesDto.getPriorityName());
		sLATimelines.setSlaTimeInHours(sLATimelinesDto.getSlaTimeInHours());
		sLATimelines.setCreatedDate(sLATimelinesDto.getCreatedDate());
		sLATimelines.setCreatedBy(userRepository.findById((int) (sLATimelinesDto.getCreatedBy())).orElse(null));
		sLATimelines.setUpdatedBy(userRepository.findById((int) (sLATimelinesDto.getUpdatedBy())).orElse(null));
		sLATimelines.setUpdatedDate(sLATimelinesDto.getUpdatedDate());
		sLATimelines.setStatus(sLATimelinesDto.isStatus());

		return slaTimelinesRepository.save(sLATimelines);
	}

	public boolean deleteById(long id) throws NotFoundException {
		SLATimelines sLATimelines = slaTimelinesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SLA Id : " + id + " Unavailable"));
		sLATimelines.setStatus(false); // Update status to false
		slaTimelinesRepository.save(sLATimelines);
		return true;
	}

	public List<SLAFilterDto> getActiveUsers() {
		List<SLATimelines> slaTimelines = slaTimelinesRepository.findByStatusOrderByPriorityNameAsc(true);
		List<SLAFilterDto> filteredCategories = new ArrayList<>();

		for (SLATimelines slaTimelines2 : slaTimelines) {
			SLAFilterDto filtered = new SLAFilterDto();

			filtered.setId(slaTimelines2.getId());
			filtered.setPriorityName(slaTimelines2.getPriorityName());
			filteredCategories.add(filtered);
		}

		return filteredCategories;
	}

}
