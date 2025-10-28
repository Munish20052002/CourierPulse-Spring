package com.project.Springframework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.Address;
import com.project.Springframework.beans.Country;
import com.project.Springframework.beans.District;
import com.project.Springframework.beans.User;
import com.project.Springframework.service.CountryService;
import com.project.Springframework.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CountryService countryService;
	
	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		
		User user = new User();
		user.getAddresses().add(new Address());
		
		model.addAttribute("user", user);
		
		List<Country> countries = countryService.findAllCountry();
		model.addAttribute("countries",countries);
		
		return "userForm";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,RedirectAttributes redirectAttribute) {
		
		
		
		System.out.println("user: "+ user);
		System.out.println("I am save an user");
		System.out.println("user name: " + user.getName());
		System.out.println("user contact: " + user.getContact());
		
		for(int i = 0; i<user.getAddresses().size(); i++) {
			if(user.getAddresses().get(i)
					.getDistrict().getState()
					.getCountry().getId() == null) {
				bindingResult.rejectValue("addresses["+ i +"].district.state.country.id", null, "hey! You have to choose country first");
			
				
			}
			if(user.getAddresses().get(i)
					.getDistrict().getState().getId()==null) {
				bindingResult.rejectValue("addresses["+ i +"].district.state.id", null, "Hey! You have to choose state first");
			}
			if(user.getAddresses().get(i)
					.getDistrict().getId()==null) {
				bindingResult.rejectValue("addresses["+ i +"].district.id", null, "Hey! You have to choose district first");
			}
			
		}
		
		
		if(bindingResult.hasErrors()) {
			
			List<Country> countries = countryService.findAllCountry();
			model.addAttribute("countries",countries);
			
			return "userForm";
		}
		
		
		userService.saveUser(user);
	    redirectAttribute.addFlashAttribute("successMessage", "User saved successfully");

		return "redirect:/userForm";
	}

	

}
