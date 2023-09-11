package com.skumar.service.impl;

import com.skumar.entity.Chat;
import com.skumar.entity.Users;
import com.skumar.exception.ChatException;
import com.skumar.exception.UserException;
import com.skumar.repository.ChatRepository;
import com.skumar.repository.UserRepository;
import com.skumar.request.GroupChatReq;
import com.skumar.service.ChatService;
import com.skumar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Override
    public Chat createChat(Users users, Integer reqUser) throws UserException {
        Users user = userService.findUserById(reqUser);
        Chat isChatExist = chatRepository.findSingleChatByUserIds(users,user);
        if(isChatExist !=null){
            return isChatExist;
        }

        Chat chat = new Chat();
        chat.setCreatedBy(users);
        chat.getUsers().add(user);
        chat.getUsers().add(users);
        chat.setGroup(false);
        return chat;
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {

        Optional<Chat> chat = chatRepository.findById(chatId);
         if(chat.isPresent()){
             return chat.get();
         }
         throw new ChatException("Chat not found with chat id : "+chatId);
    }

    @Override
    public List<Chat> findAllChatByUserEmail(String email) throws UserException {
        Users user = userService.findUserByEmail(email);
        List<Chat> chats = chatRepository.findChatByUserId(user.getUserId());
        return chats;
    }

    @Override
    public Chat createGroup(GroupChatReq req, Users reqUser) throws UserException {
        Chat group = new Chat();
        group.setGroup(true);
        group.setChat_image(req.getChat_image());
        group.setChat_name(req.getChat_name());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);
        for(Integer reqIds:req.getUserId()){
            Users user = userService.findUserById(reqIds);
            group.getUsers().add(user);
        }
        return chatRepository.save(group);
    }

    @Override
    public Chat addUserToGroup(String userEmail, Integer chatId,Users reqUser) throws UserException, ChatException {
        Users user = userService.findUserByEmail(userEmail);
        Optional<Chat> opt = chatRepository.findById(chatId);
        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getAdmins().contains(reqUser)){
                chat.getUsers().add(user);
                return chatRepository.save(chat);
            }else {
                throw new UserException("You don't have access to add user to the group");
            }
        }
        throw new ChatException("chat not found with given chat id : "+chatId);
    }

    @Override
    public Chat renameGroup(Integer chatId, String groupName, Users reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getUsers().contains(reqUser)){
                chat.setChat_name(groupName);
                return chatRepository.save(chat);
            }
            throw new UserException("You are not a member of this group");
        }
        throw new ChatException("Chat not found with given chat id : "+chatId);
    }

    @Override
    public Chat removeFromGroup(Integer chatId, String userEmail, Users reqUser) throws UserException, ChatException {
        Users user = userService.findUserByEmail(userEmail);
        Optional<Chat> opt = chatRepository.findById(chatId);
        if(opt.isPresent()){
            Chat chat = opt.get();
            if(chat.getAdmins().contains(reqUser)){
                chat.getUsers().remove(user);
                return chatRepository.save(chat);
            }else if(chat.getUsers().contains(reqUser)) {
                if(user.getUserId().equals(reqUser.getUserId())){
                    chat.getUsers().remove(user);
                    return chatRepository.save(chat);
                }
            }
            throw new UserException("You don't have access to remove user from the group");

        }
        throw new ChatException("chat not found with given chat id : "+chatId);

    }

    @Override
    public void deleteChat(Integer chatId, String userEmail) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isPresent()){
            Chat chat = opt.get();
            chatRepository.deleteById(chat.getChatId());
        }
    }
}
