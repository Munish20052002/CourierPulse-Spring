package com.project.Springframework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.Country;
import com.project.Springframework.service.CountryService;

import jakarta.validation.Valid;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/countryForm")
	public String countryForm(Model model) {
		
		Country country = new Country();
		
		model.addAttribute("country", country);
		
		List<Country> countryList = countryService.findAllCountry();
		model.addAttribute("countryList", countryList);
		
		return "Country";
	}
	
	@PostMapping("/saveCountry")
	public String saveCountry(@Valid @ModelAttribute("country") Country country, BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute ){
		
		System.out.println(country.getId());
		System.out.println(country.getCountryName());
		
		if(bindingResult.hasErrors()) {
			List<Country> countryList = countryService.findAllCountry();
			model.addAttribute("countryList", countryList);
			
			return "Country";
		}
		
		countryService.saveCountry(country);
		redirectAttribute.addFlashAttribute("successMessage", "Country saved successfully");
	
		return "redirect:/countryForm";
	}
	
	
	@GetMapping("/updateCountry")
	public String updateCountry(@RequestParam("id") Integer id, Model model) {
		
		Country country = countryService.findCountryById(id);
		
		model.addAttribute("country", country);
		List<Country> countryList = countryService.findAllCountry();
		model.addAttribute("countryList", countryList);
		
		return "Country";
	}
	@GetMapping("/deleteCountry")
	public String deleteCountry(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttribute) {
		
		
		countryService.deleteCountryById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "Country has been deleted!");
		
		return "redirect:/countryForm";
	}
	

}
