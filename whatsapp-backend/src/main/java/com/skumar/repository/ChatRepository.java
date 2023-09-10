package com.skumar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skumar.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{

}
