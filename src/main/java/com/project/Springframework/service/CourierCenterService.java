package com.project.Springframework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.Springframework.beans.CourierCenter;
import com.project.Springframework.repository.CourierCenterRepository;

@Service
public class CourierCenterService {
	
	private CourierCenterRepository centerRepository;
	

	public CourierCenterService(CourierCenterRepository centerRepository) {
		this.centerRepository = centerRepository;
	}
	
	
	public List<CourierCenter> findAllCourierCenter(){
		return centerRepository.findAll();
	}
	
	public void saveCourierCenter(CourierCenter courierCenter) {
		centerRepository.save(courierCenter);
	}
	
	public CourierCenter findCourierCenterById(Integer id) {
		return centerRepository.findById(id).orElseThrow();
	}
	
	public void deleteCourierCenterById(Integer id) {
		centerRepository.deleteById(id);
	}
	
	
	
	

}
