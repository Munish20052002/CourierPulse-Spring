package com.project.Springframework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Springframework.beans.State;


@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
	
//	@Query("from state where country.id =:id")
	public List<State> findByCountryId(Integer countryId);
	

}
