package com.project.Springframework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import com.project.Springframework.beans.State;
import com.project.Springframework.beans.Student;
import com.project.Springframework.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public String StudentForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		List<Student> allStudent = studentService.findAllStudent();
		model.addAttribute("allStudent", allStudent);
		
		return "student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student ) {
		
		
        System.out.println("I am in save student");   
        studentService.saveStudent(student);
       
		return "redirect:/student";
	}

}
