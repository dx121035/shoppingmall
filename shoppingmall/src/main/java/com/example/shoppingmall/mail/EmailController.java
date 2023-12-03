package com.example.shoppingmall.mail;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
	@Autowired
	private MailService mailService;

	@RequestMapping("/sendMail")
	public String sendMail(@RequestParam String to, @RequestParam String content) {

		mailService.sendSimpleMessage(to, to + "님에게 채팅이 왔습니다", "작성하신 내용 :" + content);
		
		return "success";
	}
}
