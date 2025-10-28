package com.project.Springframework.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Courier;


@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {
	
	@Query("SELECT MAX(c.rowCounter) from Courier AS c")
	Integer getMaxRowCounter();
	
	List<Courier> findAllBySenderName(String senderName);
	
	List<Courier> findAllByBookingCode(String bookingCode);
	
	List<Courier> findAllByTrackingCode(String trackingCode);
	
	List<Courier> findAllByCourierStatus(String courierStatus);
	
	List<Courier> findAllByDate(LocalDate date);

	List<Courier> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
	

}
