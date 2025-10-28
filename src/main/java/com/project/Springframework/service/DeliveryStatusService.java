package com.project.Springframework.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.DeliveryStatus;
import com.project.Springframework.beans.Courier;
import com.project.Springframework.repository.DeliveryStatusRepository;

@Service
public class DeliveryStatusService {

	@Autowired
	private DeliveryStatusRepository deliveryStatusRepository;
	@Autowired
	private CourierService courierService;

	public void saveDeliveryStatus(DeliveryStatus deliveryStatus) {
		deliveryStatus.setLocalDateTime(LocalDateTime.now());
		
		Courier courier = courierService.getCourierById(deliveryStatus.getCourier().getId());
		
		if(deliveryStatus.getCourierCenter().getId().equals(courier.getDestinationCenter().getId())) {
			courier.setDelivered(true);
			courierService.save(courier);
		}
		
		
		//save status
		deliveryStatusRepository.save(deliveryStatus);
	}

	public DeliveryStatus findDeliveryStatusById(Integer id) {
		return deliveryStatusRepository.findById(id).orElseThrow();

	}

	public List<DeliveryStatus> findAllDeliveryStatus() {
		return deliveryStatusRepository.findAll();
	}

	public LinkedList<DeliveryStatus> findDeliveryStatusByCourierId(Integer courierId) {

		return deliveryStatusRepository.findByCourierIdOrderByLocalDateTime(courierId);
	}

}
