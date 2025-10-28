package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.District;
import com.project.Springframework.repository.DistrictRepository;

@Service
public class DistrictService {
	
	@Autowired 
	private DistrictRepository districtRepository;
	
	public void saveDistrict (District district) {
		districtRepository.save(district);
	}
	
	public District findDistrictById(Integer id) {
		return districtRepository.findById(id).orElseThrow();
	}
	public void deleteDistrictById(Integer id) {
		districtRepository.deleteById(id);
	}

	public List<District> findAllDistrict() {
		return districtRepository.findAll();
	}

	public List<District> findAllDistrictsByStateId(Integer stateId) {

		return districtRepository.findByStateId(stateId);
	}

}
