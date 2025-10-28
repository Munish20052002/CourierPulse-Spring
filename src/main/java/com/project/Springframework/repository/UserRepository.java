package com.project.Springframework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findUserById(Integer districtId);

}
