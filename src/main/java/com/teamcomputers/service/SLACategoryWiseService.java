package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.SLACategoryWiseDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.SLACategoryWise;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.SLACategoryWiseRepository;
import com.teamcomputers.repository.SLATimelinesRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class SLACategoryWiseService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private SLACategoryWiseRepository slaCategoryWiseRepository;
	
	@Autowired
	private ServiceTitleRepository serviceTitleRepository;
	
	@Autowired
	private SLATimelinesRepository slaTimelinesRepository;
	
	public SLACategoryWise add(SLACategoryWiseDto slaCategoryWiseDto) {

		SLACategoryWise slaCategoryWise = new SLACategoryWise();
		
		slaCategoryWise.setCategory(categoryRepository.findById((long) slaCategoryWiseDto.getCategoryId()).orElse(null));
		slaCategoryWise.setSubCategory(subCategoryRepository.findById((int) slaCategoryWiseDto.getSubCategoryId()).orElse(null));
		slaCategoryWise.setServiceTitle(serviceTitleRepository.findById((int) slaCategoryWiseDto.getServiceTitleId()).orElse(null));
		slaCategoryWise.setPriority(slaTimelinesRepository.findById(slaCategoryWiseDto.getPriority()).orElse(null));
		slaCategoryWise.setTimeline(slaCategoryWiseDto.getTimeline());
		slaCategoryWise.setCreatedDate(slaCategoryWiseDto.getCreatedDate());
		slaCategoryWise.setCreatedBy(userRepository.findById((int) (slaCategoryWiseDto.getCreatedBy())).orElse(null));
		slaCategoryWise.setUpdatedBy(userRepository.findById((int) (slaCategoryWiseDto.getUpdatedBy())).orElse(null));
		slaCategoryWise.setUpdatedDate(slaCategoryWiseDto.getUpdatedDate());
		slaCategoryWise.setStatus(slaCategoryWiseDto.isStatus());

		return slaCategoryWiseRepository.save(slaCategoryWise);
	}
	
	public SLACategoryWise getById(long sId) {
		return slaCategoryWiseRepository.findById(sId)
				.orElseThrow(() -> new ResourceNotFoundException("SLA Id : " + sId + " Unavailable"));
	}

	public List<SLACategoryWise> getAll() {
		return slaCategoryWiseRepository.findAll();
	}
	
	public SLACategoryWise update(SLACategoryWiseDto slaCategoryWiseDto) {

		SLACategoryWise slaCategoryWise = new SLACategoryWise();
		slaCategoryWise.setsId(slaCategoryWiseDto.getsId());
		slaCategoryWise.setCategory(categoryRepository.findById((long) slaCategoryWiseDto.getCategoryId()).orElse(null));
		slaCategoryWise.setSubCategory(subCategoryRepository.findById((int) slaCategoryWiseDto.getSubCategoryId()).orElse(null));
		slaCategoryWise.setServiceTitle(serviceTitleRepository.findById((int) slaCategoryWiseDto.getServiceTitleId()).orElse(null));
		slaCategoryWise.setPriority(slaTimelinesRepository.findById(slaCategoryWiseDto.getPriority()).orElse(null));
		slaCategoryWise.setTimeline(slaCategoryWiseDto.getTimeline());
		slaCategoryWise.setCreatedDate(slaCategoryWiseDto.getCreatedDate());
		slaCategoryWise.setCreatedBy(userRepository.findById((int) (slaCategoryWiseDto.getCreatedBy())).orElse(null));
		slaCategoryWise.setUpdatedBy(userRepository.findById((int) (slaCategoryWiseDto.getUpdatedBy())).orElse(null));
		slaCategoryWise.setUpdatedDate(slaCategoryWiseDto.getUpdatedDate());
		slaCategoryWise.setStatus(slaCategoryWiseDto.isStatus());

		return slaCategoryWiseRepository.save(slaCategoryWise);
	}
	
	public boolean deleteById(long sId) throws NotFoundException {
		SLACategoryWise slaCategoryWise = slaCategoryWiseRepository.findById(sId)
				.orElseThrow(() -> new ResourceNotFoundException("SLA Id : " + sId + " Unavailable"));
		slaCategoryWise.setStatus(false); // Update status to false
		slaCategoryWiseRepository.save(slaCategoryWise);
		return true;
	}
	
	
}
