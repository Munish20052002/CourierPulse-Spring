package com.project.Springframework.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Courier;
import com.project.Springframework.beans.CourierCustomer;
import com.project.Springframework.beans.CourierItem;
import com.project.Springframework.repository.CourierCustomerRepository;
import com.project.Springframework.repository.CourierRepository;

@Service
public class CourierService {
	@Autowired
	private CourierRepository courierRepository;
	private CourierCustomerRepository courierCustomerRepository;
	private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public CourierService(CourierRepository courierRepository, CourierCustomerRepository courierCustomerRepository) {
		this.courierRepository = courierRepository;
		this.courierCustomerRepository = courierCustomerRepository;
	}

	public List<Courier> getAllCouriers() {
		return courierRepository.findAll();
	}
	
	

    public static String generateRandomString() {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHANUMERIC_CHARS.length());
            char randomChar = ALPHANUMERIC_CHARS.charAt(index);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

	public void saveCourierCustomer(Courier courier, Boolean saveReceiver) {
		
		CourierCustomer courierCustomer =  courierCustomerRepository
									.findCourierCustomerByMobNo(courier.getSenderPhone());
		
//		System.out.println(courierCustomer);
//		System.out.println(saveReceiver);
		
		System.out.println(courierCustomerRepository.findCourierCustomerByMobNo(courier.getReceiverPhone()));
		
		if(courierCustomer!=null) {
			courier.setCustomer(courierCustomer);
		}else{
//			String name, String mobNo, String addressLine1, String addressLine2, String city,
//			String pincode, District district
			 courierCustomer = new CourierCustomer(
					courier.getSenderName(), 
					courier.getSenderPhone(),
					courier.getSenderAddressLine1(), 
					courier.getSenderAddressLine2(), 
					courier.getSenderCity(),
					courier.getSenderPincode(), 
					courier.getSenderDistrict());
			courierCustomerRepository.save(courierCustomer);
			courier.setCustomer(courierCustomer);
		}
		
		if (saveReceiver && courierCustomerRepository.findCourierCustomerByMobNo(courier.getReceiverPhone()) == null) {
			
			 courierCustomer = new CourierCustomer(
					courier.getReceiverName(), 
					courier.getReceiverPhone(),
					courier.getReceiverAddressLine1(), 
					courier.getReceiverAddressLine2(), 
					courier.getReceiverCity(),
					courier.getReceiverPincode(), 
					courier.getReceiverDistrict());
			courierCustomerRepository.save(courierCustomer);
		}

	}

	public void save(Courier courier, Boolean saveReceiver) {

		Integer maxRowCounter = courierRepository.getMaxRowCounter();
		Integer finalmaxRowCounter = maxRowCounter == null ? 1 : maxRowCounter + 1;
		courier.setBookingCode("COUR/" + String.format("%06d", finalmaxRowCounter));
		courier.setRowCounter(finalmaxRowCounter);
		courier.setCourierStatus("Booked");
		
		courier.setTrackingCode(generateRandomString()+(finalmaxRowCounter));
		
		

		saveCourierCustomer(courier, saveReceiver);

		courierRepository.save(courier);
	}
	
	public void save(Courier courier) {
		courierRepository.save(courier);
	}
	

	public void normalSave(Courier courier) {
		courierRepository.save(courier);
	}
	
	
	public Courier getCourierById(Integer id) {
		return courierRepository.findById(id).get();
	}

	public List<CourierItem> getCourierItemByCourierId(Integer id) {
		return getCourierById(id).getCourierItems();

	}

	public void deleteCourierById(Integer id) {
		courierRepository.deleteById(id);
	}
	
	public List<Courier> getCourierByCustomerName(String name) {
		return courierRepository.findAllBySenderName(name);
	}
	public List<Courier> getCourierByBookingCode(String bookingCode) {
		return courierRepository.findAllByBookingCode(bookingCode);
	}
	public List<Courier> getCourierByTrackingCode(String trackingCode) {
		return courierRepository.findAllByTrackingCode(trackingCode);
	}
	public List<Courier> getCourierByCourierStatus(String courierStatus) {
		return courierRepository.findAllByCourierStatus(courierStatus);
	}
	public List<Courier> getCourierByDate(LocalDate date) {
		return courierRepository.findAllByDate(date);
	}
	public List<Courier> getCourierByDateBetween(LocalDate startDate, LocalDate endDate) {
		return courierRepository.findAllByDateBetween(startDate, endDate);
	}
	


}
