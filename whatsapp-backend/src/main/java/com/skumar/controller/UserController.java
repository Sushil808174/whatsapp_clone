package com.skumar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skumar.entity.Users;
import com.skumar.request.UpdateUserRequest;
import com.skumar.service.UserService;

@RestController
@RequestMapping("/whatsapp/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@GetMapping("/hello")
	public ResponseEntity<String> HelloHandler(){
		return new ResponseEntity<>("Hello world!", HttpStatus.OK);
	}
	@GetMapping("/check")
	public ResponseEntity<?> checkHandler(Authentication auth){
		return new ResponseEntity<>(auth.getCredentials(), HttpStatus.OK);
	}
	
	
	@PostMapping("/create-user")
	public ResponseEntity<Users> createUserHandler(@RequestBody Users user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Users newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user-profile")
	public ResponseEntity<Users> getUserProfileHandler(Authentication auth){
		Users users = userService.findUserProfile(auth);
		
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/search-user/{input}")
	public ResponseEntity<List<Users>> searchUserHandler(@PathVariable String input){
		List<Users> users = userService.searchUser(input);
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	
	@PutMapping("/update-profile")
	public ResponseEntity<Users> updateUserHandler(@RequestBody UpdateUserRequest userReq, Authentication auth){
		Users user = userService.updateUserProfile(auth.getName(), userReq);
		return new ResponseEntity<Users>(user,HttpStatus.ACCEPTED);
	}
	
	
	
	public ResponseEntity<Users> sighInUserHandler(Authentication auth){
		
		System.out.println(auth.getCredentials());
	
		Users user = userService.findUserByEmail(auth.getName());
		
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
		
	}
	
}
