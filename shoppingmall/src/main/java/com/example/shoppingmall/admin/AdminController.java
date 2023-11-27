package com.example.shoppingmall.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shoppingmall.delivery.Delivery;
import com.example.shoppingmall.delivery.DeliveryRepository;
import com.example.shoppingmall.delivery.DeliveryService;
import com.example.shoppingmall.status.Status;
import com.example.shoppingmall.status.StatusRepository;
import com.example.shoppingmall.user.UserService;


@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/main")
	public String main(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		List<Delivery> delivery = adminService.deliveryReadList();
		int totalSum = delivery.stream()
                .mapToInt(Delivery::getTotal)
                .sum();
		
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("users", adminService.userReadList());
		model.addAttribute("totalSum", totalSum);
		model.addAttribute("paging", adminService.deliveryReadList(page));
		
		return "admin/main";
	}
	
	@GetMapping("/userReadList")
	public String userReadList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("users", adminService.userReadList());
		model.addAttribute("paging", adminService.userReadList(page));
		
		return "admin/userReadList";
	}
	
	@GetMapping("/userReadDetail")
	public String userReadDetail(Model model, String username) {
		
		model.addAttribute("user", adminService.userReadDetail(username));
		
		return "admin/userReadDetail";
	}
	
	@PostMapping("/updateRole")
	@ResponseBody
	public void updateRole(String username, String newRole) {
		
		adminService.updateRole(username, newRole);
	}
	
	@GetMapping("/deliveryReadList")
	public String deliveryReadList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("paging", adminService.deliveryReadList(page));
		
		return "admin/deliveryReadList";
	}
	
	@GetMapping("/deliveryReadDetail")
	public String deliveryReadDetail(Model model, Integer id) {
		
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("delivery", deliveryService.readDetail(id));
		
		return "admin/deliveryReadDetail";
	}
	
	@PostMapping("/create")
	public String create(Integer id, String username, String sno, Integer step) {
		
		Status status = new Status();
		status.setUsername(username);
		status.setSno(sno);
		status.setStep(step);
		
		Optional<Delivery> od = deliveryRepository.findById(id);
		
		status.setCreateDate(LocalDateTime.now());
		status.setDelivery(od.get());
		statusRepository.save(status);
		
		return "redirect:/admin/deliveryReadDetail?id=" + id;
	}
	
}
