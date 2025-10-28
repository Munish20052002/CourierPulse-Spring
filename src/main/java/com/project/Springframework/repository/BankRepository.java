package com.project.Springframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.Bank;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

}
