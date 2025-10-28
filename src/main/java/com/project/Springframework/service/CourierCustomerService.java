package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.CourierCustomer;
import com.project.Springframework.repository.CourierCustomerRepository;


@Service
public class CourierCustomerService {
	@Autowired
	CourierCustomerRepository courierCustomerRepository;
	
	public List<CourierCustomer> getAllCourierCustomer(){
		return courierCustomerRepository.findAll();
	}
	
	public void saveCourierCustomer(CourierCustomer courierCustomer) {
		courierCustomerRepository.save(courierCustomer);
	}
	
	public void deleteCourierCustomerById(Integer id) {
		courierCustomerRepository.deleteById(id);
	}
	
	public CourierCustomer getCourierById(Integer id) {
		return courierCustomerRepository.findById(id).get();
	}
}
