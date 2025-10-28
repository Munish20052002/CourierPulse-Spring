package com.project.Springframework.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Please fill this field!")
	private String firstName;
	@NotBlank(message = "Please fill this field!")
	private String lastName;
	@NotBlank(message = "Please fill this field!")
	private String prefix;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "please enter dob") 
	private LocalDate dob;
	@NotBlank(message = "Please fill this field!")
	private String contact;
	@NotBlank(message = "Please fill this field!")
	private String alternateContact;
	@NotBlank(message = "Please fill this field!")
	private String email;
	@Lob
	@Column(name = "profile_pic", columnDefinition = "LONGBLOB")
	private byte[] profilePic;
	@NotBlank(message = "Please fill this field!")
	private String gender;
//	@NotBlank(message = "Please fill this field!")
//	private String department;
//	@NotBlank(message = "Please fill this field!")
//	private String designation;
	
	@ManyToOne
	private Designation designation;
		

	private String status; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfJoining;
	@NotBlank(message = "Please fill this field!")
	private String maritalStatus;
	@NotBlank(message = "Please fill this field!")
	private String senior;
	@NotNull(message = "Please fill this field!")
	private Double salary;
	@NotBlank(message = "Please fill this field!")
	private String experienceLevel;
	private Integer experience;
	
	
	@Transient
	private MultipartFile tempPhoto;
	

	
	@Valid
	@OneToOne(mappedBy = "employee"  , cascade = CascadeType.ALL)
	private Bank bank;
	
	@Valid
	@OneToMany(mappedBy = "employee"  , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Address> addresses =new ArrayList<Address>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Designation getDesignation() {
		return designation;
	}
	
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAlternateContact() {
		return alternateContact;
	}

	public void setAlternateContact(String alternateContact) {
		this.alternateContact = alternateContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSenior() {
		return senior;
	}

	public void setSenior(String senior) {
		this.senior = senior;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public MultipartFile getTempPhoto() {
		return tempPhoto;
	}

	public void setTempPhoto(MultipartFile tempPhoto) {
		this.tempPhoto = tempPhoto;
	}


	
}
