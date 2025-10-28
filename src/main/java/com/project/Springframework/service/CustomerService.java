package com.project.Springframework.service;

import com.project.Springframework.beans.Customer;

import com.project.Springframework.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	
	
	 @Autowired
	 private CustomerRespository customerRepository;

	 public List<Customer> getAllCustomers() {		 
	     return customerRepository.findAll();
	 }

	 public Customer getCustomerById(Long id) {
		 Optional<Customer> custommerOptional = customerRepository.findById(id);
	     return custommerOptional.orElseThrow();
	 }

	 public Customer createCustomer(Customer customer) {
		 
			return customerRepository.save(customer) 
					/*
			
			* if(custommer.getId() == null){
			* 	create a new record
			* }else{
				* 	if(custommer.getId() exist in database){
				* 
				*  update the record which id is customer.getId();
				* }else{
				* 	create a new record
				* }
			* }
			*/;
	 }

	 public void deleteCustomer(Long id) {
	    customerRepository.deleteById(id);
	 }
}   


