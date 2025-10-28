package com.project.Springframework.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Springframework.DeliveryStatus;
import com.project.Springframework.beans.Courier;
import com.project.Springframework.beans.CourierItem;
import com.project.Springframework.beans.District;
import com.project.Springframework.beans.State;
import com.project.Springframework.service.CountryService;
import com.project.Springframework.service.CourierCenterService;
import com.project.Springframework.service.CourierCustomerService;
import com.project.Springframework.service.CourierService;
import com.project.Springframework.service.DeliveryStatusService;
import com.project.Springframework.service.DistrictService;
import com.project.Springframework.service.StateService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/courier")
public class CourierController { 

	@Autowired
	CountryService countryService;

	@Autowired
	CourierService courierService;
	
	@Autowired
	CourierCustomerService courierCustomerService;
	
	@Autowired
	private StateService stateService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private CourierCenterService courierCenterService;
	
	@Autowired
	private DeliveryStatusService deliveryStatusService;
	
	/*
	 * @Autowired LoginService loginService;
	 */
	
	static boolean isSaved = false;
	static boolean isFirstTime = false;
	
	@GetMapping("/courierForm")
	public String showCourierForm(Model model) {

		model.addAttribute("countryList", countryService.findAllCountry());
		model.addAttribute("centers",courierCenterService.findAllCourierCenter());

		model.addAttribute("saveReceiver", true);
		model.addAttribute("dontUseWeights", false);
		
		if(isSaved) {
			model.addAttribute("positiveMessage", "Courier Booked Successfully !");
			isSaved = false;
		}

		Courier courier = new Courier();
		courier.setCourierItems(new ArrayList<CourierItem>());
		
		courier.getCourierItems().add(new CourierItem());
		model.addAttribute("courier", courier);

		return "CourierForm";
	}
	

	@PostMapping("/saveNewCourier")
	public String saveCurier(
			@Valid @ModelAttribute("courier") Courier courier,
			BindingResult bindingResult,
			@RequestParam("saveReceiver") String saveReceiver,
			@RequestParam(value = "dontUseWeights", defaultValue = "false") boolean dontUseWeights, 
			Model model , RedirectAttributes redirectAttributes) {
		
		
		 model.addAttribute("countryList",countryService.findAllCountry());
		 model.addAttribute("centers",courierCenterService.findAllCourierCenter());
		
		if(courier.getSenderDistrict().getId() == null) {
			bindingResult.rejectValue("senderDistrict.id", null, "Sender district is not Selected !");
			System.out.println("SenderDsistrict Error");
		}
		if(courier.getReceiverDistrict().getId() == null) {
			bindingResult.rejectValue("receiverDistrict.id", null, "Reciver district is not Selected !");
			System.out.println("ReceiverDistrict Error");
		}
		if (!dontUseWeights) {
			int a = 0;
			for(CourierItem cItem: courier.getCourierItems()) {
				
				if(cItem.getLength() == null) {
					bindingResult.rejectValue("courierItems["+ a +"].length", null, "Required Field!");
				}
				if(cItem.getWidth() == null) {
					bindingResult.rejectValue("courierItems["+ a +"].width", null, "Required Field!");
				}
				if(cItem.getHeight() == null) {
					bindingResult.rejectValue("courierItems["+ a +"].height", null, "Required Field!");
				}
				if(cItem.getWeight() == null) {
					bindingResult.rejectValue("courierItems["+ a +"].weight", null, "Required Field!");
				}
				
				a++;
			}
		}
		if(courier.getInitialCenter().getId()==null) {
			bindingResult.rejectValue("initialCenter.id", null, "Please fill initial center");
		}
		
		if(courier.getDestinationCenter().getId()==null) {
			bindingResult.rejectValue("destinationCenter.id", null, "Please fill destination center");
		}
		if(courier.getInitialCenter().getId()!=null && courier.getDestinationCenter().getId()!=null && courier.getInitialCenter().getId().equals(courier.getDestinationCenter().getId())) {
			bindingResult.rejectValue("destinationCenter.id", null, "it can't be same as initial center");
		}
		

		 if (bindingResult.hasErrors()) {
			     
			     for (FieldError error : bindingResult.getFieldErrors()) {
			            String fieldName = error.getField();
			            String errorMessage = error.getDefaultMessage();
			            System.out.println("Validation error for field '" + fieldName + "': " + errorMessage);
			        }
 
				
				 model.addAttribute("saveReceiver", saveReceiver);
				 model.addAttribute("dontUseWeights", dontUseWeights);
				 model.addAttribute("negativeMessage", "Please Fill Required Fields !");
				 
				 //sender
				 if(courier.getSenderDistrict().getState().getCountry().getId() != null) {
					 List<State> senderStates = stateService.findStateByCountryId(courier.getSenderDistrict().getState().getCountry().getId());
					 model.addAttribute("senderStates", senderStates);
					 
					 if(courier.getSenderDistrict().getState().getId() != null) {
						 List<District> senderDistricts = districtService.findAllDistrictsByStateId(courier.getSenderDistrict().getState().getId());
						 model.addAttribute("senderDistricts", senderDistricts);
					 }
				 }
				 //Receiver
				 if(courier.getReceiverDistrict().getState().getCountry().getId() != null) {
					 List<State> receiverStates = stateService.findStateByCountryId(courier.getSenderDistrict().getState().getCountry().getId());
					 model.addAttribute("receiverStates", receiverStates);
					 
					 if(courier.getReceiverDistrict().getState().getId() != null) {
						 List<District> receiverDistricts = districtService.findAllDistrictsByStateId(courier.getSenderDistrict().getState().getId());
						 model.addAttribute("receiverDistricts", receiverDistricts);
					 }
				 }
				 
				 return "CourierForm";
		  }


		
		if (dontUseWeights) {
			courier.setCourierItems(null);
		} else {

			for (CourierItem courierItem : courier.getCourierItems()) {
				courierItem.setCourier(courier);
			}
		}

		//System.out.println("in Controller Save Receiver: " + saveReceiver);

		courierService.save(courier, Boolean.parseBoolean(saveReceiver));
		isSaved = true;

		if (courier.getId()==null) {
			redirectAttributes.addFlashAttribute("positiveMessage", "Courier Successfully Placed!");
		}else {
			redirectAttributes.addFlashAttribute("positiveMessage", "Courier Successfully Updated!");

		}
		return "redirect:courierForm";
	}

	
	
