
package com.project.Springframework.Controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Springframework.beans.Customer;
import com.project.Springframework.service.CustomerService;

@Controller
@RequestMapping
public class CustomerController {
	
	@Autowired
	private CustomerService  customerService;
	
	@GetMapping("/saveUser")
	public String saveUser()	
	{
		Customer customer=new Customer();
		customer.setContact("1234567890");
		customer.setName("Rohit"); 
		
		customerService.createCustomer(customer);
		
		return "index";
	}
	

	
	@GetMapping("/customers")
	@ResponseBody
	public List<Customer> getAllCustomers(){
		
		return customerService.getAllCustomers();
	}
	
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		System.out.println("Hello WOrld!");
		return "index.html";
	}
	
}
