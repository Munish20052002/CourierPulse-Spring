package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Designation;
import com.project.Springframework.repository.DesignationRepository;

@Service
public class DesignationService {
	
	@Autowired
	private  DesignationRepository designationRepository ;

	public void savedesignation(Designation designation) {
		designationRepository.save(designation);
	}
	
	public Designation finddesignationById(Integer id) {
		return designationRepository.findById(id).orElseThrow();
	}
	public void deletedesignationById(Integer id) {
		designationRepository.deleteById(id);
	}

	public List<Designation> findAlldesignation() {
		return designationRepository.findAll();
	}
	
	public List<Designation> finddesignationBydepartmentId(Integer departmentId) {
		return designationRepository.findBydepartmentId(departmentId);
	}



}
