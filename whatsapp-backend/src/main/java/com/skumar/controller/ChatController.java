package com.skumar.controller;

import com.skumar.entity.Chat;
import com.skumar.entity.Users;
import com.skumar.request.GroupChatReq;
import com.skumar.request.SinglechatRequest;
import com.skumar.service.ChatService;
import com.skumar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/whatsapp/chat")
public class ChatController {

    private ChatService chatService;
    private UserService userService;

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SinglechatRequest singlechatRequest, Authentication auth){
        Users user = userService.findUserProfile(auth);

        Chat chat = chatService.createChat(user,singlechatRequest.getUserId());

        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatReq req, Authentication auth){
        Users user = userService.findUserProfile(auth);

        Chat group = chatService.createGroup(req,user);

        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }
    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable("chatId") Integer chatId){

        Chat chat = chatService.findChatById(chatId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }


    @GetMapping("/chats")
    public ResponseEntity<List<Chat>> findAllChatByUserId(Authentication auth){
        Users user = userService.findUserProfile(auth);

        List<Chat> chats = chatService.findAllChatByUserEmail(user.getEmail());

        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable("chatId") Integer chatId,@PathVariable("userId") Integer userId, Authentication auth){
        Users user = userService.findUserProfile(auth);
        Users firstUser = userService.findUserById(userId);

        Chat group = chatService.addUserToGroup(firstUser.getEmail(),chatId,user);

        return new ResponseEntity<>(group, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable("chatId") Integer chatId,@PathVariable("userId") Integer userId, Authentication auth){
        Users user = userService.findUserProfile(auth);
        Users firstUser = userService.findUserById(userId);

        Chat group = chatService.removeFromGroup(chatId,firstUser.getEmail(),user);

        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<String> deleteChatHandler(@PathVariable("chatId") Integer chatId, Authentication auth){
        Users user = userService.findUserProfile(auth);

        chatService.deleteChat(chatId,user.getEmail());

        return new ResponseEntity<>("Chat is deleted successfully", HttpStatus.OK);
    }


}
