package com.project.Springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Springframework.beans.Country;
import com.project.Springframework.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	

	public void saveCountry(Country country) {
		countryRepository.save(country);
	}
	
	public Country findCountryById(Integer id) {
		return countryRepository.findById(id).orElseThrow();
	}
	public void deleteCountryById(Integer id) {
		countryRepository.deleteById(id);
	}

	public List<Country> findAllCountry() {
		return countryRepository.findAll();
	}
	
	
	
	
	

}
