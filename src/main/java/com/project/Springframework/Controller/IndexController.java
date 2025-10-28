package com.project.Springframework.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Springframework.beans.Courier;
import com.project.Springframework.service.CourierCustomerService;
import com.project.Springframework.service.CourierService;

@Controller
public class IndexController {
	
	@Autowired
	private CourierService courierService;
	
	@Autowired
	private CourierCustomerService courierCustomerService;
	
	@GetMapping("/dashboard")
	public String Dashboard(Model model) {
		List<Courier> couriers = courierService.getAllCouriers();
		 
		/* model.addAttribute("courierSize", couriers.size()); */
		 model.addAttribute("couriers", couriers);
		 
		Double totalCharges = 0.00;
		for (Courier courier : couriers) {
			totalCharges += courier.getCharges();
		}
		model.addAttribute("totalCharges", totalCharges);
		model.addAttribute("customers", courierCustomerService.getAllCourierCustomer());
		
		
		return "Dashboard";
	}
	
	

}
