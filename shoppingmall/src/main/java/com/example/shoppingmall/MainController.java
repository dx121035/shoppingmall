package com.example.shoppingmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shoppingmall.auth.UserDetailService;

@Controller
public class MainController {
	
	@Autowired
	private UserDetailService userDetailService;
	 
	@GetMapping("/")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@GetMapping("/login2")
	public String signup(Model model) {
		
		String signup = "signup";
		model.addAttribute("signup", signup);
		
		return "login";
	}
	
	@GetMapping("/naverlogin")
	public String naverlogin() {
		
		return "naverlogin";
	}
	
	@GetMapping("/loginCheck")
	public String loginCheck(String username) {
		
		int result = userDetailService.loginCheck(username);
		
		if (result == 1) {
			
			return "redirect:/";
			
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/loginCheck2")
	public String loginCheck2() {
		
		int result = userDetailService.loginCheck2();
		
		if (result == 1) {
			
			return "redirect:/";
			
		} else {
			return "redirect:/login";
		}
	}
	
}
