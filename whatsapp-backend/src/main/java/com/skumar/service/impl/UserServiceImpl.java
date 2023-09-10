package com.skumar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.skumar.entity.Users;
import com.skumar.exception.UserException;
import com.skumar.repository.UserRepository;
import com.skumar.request.UpdateUserRequest;
import com.skumar.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public Users findUserByEmail(String emailId) throws UserException{
		Optional<Users> opt = userRepository.findByEmail(emailId);
		
		if(opt.isPresent()) {
			Users user = opt.get();
			return user;
		}else{
			throw new UserException("User not found with given email id : "+emailId);
		}
	}

	@Override
	public Users findUserById(Integer userId) {
		
		Optional<Users> opt = userRepository.findById(userId);
		
		if(opt.isPresent()) {
			Users user = opt.get();
			return user;
		}else{
			throw new UserException("User not found with given id : "+userId);
		}
	}

	@Override
	public Users updateUserProfile(String emailId, UpdateUserRequest req) throws UserException {
		
		Users user = findUserByEmail(emailId);
		
		if(user.getFull_name()!=null) {
			user.setFull_name(req.getFull_name());
		}
		if(user.getProfile_picture()!=null) {
			user.setProfile_picture(req.getProfilePicture());
		}
		
		return userRepository.save(user);
	}

	@Override
	public List<Users> searchUser(String query) {
		List<Users> users = userRepository.searchUser(query);
		return users;
	}

	@Override
	public Users findUserProfile(Authentication auth) {
		String email = auth.getName();
		Users user = findUserByEmail(email);
		return user;
	}

	@Override
	public Users createUser(Users user) throws UserException {
	
		if(user==null) throw new UserException("Invalid credentidals");
		
		Optional<Users> opt = userRepository.findByEmail(user.getEmail());
		if(opt.isEmpty()) {
			Users getUser = opt.get();
			userRepository.save(getUser);
		}
		throw new UserException("User already registered");
	}

}
