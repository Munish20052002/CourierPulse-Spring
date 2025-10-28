package com.project.Springframework.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Please fill this field!")
	private String accountNumber;
	@NotBlank(message = "Please fill this field!")
	private String ifscCode;
	@NotBlank(message = "Please fill this field!")
	private String branchName;
	@NotBlank(message = "Please fill this field!")
	private String accountType;
	@NotBlank(message = "Please fill this field!")
	private String accountHolderName;
	@NotBlank(message = "Please fill this field!")
	private String bankName;
	
	@OneToOne
	private Employee employee;
	 
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Override
	public String toString() {
		return "Bank [id=" + id + ", accountNumber=" + accountNumber + ", ifscCode=" + ifscCode + ", branchName="
				+ branchName + ", accountType=" + accountType + ", accountHolderName=" + accountHolderName
				+ ", bankName=" + bankName + ", employee=" + employee + "]";
	}
	
	
	
	

}
