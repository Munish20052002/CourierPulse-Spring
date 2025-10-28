package com.project.Springframework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
	
	public List<District> findByStateId(Integer stateId);

}
