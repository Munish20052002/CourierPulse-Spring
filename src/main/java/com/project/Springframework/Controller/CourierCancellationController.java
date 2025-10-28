package com.project.Springframework.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Springframework.beans.CancellationReason;
import com.project.Springframework.beans.CourierCancellation;
import com.project.Springframework.service.CourierCancellationService;
import com.project.Springframework.service.CourierService;


@Controller
@RequestMapping("courier-cancellation")
public class CourierCancellationController {
	private CourierCancellationService courierCancellationService;
	private CourierService courierService;
	public CourierCancellationController(CourierCancellationService courierCancellationService, CourierService courierService) {
		this.courierCancellationService = courierCancellationService;
		this.courierService = courierService;
	}
	
	@GetMapping("{courierId}")
	public String initiateCancellation(Model model, @PathVariable Integer courierId) {
		
		System.out.println("Courier Id: "+courierId);
		CourierCancellation courierCancellation = new CourierCancellation();
		
		courierCancellation.setCourier(courierService.getCourierById(courierId));
		
		model.addAttribute("courierCancellation", courierCancellation);
		
		model.addAttribute("reasons", CancellationReason.values());
		
		
		
		return "courier-cancellation";
	}
	
	@PostMapping("/cancel")
	//@ResponseBody
	public String cancelCourierl(@ModelAttribute CourierCancellation courierCancellation, Model model) {
		
		courierCancellationService.addCourierCancellation(courierCancellation);
		
		
		
	
		model.addAttribute("positiveMessage", courierCancellation.getCourier().getSenderName()+"'s Courier has benn cancelled");
		//sending the required result
		model.addAttribute("couriers", courierService.getAllCouriers());

		return "view_courier";
		
	}
	
}
