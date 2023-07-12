package com.teamcomputers.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.UserEntity;
import com.teamcomputers.repository.UserEntityRepository;

@Service
public class UserService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	public UserEntity addorUpdate(UserEntity user) {
		return userEntityRepository.save(user);
	}
	
	public boolean deleteById(int id) {
		userEntityRepository.deleteById(id);
		 return true;
	}
    public List<UserEntity> getAll(){
    	return userEntityRepository.findAll() ;
    }
    public UserEntity getById(int id) {
		return userEntityRepository.findById(id).get();
	}
}
