package com.skumar.service;

import java.util.List;

import com.skumar.entity.Chat;
import com.skumar.entity.Users;
import com.skumar.exception.ChatException;
import com.skumar.exception.UserException;
import com.skumar.request.GroupChatReq;

public interface ChatService {
	
	public Chat createChat(Users fromUser, Integer reqUser) throws UserException;
	public Chat findChatById(Integer chatId) throws ChatException;
	public List<Chat> findAllChatByUserEmail(String email) throws UserException;
	public Chat createGroup(GroupChatReq req, Users reqUser) throws UserException;
	public Chat addUserToGroup(String userEmail,Integer chatId,Users reqUser) throws UserException,ChatException;
	public Chat renameGroup(Integer chatId,String groupName,Users reqUser) throws UserException,ChatException;
	public Chat removeFromGroup(Integer chatId,String userEmail,Users reqUser) throws UserException,ChatException;
	public void deleteChat(Integer chatId,String userEmail) throws UserException,ChatException;
	
}
