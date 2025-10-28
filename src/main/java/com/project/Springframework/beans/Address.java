package com.project.Springframework.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Please fill this field!")
	private String addressLine1;
	@NotBlank(message = "Please fill this field!")
	private String addressLine2;
	@NotBlank(message = "Please fill this field!")
	private String pincode;
	@NotBlank(message = "Please fill this field!")
	private String city;
	@NotBlank(message = "Please fill this field!")
	private String landmark;
	@NotBlank(message = "Please fill this field!")
	private String addressType;
	
	
	

	@ManyToOne
	private District district;
	
	
	@ManyToOne
	private Employee employee;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", pincode="
				+ pincode + ", city=" + city + ", landmark=" + landmark + ", addressType=" + addressType + ", district="
				+ district + ", employee=" + employee + "]";
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}





	

}
