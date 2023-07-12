package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.RoleDto;
import com.teamcomputers.dto.RoleFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.RoleEntity;
import com.teamcomputers.repository.RoleRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class RoleService {
	private Logger logger=Logg.getLogger(RoleService.class);
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	public RoleEntity add(RoleDto roleDto) {
		try {
	        // Log the start of the method
	        logger.info("add method called with roleDto from role service class: {}", roleDto);

		RoleEntity role = new RoleEntity();

		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setRoleDescription(roleDto.getRoleDescription());
		role.setCreatedBy(userRepository.findById((int) (roleDto.getCreatedBy())).orElse(null));		
		role.setUpdatedBy(userRepository.findById((int) (roleDto.getUpdatedBy())).orElse(null));		
		role.setUpdatedDate(roleDto.getUpdatedDate());
		role.setStatus(roleDto.isStatus());
		return roleRepository.save(role);
		}catch(Exception e)
		{
			 // Log the exception and error message
	        logger.error("Error occurred while adding role in role service class", e);

	        throw new RuntimeException(e);
		}
	}

	public RoleEntity update(RoleDto roleDto) {
		try {
	        // Log the start of the method
	        logger.info("update method called with roleDto from service class: {}", roleDto);
		RoleEntity role = new RoleEntity();

		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setRoleDescription(roleDto.getRoleDescription());
		role.setCreatedBy(userRepository.findById((int) (roleDto.getCreatedBy())).orElse(null));		
		role.setUpdatedBy(userRepository.findById((int) (roleDto.getUpdatedBy())).orElse(null));		
		role.setUpdatedDate(roleDto.getUpdatedDate());
		role.setStatus(roleDto.isStatus());
		
		 RoleEntity updatedRole = roleRepository.save(role);
		 
		 logger.info("update method executed successfully. Updated role in service class: {}",updatedRole);
		return roleRepository.save(role);
		
		}catch(Exception e)
		{
			logger.error("Error occurred while updating role in role service class", e);

	        throw new RuntimeException(e);
		}
	}

	public List<RoleFilterDto> getActiveUsers() {
		try
		{
			logger.info("getActiveUsers method called from service class");
			
		List<RoleEntity> roletEntity = roleRepository.findByStatusOrderByRoleNameAsc(true);
		List<RoleFilterDto> roleFilterDto = new ArrayList<>();

		for (RoleEntity roleTitle : roletEntity) {
			RoleFilterDto roleservice = new RoleFilterDto();

			roleservice.setRoleId(roleTitle.getRoleId());
			roleservice.setRoleName(roleTitle.getRoleName());
			roleFilterDto.add(roleservice);
		}
		 logger.info("getActiveUsers method executed successfully in service class. Retrieved {} active users", roleFilterDto.size());
		return roleFilterDto;
		}catch(Exception e)
		{
			 logger.error("Error occurred while retrieving active users service class", e);

		        throw new RuntimeException(e);
		}
	}

	public RoleEntity getById(int roleId) {
		try {
	        // Log the start of the method
	        logger.info("getById method called with roleId from service class: {}", roleId);
	        
	        RoleEntity roleEntity = roleRepository.findById(roleId)
	        		
		//return roleRepository.findById(roleId)
	        		
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id :" + roleId + "Unavailable"));
		 logger.info("RoleEntity retrieved successfully from service class: {}", roleEntity);
		 
		// return roleRepository.findById(roleId);
		 
	    return roleEntity;
	     
		  } catch (Exception e) {
		        // Log the exception and error message
		        logger.error("Error occurred while retrieving RoleEntity with roleId service class: {}", roleId, e);

		        throw new RuntimeException(e); 
		    }

	}

	public List<RoleEntity> getAll() {
		 try {
		        // Log the start of the method
		        logger.info("getAll method called from Role service class");

		return roleRepository.findAll();
		 } catch (Exception e) {
		        // Log the exception and error message
		        logger.error("Error occurred while retrieving RoleEntities from service class", e);

		        throw new RuntimeException(e);
		 }
	}

	public boolean deleteById(int roleId) throws NotFoundException {
		try {
	        // Log the start of the method
	        logger.info("deleteById method called with roleId in Role service class: {}", roleId);
		RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(
				() -> new ResourceNotFoundException("customer Id : " + roleId + " is Not Present in DataBase"));
		roleEntity.setStatus(false); // Update status to false
		roleRepository.save(roleEntity);
		return true;
		} catch (Exception e) {
	        // Log the exception and error message
	        logger.error("Error occurred while deleting RoleEntity with roleId in Role service class: {}", roleId, e);

	        throw new RuntimeException(e);
	    }
	}

}