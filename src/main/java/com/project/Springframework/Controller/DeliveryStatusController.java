package com.project.Springframework.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.DeliveryStatus;
import com.project.Springframework.beans.Courier;
import com.project.Springframework.service.CourierCenterService;
import com.project.Springframework.service.CourierService;
import com.project.Springframework.service.DeliveryStatusService;

import jakarta.validation.Valid;

@Controller
public class DeliveryStatusController {
	
	 @Autowired
	    public DeliveryStatusService deliveryStatusService;

	    @Autowired
	    private CourierCenterService centerService;

	    @Autowired
	    private CourierService courierService;
	    
	    @GetMapping("/changeDeliveryStatus")
	    public String deliveryStatus(@RequestParam("courierId") Integer courierId, Model model) {
	        Courier courier = courierService.getCourierById(courierId);

	        DeliveryStatus deliveryStatus = new DeliveryStatus();
	        deliveryStatus.setCourier(courier);

	        model.addAttribute("deliveryStatus", deliveryStatus);
	        model.addAttribute("courierCenters", centerService.findAllCourierCenter());

	        return "DeliveryStatusForm";
	    }
	    
	    @PostMapping("/changeDeliveryStatus")
	    public String saveDeliveryStatus(
	            @Valid @ModelAttribute("deliveryStatus") DeliveryStatus deliveryStatus,
	            BindingResult bindingResult,
	            Model model,
	            RedirectAttributes redirectAttributes) {

	        // Validate if Courier ID is null
	        if (deliveryStatus.getCourier() == null || deliveryStatus.getCourier().getId() == null) {
	            bindingResult.rejectValue("courier.id", null, "No Courier ID Found!");
	        }

	        // Validate if Courier Center ID is null
	        if (deliveryStatus.getCourierCenter() == null || deliveryStatus.getCourierCenter().getId() == null) {
	            bindingResult.rejectValue("courierCenter.id", null, "Please Choose a Courier Center!");
	        }

	        // If there are validation errors, return to the form
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("courierCenters", centerService.findAllCourierCenter());
	            return "DeliveryStatusForm";
	        }

	        // Save the delivery status
	        deliveryStatusService.saveDeliveryStatus(deliveryStatus);

	        // Add a success message and redirect to the search page
	        redirectAttributes.addFlashAttribute("positiveMessage", "Delivery Status has been successfully changed!");

	        return "redirect:/courier/searchCourier";
	    }

}
