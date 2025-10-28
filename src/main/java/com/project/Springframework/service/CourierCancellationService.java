package com.project.Springframework.service;

import org.springframework.stereotype.Service;

import com.project.Springframework.beans.CourierCancellation;
import com.project.Springframework.repository.CourierCancellationRepository;


@Service
public class CourierCancellationService {
	private CourierCancellationRepository courierCancellationRepository;
	
	public CourierCancellationService(CourierCancellationRepository courierCancellationRepository) {
		this.courierCancellationRepository = courierCancellationRepository;
	}
	
	public void addCourierCancellation(CourierCancellation courierCancellation) {
		courierCancellation.getCourier().setCourierStatus("Cancelled");
		courierCancellationRepository.save(courierCancellation);
	}
	
	public void deleteCourierCancellationById(Integer id) {
		courierCancellationRepository.deleteById(id);

	}
	
	
	
}
