package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.ServiceFilterDto;
import com.teamcomputers.dto.ServiceTitleDto;
import com.teamcomputers.dto.ServicePriorityDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.repository.SLATimelinesRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class ServiceTitleService {
	private Logger logger=Logg.getLogger(ServiceTitleService.class);
	@Autowired
	private ServiceTitleRepository serviceTitleRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private SLATimelinesRepository slaTimelinesRepository;
	@Autowired
	private UserRepository userRepository;
	

	public ServiceTitleEntity add(ServiceTitleDto serviceTitleDto) throws DuplicateUserException {

		 try {
		        ServiceTitleEntity userDup = serviceTitleRepository.findByServiceName(serviceTitleDto.getServiceName());
		        if (userDup != null) {
		            throw new DuplicateUserException("Service name already exists");
		        }

		        ServiceTitleEntity serviceTitleEntity = new ServiceTitleEntity();

		        serviceTitleEntity.setServiceId(serviceTitleDto.getServiceId());
		        serviceTitleEntity.setServiceName(serviceTitleDto.getServiceName());
		        serviceTitleEntity.setCreatedBy(userRepository.findById((int) (serviceTitleDto.getCreatedBy())).orElse(null));
		        serviceTitleEntity.setUpdatedBy(userRepository.findById((int) (serviceTitleDto.getUpdatedBy())).orElse(null));
		        serviceTitleEntity.setCreatedDate(serviceTitleDto.getCreatedDate());
		        serviceTitleEntity.setUpdatedDate(serviceTitleDto.getUpdatedDate());
		        serviceTitleEntity.setDefaultPriority(slaTimelinesRepository.findById(serviceTitleDto.getDefaultPriority()).orElse(null));
		        serviceTitleEntity.setStatus(serviceTitleDto.isStatus());
		        serviceTitleEntity.setSubCategory(subCategoryRepository.findById(serviceTitleDto.getSubCategoryId()).orElse(null));

		        ServiceTitleEntity savedServiceTitle = serviceTitleRepository.save(serviceTitleEntity);
		        logger.info("Service details successfully saved. Service ID: {}", savedServiceTitle.getServiceId());
		        return savedServiceTitle;
		    } catch (DuplicateUserException e) {
		        logger.error("Failed to save service details due to duplicate service name. Error: {}", e.getMessage());
		        throw e;
		    } catch (Exception e) {
		        logger.error("Failed to save service details. Error: {}", e.getMessage());
		        throw new RuntimeException( e);
		    }
		}

	public ServiceTitleEntity getById(int serviceId) {
		try {
	        return serviceTitleRepository.findById(serviceId)
	                .orElseThrow(() -> new ResourceNotFoundException("Service Id: " + serviceId + " Unavailable"));
	    } catch (ResourceNotFoundException e) {
	        logger.error("Failed to find service by ID from ServiceTitleService{}. Error: {}", serviceId, e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
		

	public List<ServicePriorityDto> getPriorityById(int serviceId) {
		 try {
		        List<ServiceTitleEntity> serviceTitleEntities = serviceTitleRepository.findByStatus(true);
		        List<ServicePriorityDto> servicePriorityDtos = new ArrayList<>();

		        for (ServiceTitleEntity serviceTitle : serviceTitleEntities) {
		            ServicePriorityDto filteredService = new ServicePriorityDto();
		            filteredService.setDefaultPriority(serviceTitle.getDefaultPriority());
		            servicePriorityDtos.add(filteredService);
		        }

		        return servicePriorityDtos;
		    } catch (Exception e) {
		        logger.error("Failed to retrieve service priorities from serviceTitle. Error: {}", e.getMessage());
		        throw new RuntimeException(e);
		    	}
	}

	
	public List<ServiceFilterDto> getActiveUsers() {
		 try {
		        List<ServiceTitleEntity> serviceTitleEntities = serviceTitleRepository.findByStatus(true);
		        List<ServiceFilterDto> serviceFilterDtos = new ArrayList<>();

		        for (ServiceTitleEntity serviceTitle : serviceTitleEntities) {
		            ServiceFilterDto filteredService = new ServiceFilterDto();
		            filteredService.setServiceId(serviceTitle.getServiceId());
		            filteredService.setServiceName(serviceTitle.getServiceName());
		            serviceFilterDtos.add(filteredService);
		        }

		        return serviceFilterDtos;
		    } catch (Exception e) {
		        logger.error("Failed to retrieve active users serviceTitle service. Error: {}", e.getMessage());
		        throw new RuntimeException(e);
		    }
		//return serviceFilterDto;
	}

	public List<ServiceFilterDto> getAllActiveServiceTitlesBySubCategoryId(int subCategoryId) {
		try {
	        SubCategory subCategory = new SubCategory();
	        subCategory.setSubCategoryId(subCategoryId);
	        List<ServiceTitleEntity> serviceTitleEntities = serviceTitleRepository
	                .findBySubCategoryAndStatusTrueOrderByServiceNameAsc(subCategory);
	        List<ServiceFilterDto> filteredServiceTitles = new ArrayList<>();

	        for (ServiceTitleEntity serviceTitle : serviceTitleEntities) {
	            ServiceFilterDto filteredService = new ServiceFilterDto();
	            filteredService.setServiceId(serviceTitle.getServiceId());
	            filteredService.setServiceName(serviceTitle.getServiceName());
	            filteredServiceTitles.add(filteredService);
	        }
	        return filteredServiceTitles;
	    } catch (Exception e) {
	        logger.error("Failed to retrieve all active service titles by subcategory ID in serviceTitle service class  {}. Error: {}", subCategoryId, e.getMessage());
	        throw  new RuntimeException(e);
	    }
	}

	public List<ServiceTitleEntity> getAll() {
		try {
	        return serviceTitleRepository.findAllByOrderByServiceIdAsc();
	    } catch (Exception e) {
	        logger.error("Failed to retrieve all service titles service class. Error: {}", e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
		//return serviceTitleRepository.findAllByOrderByServiceIdAsc();
	
	
	public List<ServicePriorityDto> findPriorityNameByServiceId(int serviceId) {
		try {
	        List<ServiceTitleEntity> serviceTitleEntity = serviceTitleRepository.findPriorityNameByServiceId(serviceId);
	        List<ServicePriorityDto> filteredSubCategories = new ArrayList<>();

	        for (ServiceTitleEntity serviceTitle : serviceTitleEntity) {
	            ServicePriorityDto filteredCategory = new ServicePriorityDto();

	            filteredCategory.setDefaultPriority(serviceTitle.getDefaultPriority());
	            filteredSubCategories.add(filteredCategory);
	        }

	        return filteredSubCategories;
	    } catch (Exception e) {
	        logger.error("Failed to find priority names for service with ID from serviceTitle service class {}. Error: {}", serviceId, e.getMessage());
	        throw new RuntimeException(e);
	    }
   }
	

	public ServiceTitleEntity update(ServiceTitleDto serviceTitleDto) {

		try {
	        ServiceTitleEntity serviceTitleEntity = new ServiceTitleEntity();

	        serviceTitleEntity.setServiceId(serviceTitleDto.getServiceId());
	        serviceTitleEntity.setServiceName(serviceTitleDto.getServiceName());
	        serviceTitleEntity.setCreatedDate(serviceTitleDto.getCreatedDate());
	        serviceTitleEntity.setCreatedBy(userRepository.findById((int) serviceTitleDto.getCreatedBy()).orElse(null));
	        serviceTitleEntity.setUpdatedBy(userRepository.findById((int) serviceTitleDto.getUpdatedBy()).orElse(null));
	        serviceTitleEntity.setUpdatedDate(serviceTitleDto.getUpdatedDate());
	        serviceTitleEntity.setDefaultPriority(slaTimelinesRepository.findById(serviceTitleDto.getDefaultPriority()).orElse(null));

	        serviceTitleEntity.setStatus(serviceTitleDto.isStatus());

	        serviceTitleEntity.setSubCategory(subCategoryRepository.findById(serviceTitleDto.getSubCategoryId()).orElse(null));

	        ServiceTitleEntity updatedServiceTitle = serviceTitleRepository.save(serviceTitleEntity);
	        logger.info("Service Title updated successfully with ID: {}", updatedServiceTitle.getServiceId());
	        return updatedServiceTitle;
	    } catch (Exception e) {
	        logger.error("Failed to update Service Title in servic class. Error: {}", e.getMessage());
	        throw new RuntimeException(e);
	    }
	}

	public boolean deleteById(int serviceId) throws NotFoundException {
		try {
	        ServiceTitleEntity serviceTitleEntity = serviceTitleRepository.findById(serviceId)
	                .orElseThrow(() -> new ResourceNotFoundException("serviceId: " + serviceId + " is Not Present in Database"));
	        serviceTitleEntity.setStatus(false); // Update status to false
	        serviceTitleRepository.save(serviceTitleEntity);
	        logger.info("Service Title deleted successfully with ID serviceTitle service class: {}", serviceId);
	        return true;
	    } catch (Exception e) {
	        logger.error("Failed to delete Service Title erviceTitle service class. Error: {}", e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
}