	@GetMapping("/update-form")
	public String updateCourierForm(@RequestParam("courierId") Integer courierId, Model model) {

		

		String saveReceiver = "true";
		model.addAttribute("saveReceiver", saveReceiver);
		// model.addAttribute("dontUseWeights", false);

		Courier courier = courierService.getCourierById(courierId);
		model.addAttribute("courier", courier);
		
		model.addAttribute("countryList", countryService.findAllCountry());
		
		List<State> senderStates = stateService.findStateByCountryId(courier.getSenderDistrict().getState().getCountry().getId());
		List<State> receiverStates = stateService.findStateByCountryId(courier.getReceiverDistrict().getState().getCountry().getId());
		
		model.addAttribute("senderStates", senderStates);
		model.addAttribute("receiverStates", receiverStates);
		
		List<District> senderDistricts = districtService.findAllDistrictsByStateId(courier.getSenderDistrict().getState().getId());
		List<District> receiverDistricts = districtService.findAllDistrictsByStateId(courier.getReceiverDistrict().getState().getId());
		model.addAttribute("senderDistricts", senderDistricts);
		model.addAttribute("receiverDistricts", receiverDistricts);
		
		model.addAttribute("centers",courierCenterService.findAllCourierCenter());

		return "CourierForm";
	}


	
	@GetMapping("/view-courier")
	public String courierManagement(Model model) {
		model.addAttribute("couriers", courierService.getAllCouriers());
		return "view_courier";
	}
	
	
	
	@GetMapping("/searchCourier")
	public String searchCourier(
			Model model,
			@Valid @RequestParam(value = "clientName", required = false) String clientName,
			@RequestParam(value = "bookingCode", required = false) String bookingCode,
			@RequestParam(value = "trackingCode", required = false) String trackingCode,
			@RequestParam(value = "bookingDate", required = false) LocalDate bookingDate,
			@RequestParam(value = "courierStatus", required = false) String courierStatus,
			@RequestParam(value = "startDate", required = false) LocalDate startDate, 
			@RequestParam(value = "endDate", required = false) LocalDate endDate){
		
		
		List<Courier> couriers = null;
		String field = "";

		if(clientName!=null && !clientName.isBlank()) {
			couriers = courierService.getCourierByCustomerName(clientName);
			field = clientName;
		}else if(bookingCode!=null && !bookingCode.isBlank()) {
			couriers = courierService.getCourierByBookingCode(bookingCode);
			field = bookingCode;
		}else if(trackingCode!=null && !trackingCode.isBlank()) {
			couriers = courierService.getCourierByTrackingCode(trackingCode);
			field = trackingCode;
		}else if(bookingDate!=null) {
			couriers = courierService.getCourierByDate(bookingDate);
			field = bookingDate.toString();
		}else if(courierStatus!=null) {
			couriers = courierService.getCourierByCourierStatus(courierStatus);
			field = courierStatus;
		}else if(startDate != null && endDate != null){
			couriers = courierService.getCourierByDateBetween(startDate, endDate);
			field = startDate.toString() +", "+ endDate.toString();
		}else {
			couriers = courierService.getAllCouriers(); 
		}
		
		
		
		if (!field.isBlank()) {
			if (couriers.isEmpty() || couriers == null) {
				model.addAttribute("positiveMessage", "No record found for ' " + field + " ' !");
			} else {
				// System.out.println(couriers);
				model.addAttribute("positiveMessage", couriers.size() + " record(s) matches '" + field + "'");
			}
		}

		//sending the required result
		model.addAttribute("couriers", couriers);

		return "view_courier";
	}



	@GetMapping("/seeParticular/{id}")
	public String getCourierById(@PathVariable Integer id, Model model) {

		model.addAttribute("courier", courierService.getCourierById(id));
		return "view_courier_details";
	}

	@GetMapping("/deleteParticular/{id}")
	public String deleteCourierById(@PathVariable Integer id, Model model) {
		courierService.deleteCourierById(id);
		return "redirect:/courier/view-courier";
	}
	
	
	
	@GetMapping("/trackCourier")
	public String trackCourier(@RequestParam("courierId") Integer courierId, Model model) {
		
		
		LinkedList<DeliveryStatus> deliveryStatuses = deliveryStatusService.findDeliveryStatusByCourierId(courierId);
		model.addAttribute("deliveryStatuses", deliveryStatuses);
		
		return "trackingCourier";
	}
	

	


}
