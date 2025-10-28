package com.project.Springframework;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.Springframework.beans.Courier;
import com.project.Springframework.beans.CourierCenter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private CourierCenter courierCenter;
	@ManyToOne
	private Courier courier;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime localDateTime;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CourierCenter getCourierCenter() {
		return courierCenter;
	}
	public void setCourierCenter(CourierCenter courierCenter) {
		this.courierCenter = courierCenter;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	

}
