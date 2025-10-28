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

import com.project.Springframework.beans.CourierCenter;
import com.project.Springframework.service.CourierCenterService;

import jakarta.validation.Valid;

@Controller
public class CourierCenterController {

	@Autowired
	private CourierCenterService courierCenterService;
	
	@GetMapping("/courierTracking")
	public String courierTracking(Model model) {
		
		CourierCenter CourierCenter = new CourierCenter();
		
		model.addAttribute("CourierCenter", CourierCenter);
		
		List<CourierCenter> CourierCenterList = courierCenterService.findAllCourierCenter();
		model.addAttribute("CourierCenterList", CourierCenterList);
		
		return "CourierCenter";
	}
	@PostMapping("/saveCourierCenter")
	public String saveCourierCenter(@Valid @ModelAttribute("CourierCenter") CourierCenter CourierCenter, BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute ){
		
		System.out.println(CourierCenter.getId());
		System.out.println(CourierCenter.getCenterName());
		
		if(bindingResult.hasErrors()) {
			List<CourierCenter> CourierCenterList = courierCenterService.findAllCourierCenter();
			model.addAttribute("CourierCenterList", CourierCenterList);
			
			return "CourierCenter";
		}
		
		courierCenterService.saveCourierCenter(CourierCenter);
		redirectAttribute.addFlashAttribute("successMessage", "CourierCenter saved successfully");
	
		return "redirect:/courierTracking";
	}
	
	@GetMapping("/updateCourierCenter")
	public String updateCourierCenter(@RequestParam("id") Integer id, Model model) {
		
		CourierCenter CourierCenter = courierCenterService.findCourierCenterById(id);
		
		model.addAttribute("CourierCenter", CourierCenter);
		List<CourierCenter> CourierCenterList = courierCenterService.findAllCourierCenter();
		model.addAttribute("CourierCenterList", CourierCenterList);
		
		return "CourierCenter";
	}
	@GetMapping("/deleteCourierCenter")
	public String deleteCourierCenter(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttribute) {
		
		
		courierCenterService.deleteCourierCenterById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "CourierCenter has been deleted!");
		
		return "redirect:/courierTracking";
	}

}
