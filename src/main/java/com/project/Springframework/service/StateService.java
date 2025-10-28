package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.Springframework.beans.State;
import com.project.Springframework.repository.StateRepository;


@Service
public class StateService {
	
	@Autowired
	private  StateRepository stateRepository ;

	public void saveState(State state) {
		stateRepository.save(state);
	}
	
	public State findStateById(Integer id) {
		return stateRepository.findById(id).orElseThrow();
	}
	public void deleteStateById(Integer id) {
		stateRepository.deleteById(id);
	}

	public List<State> findAllState() {
		return stateRepository.findAll();
	}
	
	public List<State> findStateByCountryId(Integer countryId) {
		return stateRepository.findByCountryId(countryId);
	}

	
}
