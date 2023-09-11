package com.skumar.controller;

import com.skumar.entity.Message;
import com.skumar.entity.Users;
import com.skumar.request.SendMessageReq;
import com.skumar.service.ChatService;
import com.skumar.service.MessageService;
import com.skumar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/whatsapp/message")
public class MessageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessageHanlder(@RequestBody SendMessageReq req, Authentication auth){
        Users user = userService.findUserProfile(auth);
        req.setUserId(user.getUserId());
        Message message = messageService.sendMessage(req);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatsMessageHanlder(@PathVariable("chatId") Integer chatId){
        List<Message> messages = messageService.getChatsMessage(chatId);
        return new ResponseEntity<>(messages, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageByIdHanlder(@PathVariable("messageId") Integer messageId){
        Message message = messageService.getMessageById(messageId);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessageByIdHanlder(@PathVariable("messageId") Integer messageId){
        messageService.deleteMessage(messageId);
        return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
    }




}
