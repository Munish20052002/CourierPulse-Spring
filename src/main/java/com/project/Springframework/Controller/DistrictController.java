package com.project.Springframework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.District;
import com.project.Springframework.service.CountryService;
import com.project.Springframework.service.DistrictService;
import com.project.Springframework.service.StateService;

import jakarta.validation.Valid;

@Controller
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	@Autowired
	private StateService stateService;
	@Autowired
	private CountryService countryService;

	@GetMapping("/districtForm")
	public String DistrictForm(Model model) {

		System.out.println("in dist form");
		District district = new District();
		model.addAttribute("district", district);

		List<District> allDistrict = districtService.findAllDistrict();
		model.addAttribute("allDistrict", allDistrict);

		model.addAttribute("State", stateService.findAllState());

		model.addAttribute("countries", countryService.findAllCountry());

		return "District";
	}

	@PostMapping("/saveDistrict")
	public String saveDistrict(@Valid @ModelAttribute("district") District district, BindingResult bindingResult,Model model, RedirectAttributes redirectAttribute) {

		System.out.println("I am save the District");
		System.out.println("District name: " + district.getDistrictName());

		
		
		if(district.getState().getId()==null) {
			bindingResult.rejectValue("state.id", null, "Hey! You have to choose state first!");
		}
		if(district.getState().getCountry().getId()==null) {
			bindingResult.rejectValue("state.country.id", null, "Hey! You have to choose Country first!");
		}
		
		if(bindingResult.hasErrors()) {
			
			List<District> allDistrict = districtService.findAllDistrict();
			model.addAttribute("allDistrict", allDistrict);
			
			model.addAttribute("state",stateService.findAllState());
			
			model.addAttribute("countries",countryService.findAllCountry());
			
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			for(FieldError error : fieldErrors) {
				System.out.println("error field: "+ error.getField());
			}
			
			return "District";
		}
		
		districtService.saveDistrict(district);
		redirectAttribute.addFlashAttribute("successMessage", "District saved successfully");

		return "redirect:/districtForm";
	}

	@GetMapping("/editDistrict")
	public String editDistrict(@RequestParam("id") Integer id, Model model) {
		District district = districtService.findDistrictById(id);
		model.addAttribute("district", district);

		List<District> allDistrict = districtService.findAllDistrict();
		model.addAttribute("allDistrict", allDistrict);

		model.addAttribute("states", stateService.findAllState());

		model.addAttribute("countries", countryService.findAllCountry());

		return "District";

	}

	@GetMapping("/deleteDistrict")
	public String deleteDistrict(@RequestParam("id") Integer id,  RedirectAttributes redirectAttribute) {
		districtService.deleteDistrictById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "State has been deleted!");

		return "redirect:/districtForm";
		

	}

	@GetMapping("/getDistrictsByStateId")
	public String getDistrictsByStateId(@RequestParam("stateId") Integer stateId, Model model) {
			List<District> districts = districtService.findAllDistrictsByStateId(stateId);
			
			model.addAttribute("districts", districts);
			
			return "ajaxDistrict";
	}

}
