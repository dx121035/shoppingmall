package com.example.shoppingmall.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.auth.UnauthorizedAccessException;
import com.example.shoppingmall.chatreply.ChatreplyService;
import com.example.shoppingmall.user.UserService;

@RequestMapping("/chat")
@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatreplyService chatreplyService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		model.addAttribute("name", username);
		
		return "chat/create";
	}
	
	
	@PostMapping("/create")
	public String create(Chat chat) {
		
		chatService.create(chat);
		
		return "redirect:/chat/readlist";
	}
	
//	@GetMapping("/readlist")
//	public String readlist(Model model) {
//		
//		model.addAttribute("notices", noticeService.readlist());
//		
//		return "notice/readlist";
//	}
//	
	@GetMapping("/readlist")
	public String readlist(Model model, 
			 @RequestParam(value="page", defaultValue="0") int page) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		if(!"admin@naver.com".equals(username)) {
			
			throw new UnauthorizedAccessException("관리자 전용 페이지 입니다.");
		}
		
		Page<Chat> paging = chatService.getList(page);
		model.addAttribute("paging", paging);
		
		return "chat/readlist";
	}
	
	
	
	
	@GetMapping("/readdetail")
	public String readdetail(Model model
							
							 ) {
		
		
		model.addAttribute("chat", chatService.readdetailusername());

		
		return "chat/readdetail";
	}
	
	@GetMapping("/adminreaddetail")
	public String adminreaddetail(Model model, 
							 @RequestParam Integer id) {
		
		
		
		model.addAttribute("chat", chatService.readdetail(id));
		
		return "chat/readdetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, 
						 @RequestParam Integer id) {

		model.addAttribute("chat", chatService.readdetail(id));

		return "chat/update";
	}
	
	
	@PostMapping("/update")
	public String update(Chat chat) {
		
		chatService.update(chat);
		
		return "redirect:/chat/readlist";
	}
	
	
	
	
	
	
}
