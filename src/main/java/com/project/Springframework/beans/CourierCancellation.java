package com.project.Springframework.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CourierCancellation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private CancellationReason cancellationReason;
	
	private String description;
	
	@OneToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;

	
	public CourierCancellation() {
		super();
	}


	public CourierCancellation(CancellationReason cancellationReason, String description, Courier courier) {
		super();
		this.cancellationReason = cancellationReason;
		this.description = description;
		this.courier = courier;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public CancellationReason getCancellationReason() {
		return cancellationReason;
	}


	public void setCancellationReason(CancellationReason cancellationReason) {
		this.cancellationReason = cancellationReason;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Courier getCourier() {
		return courier;
	}


	public void setCourier(Courier courier) {
		this.courier = courier;
	}


	@Override
	public String toString() {
		return "CourierCancellation [id=" + id + ", cancellationReason=" + cancellationReason + ", description=" + description + ", courier="
				+ courier + "]";
	}
	

	
}
