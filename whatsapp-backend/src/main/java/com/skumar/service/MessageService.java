package com.skumar.service;

import com.skumar.entity.Message;
import com.skumar.exception.ChatException;
import com.skumar.exception.MessageException;
import com.skumar.exception.UserException;
import com.skumar.request.SendMessageReq;

import java.util.List;

public interface MessageService {

    public Message sendMessage(SendMessageReq sendMessageReq) throws UserException, ChatException;
    public List<Message> getChatsMessage(Integer chatId) throws ChatException;
    public Message getMessageById(Integer messageId) throws MessageException;
    public void deleteMessage(Integer messageId) throws MessageException;
}
