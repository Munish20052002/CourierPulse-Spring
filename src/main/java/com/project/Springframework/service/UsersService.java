package com.project.Springframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Users;
import com.project.Springframework.repository.UsersRepository;


@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public void saveUsers(Users users) {
		
		try {
			users.setPassword(PasswordEncryptionDecryption.encrypt(users.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		usersRepository.save(users);
	}

	public boolean getUser(String email, String password) {
		
		if(usersRepository.existsByEmailAndPassword(email, password)) {
			return true;
		}
		return false;
	}
	public Users getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

}
