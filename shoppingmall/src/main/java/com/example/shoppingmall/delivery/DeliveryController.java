package com.example.shoppingmall.delivery;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/delivery")
@Controller
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("/payment")
	public String payment(String uid) {
		
		deliveryService.create(uid);
		
		return "redirect:/cart/readDetail";
	}
	
	@GetMapping("/readList")
	public String readList(Model model, Principal principal, @RequestParam(value="page", defaultValue="0") int page) {
		
		if (principal != null) {
		
		model.addAttribute("paging", deliveryService.readList(page));
		
		return "delivery/readList";
		}
		
		return "/login";
	}
	
	@GetMapping("/readDetail")
	public String readDetail(Model model, @RequestParam Integer id) {
		
		model.addAttribute("delivery", deliveryService.readDetail(id));
		
		return "delivery/readDetail";
	}

}
