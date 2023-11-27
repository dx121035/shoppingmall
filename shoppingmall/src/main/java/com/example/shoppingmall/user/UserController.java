package com.example.shoppingmall.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public String create(User user, MultipartFile file) throws IOException {
		
		userService.create(user, file);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/tel")
	public int tel(String tel) throws NoSuchAlgorithmException, IOException {
		
		int number = (int)(Math.random() * (90000)) + 100000;
		
		userService.sendSms(tel, number);
		
		return number;
	}
	
	@GetMapping("/readDetail")
	public String readDetail(Model model) {

		model.addAttribute("user", userService.readDetailUsername());
		
		return "user/readDetail";
	}
	
	@GetMapping("/update")
	public String update(Model model) {

		model.addAttribute("user", userService.readDetailUsername());
		
		return "user/update";
	}
	
	@PostMapping("/update")
	public String update(Model model, User user, MultipartFile file) throws IOException {
		
		userService.update(user, file);

		model.addAttribute("user", userService.readDetailUsername());

		return "user/readDetail";
	}
	
	@GetMapping("/delete")
	public String delete() {
		
		userService.delete();
		
		return "redirect:/";
	}

}
