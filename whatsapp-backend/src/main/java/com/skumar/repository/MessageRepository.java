package com.skumar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skumar.entity.Message;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("Select m From Message m join m.chat c where c.chatId=:chatId")
    public List<Message> findByChatId(@Param("chatId") Integer chatId);
}
