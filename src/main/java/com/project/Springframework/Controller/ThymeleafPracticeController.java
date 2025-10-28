package com.project.Springframework.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafPracticeController {
	
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		
		return "index";
	}
	
	
	@GetMapping("/print")
	public String printName(Model model) {
		
		model.addAttribute("name", "Rohit"); 
		
		return "index";
	}
	
	

}
