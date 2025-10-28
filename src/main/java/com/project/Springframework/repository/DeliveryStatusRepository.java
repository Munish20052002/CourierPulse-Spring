package com.project.Springframework.repository;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.DeliveryStatus;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Integer> {
	
	public LinkedList<DeliveryStatus> findByCourierIdOrderByLocalDateTime(Integer courierId); 

}
