package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.model.UserEntity;
import com.teamcomputers.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping
	private UserEntity addorUpdate(@RequestBody UserEntity user){
		return this.userService.addorUpdate(user);
	}	
	@GetMapping
	private List<UserEntity> getAll(){
		return userService.getAll();
	}
	
	
	@GetMapping("/{userId}")
	private UserEntity getById(@PathVariable int userId){
		return userService.getById(userId);
	}
	
	
	@PutMapping("/{userId}")
	private UserEntity update (@PathVariable int userId,@RequestBody UserEntity user) {
		user.setUserId(userId);
		return userService.addorUpdate(user);
}
}
