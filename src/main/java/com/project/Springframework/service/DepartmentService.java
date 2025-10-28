package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Department;
import com.project.Springframework.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
	}
	
	public List<Department> findAllDepartments(){
		return departmentRepository.findAll();
	}

	public Department finddepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentRepository.findById(id).orElse(null);
	}
	
	public void deletedepartmentById(Integer id) {
		departmentRepository.deleteById(id);
	}

}
