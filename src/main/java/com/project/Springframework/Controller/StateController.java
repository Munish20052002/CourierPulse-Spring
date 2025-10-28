package com.project.Springframework.Controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.Designation;
import com.project.Springframework.beans.State;
import com.project.Springframework.service.CountryService;
import com.project.Springframework.service.DesignationService;
import com.project.Springframework.service.StateService;

import jakarta.validation.Valid;

@Controller
public class StateController {
	
	@Autowired
	private StateService stateService;
	@Autowired
	private CountryService countryService;
	
	
	@GetMapping("/stateForm")
	public String StateForm(Model model) {
		State state = new State();
		model.addAttribute("state", state);
		
		List<State> allState = stateService.findAllState();
		model.addAttribute("allState", allState);
		
		model.addAttribute("countries", countryService.findAllCountry());
		
		
		return "state";
	}
	
	@PostMapping("/saveState")
	public String saveState(@Valid @ModelAttribute("state") State state, BindingResult bindingResult, Model model,  RedirectAttributes redirectAttribute ) {
		
       System.out.println("I am in save state");
       System.out.println("State name: "+ state.getStateName());
       
       System.out.println("state.getCountry().getId(): "+ state.getCountry().getId());
       System.out.println("state.getCountry().getCountryName(): "+ state.getCountry().getCountryName());
       if(state.getCountry().getId() == null) {
    	   bindingResult.rejectValue("country.id", null, "Hey! You have to choose country first!");
       }
       
       if(bindingResult.hasErrors()) {
    	   List<State> allState = stateService.findAllState();
   		model.addAttribute("allState", allState);
   		
   		model.addAttribute("countries", countryService.findAllCountry());
   		
   		
   		return "state";
       }
       
       stateService.saveState(state);
       redirectAttribute.addFlashAttribute("successMessage", "State saved successfully");
       
		return "redirect:/stateForm";
	}
	
	@GetMapping("/editState")
	public String editState(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttribute) {
		State state = stateService.findStateById(id);
		model.addAttribute("state", state);
		
		List<State> allState = stateService.findAllState();
		model.addAttribute("allState", allState);
		
		model.addAttribute("countries", countryService.findAllCountry());
		
		return "state";
		
	}
	@GetMapping("/deleteState")
	public String deleteState(@RequestParam("id") Integer id, RedirectAttributes redirectAttribute) {
		stateService.deleteStateById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "State has been deleted!");
		return "redirect:/stateForm";
		
	}
	
	@GetMapping("/getStatesByCountryId")
	public String getStatesByCountryId(@RequestParam("countryId") Integer countryId, Model model) {
		
		List<State> states = stateService.findStateByCountryId(countryId);
		model.addAttribute("states", states);
		
		return "ajaxState";
	}
	
	@Autowired
	private DesignationService designationService;
	
	@GetMapping("/getDesignationByDepartementId")
	public String getDesignationByDepartementId(@RequestParam("departmentId") Integer departmentId, Model model) {
		
		List<Designation> designationList = designationService.finddesignationBydepartmentId(departmentId);
		model.addAttribute("designationList", designationList);
		return "desgAjax";
	}
	
	
	
			
		
	

}
