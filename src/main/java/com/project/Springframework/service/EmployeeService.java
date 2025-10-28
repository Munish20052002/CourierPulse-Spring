package com.project.Springframework.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Address;
import com.project.Springframework.beans.Employee;
import com.project.Springframework.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	public void saveEmployee(Employee employee) {
		
		if(employee.getId()==null){
			employee.setDateOfJoining(LocalDate.now());
			employee.setStatus("Active");
		}
		
		
		employee.getBank().setEmployee(employee);
		
		for (Address address : employee.getAddresses()) {
			address.setEmployee(employee);
		}
		
		try {
			if(employee.getTempPhoto().getBytes().length>0) {
				employee.setProfilePic(employee.getTempPhoto().getBytes());
//				employee.getTempPhoto().getContentType()
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("in service: "+ employee.getAddresses());
		employeeRepository.save(employee);
	}
	
	public Employee findEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow();
	}
	
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public List<Employee> findAllEmployeeByBankId(Long bankid){
		return employeeRepository.findByBankId(bankid);
	}
	
	public List<Employee> findAllEmployeebyname(String name){
		List<Employee> employees = employeeRepository.findByFirstNameContains(name);
		return employees;
	}

	public List<Employee> findEmployeeByContact(String contact) {
		return employeeRepository.findByContactContains(contact);
	}
	
	public List<Employee> findEmployeeByDob(LocalDate dob){
		return employeeRepository.findByDob(dob);
		
	}
	
	public List<Employee> findEmployeeByDesignation(String designation){
		return employeeRepository.findByDesignationDesignationName(designation);
		
	}
	
	public List<Employee> findEmployeeByStatus(String status){
		return employeeRepository.findByStatus(status);
		
	}
	public List<Employee> findEmployeeByDateOfJoiningBetween(LocalDate startDate, LocalDate endDate){
		return employeeRepository.findByDateOfJoiningBetween(startDate, endDate);
		
	}
	
	public List<Employee> findEmployeeByDateOfJoining(LocalDate dateOfJoining){
		return employeeRepository.findByDateOfJoining(dateOfJoining);
		
	}

	public void viewEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
