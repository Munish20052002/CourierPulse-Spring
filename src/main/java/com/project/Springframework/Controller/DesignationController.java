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

import com.project.Springframework.beans.Designation;
import com.project.Springframework.service.DepartmentService;
import com.project.Springframework.service.DesignationService;

import jakarta.validation.Valid;


@Controller
public class DesignationController {
	
	@Autowired
	private DesignationService designationService;
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/designationForm")
	public String DesignationForm(Model model) {
		Designation designation = new Designation();
		model.addAttribute("designation", designation);
		
		List<Designation> alldesignation = designationService.findAlldesignation();
		model.addAttribute("alldesignation", alldesignation);
		
		model.addAttribute("department", departmentService.findAllDepartments());
		
		
		return "Designation";
		
	}
	@PostMapping("/savedesignation")
	public String savedesignation(@Valid @ModelAttribute("designation") Designation designation, BindingResult bindingResult, Model model,  RedirectAttributes redirectAttribute ) {
		
       System.out.println("I am in save designation");
       System.out.println("designation name: "+ designation.getDesignationName());
       
       System.out.println("designation.getdepartment().getId(): "+ designation.getDepartment().getId());
       System.out.println("designation.getdepartment().getdepartmentName(): "+ designation.getDepartment().getDepartmentName());
       if(designation.getDepartment().getId() == null) {
    	   bindingResult.rejectValue("department.id", null, "Hey! You have to choose department first!");
       }
       
       if(bindingResult.hasErrors()) {
    	   List<Designation> alldesignation = designationService.findAlldesignation();
   		model.addAttribute("alldesignation", alldesignation);
   		
   		model.addAttribute("department", departmentService.findAllDepartments());
   		
   		
   		return "designation";
       }
       designationService.savedesignation(designation);
       redirectAttribute.addFlashAttribute("successMessage", "Designation saved successfully");
       
		return "redirect:/designationForm";


	}
	@GetMapping("/editdesignation")
	public String editdesignation(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttribute) {
		Designation designation = designationService.finddesignationById(id);
		model.addAttribute("designation", designation);
		
		List<Designation> alldesignation = designationService.findAlldesignation();
		model.addAttribute("alldesignation", alldesignation);
		
		model.addAttribute("countries", departmentService.findAllDepartments());
		
		return "designation";
		
	}
	@GetMapping("/deletedesignation")
	public String deletedesignation(@RequestParam("id") Integer id, RedirectAttributes redirectAttribute) {
		designationService.deletedesignationById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "designation has been deleted!");
		return "redirect:/designationForm";
		
	}
}
