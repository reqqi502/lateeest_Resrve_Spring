package com.basmaonline.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basmaonline.ws.request.UserRequest;
import com.basmaonline.ws.responses.UserResponse;
import com.basmaonline.ws.services.UserService;
import com.basmaonline.ws.shared.dto.UserDto;

@RestController

@RequestMapping("/users") // localhost:8080/users lien dyal App
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(path ="/{id}")  // Method Get pour Affiche list Users  // Serialize
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		
	UserDto userDto = userService.getUserByUserId(id);
	
	UserResponse userResponse = new UserResponse();
	
	BeanUtils.copyProperties(userDto, userResponse);
	
	return new ResponseEntity<>(userResponse ,HttpStatus.OK);
	
	}
	
	@PostMapping // Method Post Pour Cree un User // deSerialize
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createUser =  userService.createUser(userDto);
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		
		return new ResponseEntity<>(userResponse ,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping(path ="/{id}") // Method Put pour modifier un user
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto updateUser =  userService.updateUser(id , userDto);
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(updateUser, userResponse);
		
		return new ResponseEntity<>(userResponse ,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path ="/{id}") // Method delete pour supprime un user
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}