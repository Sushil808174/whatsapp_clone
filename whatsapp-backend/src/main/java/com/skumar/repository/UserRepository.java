package com.skumar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skumar.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	public Optional<Users> findByEmail(String emailId);
	
	
	@Query("SELECT u FROM Users u WHERE u.full_name LIKE %:query% OR u.email LIKE %:query%")
	public List<Users> searchUser(@Param("query") String query);

}
