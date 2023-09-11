package com.skumar.repository;

import com.skumar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skumar.entity.Chat;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{

    @Query("SELECT c FROM Chat c JOIN c.users u where u.id = :userId")
    public List<Chat> findChatByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Chat c WHERE c.isGroup=false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findSingleChatByUserIds(@Param("user") Users user, @Param("reqUser") Users reqUser);

}
