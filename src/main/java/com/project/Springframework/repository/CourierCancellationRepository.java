package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.CourierCancellation;

@Repository
public interface CourierCancellationRepository extends JpaRepository<CourierCancellation, Integer>{
	
}
