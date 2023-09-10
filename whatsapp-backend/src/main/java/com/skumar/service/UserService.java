package com.skumar.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.skumar.entity.Users;
import com.skumar.exception.UserException;
import com.skumar.request.UpdateUserRequest;

public interface UserService {
	
	public Users createUser(Users user) throws UserException;
	public Users findUserByEmail(String emailId) throws UserException;
	public Users findUserById(Integer userId)throws UserException;
	public Users findUserProfile(Authentication auth);
	public Users updateUserProfile(String emailId,UpdateUserRequest req) throws UserException;
	public List<Users> searchUser(String query);
}
