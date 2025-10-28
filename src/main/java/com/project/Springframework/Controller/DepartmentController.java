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

import com.project.Springframework.beans.Department;
import com.project.Springframework.service.DepartmentService;

import jakarta.validation.Valid;


@Controller
public class DepartmentController {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/departmentForm")
	public String departmentform(Model model) {
		
		Department department = new Department();
		
		model.addAttribute("department",department);
		
		List<Department> departmentList = departmentService.findAllDepartments();
		model.addAttribute("departmentList",departmentList);
		
		
		
		return "department";
		
	}
	
	@PostMapping("/savedepartment")
	public String savedepartment(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute ){
		
		System.out.println(department.getId());
		System.out.println(department.getDepartmentName());
		
		if(bindingResult.hasErrors()) {
			List<Department> departmentList = departmentService.findAllDepartments();
			model.addAttribute("departmentList", departmentList);
			
			return "department";
		}
		
		departmentService.saveDepartment(department);
		redirectAttribute.addFlashAttribute("successMessage", "department saved successfully");
	
		return "redirect:/departmentForm";
	}
	

	@GetMapping("/updatedepartment")
	public String updatedepartment(@RequestParam("id") Integer id, Model model) {
		
		Department department = departmentService.finddepartmentById(id);
		
		model.addAttribute("department", department);
		List<Department> departmentList = departmentService.findAllDepartments();
		model.addAttribute("departmentList", departmentList);
		
		return "department";
	}
	
	@GetMapping("/deletedepartment")
	public String deletedepartment(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttribute) {
		
		
		departmentService.deletedepartmentById(id);
		redirectAttribute.addFlashAttribute("dangerMessage", "department has been deleted!");
		
		return "redirect:/departmentForm";
	}

}
