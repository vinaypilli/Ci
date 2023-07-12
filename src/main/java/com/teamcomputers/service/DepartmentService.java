package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.DepartmentDto;
import com.teamcomputers.dto.DepartmentfilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.repository.DepartmentRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class DepartmentService {
	private Logger logger=Logg.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	public DepartmentEntity add(DepartmentDto departmentDto) {
		try {
			
		 logger.info("Adding department in service class: {}", departmentDto.getDepartmentName());


		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDepartmentId(departmentDto.getDepartmentId());
		departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
		departmentEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		departmentEntity.setDepartmentHead(departmentDto.getDepartmentHead());
		departmentEntity.setDescription(departmentDto.getDescription());
		departmentEntity.setCreatedBy(userRepository.findById((int) (departmentDto.getCreatedBy())).orElse(null));		
		departmentEntity.setUpdatedBy(userRepository.findById((int) (departmentDto.getUpdatedBy())).orElse(null));
		departmentEntity.setCreatedDate(departmentDto.getCreatedDate());
		
		departmentEntity.setUpdatedDate(departmentDto.getUpdatedDate());

		return departmentRepository.save(departmentEntity);
		} catch (Exception e) {
		    logger.error("Error adding department in service class: {}", departmentDto.getDepartmentName(), e);
		    throw new RuntimeException(e);
		}
	}

	public DepartmentEntity getById(int departmentId) {
		try
		{
			
			logger.info("Fetching department by ID from service class: {}", departmentId);
		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("department Id : " + departmentId + " Unavailable"));
		} catch (Exception e) {
		    logger.error("Error fetching department by ID from service class: {}", departmentId, e);
		    throw new RuntimeException(e);
		}
	}

	public List<DepartmentEntity> getAll() {
		try
		{
			logger.info("Get All department by ID from service class: {}");	
		
		return departmentRepository.findAll();
		}catch (Exception e) {
		    logger.error("Error Get All department by ID from service  class: {}", e);
		    throw new RuntimeException(e);
		}

	}

	public DepartmentEntity update(DepartmentDto departmentDto) {
		try
		{
		logger.info("Updating department id in service class successful : {}", departmentDto.getDepartmentName());
		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDepartmentId(departmentDto.getDepartmentId());
		departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
		departmentEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		departmentEntity.setDepartmentHead(departmentDto.getDepartmentHead());
		departmentEntity.setDescription(departmentDto.getDescription());
		departmentEntity.setCreatedBy(userRepository.findById((int) (departmentDto.getCreatedBy())).orElse(null));		
		departmentEntity.setUpdatedBy(userRepository.findById((int) (departmentDto.getUpdatedBy())).orElse(null));
		departmentEntity.setCreatedDate(departmentDto.getCreatedDate());
		
		departmentEntity.setUpdatedDate(departmentDto.getUpdatedDate());
		return departmentRepository.save(departmentEntity);
		} catch (Exception e) {
		    logger.error("Error updating department in service class: {}", departmentDto.getDepartmentName(), e);
		    throw new RuntimeException(e);
		}
	}

	public List<DepartmentfilterDto> getActiveUsers() {
		try
		{
			logger.info("Fetching active users from seervice class");
		
		List<DepartmentEntity> departmentEntity = departmentRepository.findByStatusOrderByDepartmentNameAsc(true);
		List<DepartmentfilterDto> sdepartmentFilterDto = new ArrayList<>();

		for (DepartmentEntity departmentTitle : departmentEntity) {
			DepartmentfilterDto departmentservice = new DepartmentfilterDto();

			departmentservice.setDepartmentId(departmentTitle.getDepartmentId());
			departmentservice.setDepartmentName(departmentTitle.getDepartmentName());
			sdepartmentFilterDto.add(departmentservice);
		}

		return sdepartmentFilterDto;
		} catch (Exception e) {
		    logger.error("Error fetching active users from service class", e);
		    throw new RuntimeException(e);
		}
	}

	public boolean deleteById(int departmentId) throws NotFoundException {
		try
		{
			logger.info("Deleting department with ID in service class: {}", departmentId);	
		
		DepartmentEntity department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("department Id : " + departmentId + " is Not Present in DataBase"));
		department.setStatus(false); // Update status to false
		departmentRepository.save(department);
		return true;
		} catch (Exception e) {
		    logger.error("Error deleting department with ID in service class: {}", departmentId, e);
		    throw new RuntimeException(e);
		}
		
	}

//	public boolean deleteById(int id) {
//		departmentRepository.deleteById(id);
//		return true;
//	}
}