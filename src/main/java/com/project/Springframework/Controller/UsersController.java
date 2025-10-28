package com.project.Springframework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.beans.Users;
import com.project.Springframework.service.PasswordEncryptionDecryption;
import com.project.Springframework.service.UsersService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private HttpSession session;	
	@GetMapping("/loginForm")
	public String LoginForm(Model model) {
		Users users= new Users();
		
		model.addAttribute("users",users);
		
		return "Login";
	}
	
	@PostMapping("/saveUsers")
	public String saveUsers(Model model,Users users, RedirectAttributes redirectAttributes) {
		System.out.println("HI im here these are the terma :email"+users.getEmail() +" name   "+users.getName() +"  Pass"+users.getPassword() );
		
		Users existingUsers = usersService.getUserByEmail(users.getEmail());
		
		if(users.getName().isEmpty()) {
			redirectAttributes.addFlashAttribute("warning", "Please enter Name!");
			
		}else if(users.getEmail().isEmpty()) {
			redirectAttributes.addFlashAttribute("warning", "Please enter Email!");
			
		}else if(users.getPassword().isBlank()) {
			redirectAttributes.addFlashAttribute("warning", "Please enter Password!");
		}
		
		if(existingUsers!=null) {
			redirectAttributes.addFlashAttribute("warning", "You are already an existing user!");
		}else {
			
			//new user
			usersService.saveUsers(users);
		}
		
		return "redirect:/loginForm";
	}
	
	@PostMapping("/login")
	public String checkLogIn(Model model, Users requestUser, RedirectAttributes redirectAttributes) throws Exception {
		Users users = usersService.getUserByEmail(requestUser.getEmail());
		
		if(users == null) {
			System.out.println("Invalid Email!");
			redirectAttributes.addFlashAttribute("danger", "Invalid Email!");
		}else {
			
			if(requestUser.getPassword().equals(PasswordEncryptionDecryption.decrypt(users.getPassword()))) {
				System.out.println("Good to go!");
				redirectAttributes.addFlashAttribute("success", "Welcome "+ users.getName());
				session.setAttribute("users", users);
				
				return "redirect:/dashboard";
			}else {
				
				redirectAttributes.addFlashAttribute("danger", "Invalid Password!");
				System.out.println("Invalid Password!");
			}
		}
		
		
		return "redirect:/loginForm";
		
	}
	
	@GetMapping("/signout")
	public String signOut(RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("success", "Successfully signed out!");
		session.removeAttribute("users");
		return "redirect:/loginForm";
		
	}
	
	

}
