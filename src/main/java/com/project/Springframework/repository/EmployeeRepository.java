package com.project.Springframework.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public List<Employee> findByBankId(Long bankid);

	@Query("FROM  Employee e WHERE e.firstName LIKE %:name%")
	public List<Employee> findByFirstNameLike(String name); 
	
	public List<Employee> findByFirstNameContains(String name); 
	
	public List<Employee> findByContactContains(String contact); 

	public List<Employee> findByDob(LocalDate dob);

	public List<Employee> findByDesignationDesignationName(String designation);

	public List<Employee> findByStatus(String status);
	
	public List<Employee> findByDateOfJoiningBetween(LocalDate startDate, LocalDate endDate);

	public List<Employee> findByDateOfJoining(LocalDate dateOfJoining);
	
	

	

}
