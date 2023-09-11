package com.skumar.service.impl;

import com.skumar.entity.Chat;
import com.skumar.entity.Message;
import com.skumar.entity.Users;
import com.skumar.exception.ChatException;
import com.skumar.exception.MessageException;
import com.skumar.exception.UserException;
import com.skumar.repository.MessageRepository;
import com.skumar.request.SendMessageReq;
import com.skumar.service.ChatService;
import com.skumar.service.MessageService;
import com.skumar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @Override
    public Message sendMessage(SendMessageReq sendMessageReq) throws UserException, ChatException {
        Users user = userService.findUserById(sendMessageReq.getUserId());
        Chat chat = chatService.findChatById(sendMessageReq.getChatId());

        Message message = new Message();
        message.setUser(user);
        message.setChat(chat);
        message.setContent(sendMessageReq.getContent());
        message.setTimeStamp(LocalDate.from(LocalDateTime.now()));
        return message;
    }

    @Override
    public List<Message> getChatsMessage(Integer chatId) throws ChatException {
        List<Message> messages = messageRepository.findByChatId(chatId);
        return messages;
    }

    @Override
    public Message getMessageById(Integer messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new MessageException("Message is not found with given id : "+messageId);
    }

    @Override
    public void deleteMessage(Integer messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);

        if(opt.isPresent()){
            Message message = opt.get();
            messageRepository.deleteById(message.getId());
        }
        throw new MessageException("Message is not found with given id : "+messageId);
    }
}
