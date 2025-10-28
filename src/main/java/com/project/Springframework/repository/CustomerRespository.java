package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Customer;

@Repository
public interface CustomerRespository extends  JpaRepository<Customer, Long>
{

}
 