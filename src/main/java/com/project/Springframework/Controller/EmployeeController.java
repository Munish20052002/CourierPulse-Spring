package com.project.Springframework.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.Address;
import com.project.Springframework.beans.Employee;
import com.project.Springframework.service.CountryService;
import com.project.Springframework.service.DepartmentService;
import com.project.Springframework.service.DesignationService;
import com.project.Springframework.service.DistrictService;
import com.project.Springframework.service.EmployeeService;
import com.project.Springframework.service.StateService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DesignationService designationService;

	@GetMapping("/employeeForm")
	public String EmployeeForm(Model model) {
		Employee employee = new Employee();
		employee.getAddresses().add(new Address());

		model.addAttribute("employee", employee);
		model.addAttribute("countries", countryService.findAllCountry());
		model.addAttribute("departments", departmentService.findAllDepartments());

		return "EmployeeForm";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,
			Model model , RedirectAttributes attributes) {

		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("countries", countryService.findAllCountry());


		if (employee.getDesignation().getDepartment().getId()==null) {
			bindingResult.rejectValue("designation.department.id", null , "Please select department first");
		}else {
			model.addAttribute("designations", designationService.finddesignationBydepartmentId(employee.getDesignation().getDepartment().getId()));

		}

		for (int i = 0; i < employee.getAddresses().size(); i++) {
			if (employee.getAddresses().get(i).getDistrict().getState().getCountry().getId() == null) {
				bindingResult.rejectValue("addresses[" + i + "].district.state.country.id", null,
						"hey! You have to choose country first");

			}else {
				model.addAttribute("states", stateService.findStateByCountryId(employee.getAddresses().get(i).getDistrict().getState().getCountry().getId()));

			}
			if (employee.getAddresses().get(i).getDistrict().getState().getId() == null) {
				bindingResult.rejectValue("addresses[" + i + "].district.state.id", null,
						"Hey! You have to choose state first");
			}else {
				model.addAttribute("districts", districtService.findAllDistrictsByStateId(employee.getAddresses().get(i).getDistrict().getState().getId()));

			}
			if (employee.getAddresses().get(i).getDistrict().getId() == null) {
				bindingResult.rejectValue("addresses[" + i + "].district.id", null,
						"Hey! You have to choose district first");
			}

		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("countries", countryService.findAllCountry());
			for (FieldError field : bindingResult.getFieldErrors()) {
				System.out.println("field: " + field.getField() + ", msg: " + field.getDefaultMessage());
			}

			return "EmployeeForm";
		}

		employeeService.saveEmployee(employee);
		if (employee.getId()==null) {
	        attributes.addFlashAttribute("positiveMessage", "Employee Successfully Added!");
		}else {
			attributes.addFlashAttribute("positiveMessage", "Employee Successfully Updated!");
			
		}
		return "redirect:/employeeForm";
	}

	 @GetMapping("/manageEmployee") 
	 public String manageEmployee(
			 @RequestParam(name="employeeName", required = false)String name, 
			 @RequestParam(name="contact", required = false)String contact, 
			 @RequestParam(name="dob", required = false) String dob,
			 @RequestParam(name="designation", required = false)String designation,
			 @RequestParam(name="status", required = false)String status,
			 @RequestParam(name="startDateOfJoining", required = false)String startDateOfJoining,
			 @RequestParam(name="endDateOfJoining", required = false)String endDateOfJoining,
			 @RequestParam(name="dateOfJoining", required = false) String dateOfJoining,
			 		Model model) {
	  
		 List<Employee> employees = null;
		 
	  if(name!= null && !name.isBlank()) {
		  employees = employeeService.findAllEmployeebyname(name);
	  }else if(contact!= null && !contact.isBlank()) {
		  employees = employeeService.findEmployeeByContact(contact);
	  }
	  else if(dob!= null && !dob.isBlank()) {
		  LocalDate dobLocalDate = LocalDate.parse(dob);
		  employees = employeeService.findEmployeeByDob(dobLocalDate);
		  }
	  else if(designation!= null && !designation.isBlank()) {
		  employees = employeeService.findEmployeeByDesignation(designation);
	  }
	  else if(status!= null && !status.isBlank()) {
		  employees = employeeService.findEmployeeByStatus(status);
	  }
	  else if(startDateOfJoining!=null && !startDateOfJoining.isBlank() && endDateOfJoining!= null && !endDateOfJoining.isBlank()) {
		  LocalDate startDateOfJoiningLocalDate = LocalDate.parse(startDateOfJoining);
		  LocalDate endDateOfJoiningLocalDate = LocalDate.parse(endDateOfJoining);
		  employees = employeeService.findEmployeeByDateOfJoiningBetween(startDateOfJoiningLocalDate, endDateOfJoiningLocalDate);
	  }
	  else if(dateOfJoining!= null && !dateOfJoining.isBlank()) {
		  LocalDate dateOfJoiningLocalDate = LocalDate.parse(dateOfJoining);
		  employees = employeeService.findEmployeeByDateOfJoining(dateOfJoiningLocalDate);
	  }
	  else {
		  employees = employeeService.findAllEmployees();
	  }
	  
	  
	  
	  model.addAttribute("employees", employees);
	  
	 return "manageEmployee"; 
	 }

     @GetMapping("/updateEmployee")
     public String updateEmployee(@RequestParam("id") Long id, Model model) {
    	 
    	 Employee employee= employeeService.findEmployeeById(id);
    	 
    	 model.addAttribute("employee", employee);
    	 model.addAttribute("countries", countryService.findAllCountry());
    	 
    	 model.addAttribute("states", stateService.findAllState());
    	 model.addAttribute("districts",districtService.findAllDistrict());
    	 
    	 model.addAttribute("departments", departmentService.findAllDepartments());
    	 model.addAttribute("designations", designationService.findAlldesignation());
    	 
    	 
    	 
		return "EmployeeForm";
    	 
     }
     
     
     @GetMapping("/photo/{id}") // -> /photo/1
     public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
         // Retrieve the photo as a byte array from the database
         byte[] photoData = employeeService.findEmployeeById(id).getProfilePic(); // Fetch photo based on ID or another identifier

         // Set headers for image content
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.IMAGE_PNG); // Set image type (change to PNG if needed)

         // Return the image data in the response entity
         return new ResponseEntity<>(photoData, headers, HttpStatus.OK);
     }
     
     @GetMapping("/viewEmployee")
     public String viewEmployee(@RequestParam("id") Long id,Model model) {
    	 Employee employee= employeeService.findEmployeeById(id);
    	 
    	 model.addAttribute("employee", employee);
   	  
		return "viewEmployee";
    	 
     }
}
