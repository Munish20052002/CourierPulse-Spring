package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>  {

	@Query("SELECT COUNT(u) > 0 FROM Users u WHERE u.email = :email AND u.password = :password")
	boolean existsByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	Users findByEmail(@Param("email") String email);

	
}
