package com.skumar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skumar.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
