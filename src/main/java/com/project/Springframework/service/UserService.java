package com.project.Springframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Address;
import com.project.Springframework.beans.User;
import com.project.Springframework.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void saveUser(User user) {
		
//		for(Address address : user.getAddresses()) {
//			addressService.saveAddress()
//		}
		
		for(Address address : user.getAddresses()) {
			address.setUser(user);
		}
		
		userRepository.save(user);
	}
	
	
	private User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}
	
	
}
