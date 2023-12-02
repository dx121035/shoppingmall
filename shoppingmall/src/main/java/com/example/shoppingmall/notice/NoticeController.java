package com.example.shoppingmall.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.cart.Cart;
import com.example.shoppingmall.cart.CartService;
import com.example.shoppingmall.item.ItemService;
import com.example.shoppingmall.user.User;
import com.example.shoppingmall.user.UserService;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/create")
	public String create() {
		return "notice/create";
	}
	
	@GetMapping("/create2")
	public String create2() {
		return "notice/create2";
	}
	
	
	@PostMapping("/create")
	public String create(Notice notice) {
		
		noticeService.create(notice);
		
		return "redirect:/notice/readList";
	}
	
	@GetMapping("/readList")
	public String readList(Model model, 
			 @RequestParam(value="page", defaultValue="0") int page) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		
		Page<Notice> paging = noticeService.getList(page);
		model.addAttribute("paging", paging);
		
		return "/notice/readList";
	}
		
	@GetMapping("/readDetail")
	public String readDetail(Model model, 
							 @RequestParam Integer id) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		
		Notice notice = noticeService.readDetail(id);
		User user = userService.readDetailUsername();
		noticeService.hit(notice, user);
		
		
		model.addAttribute("notice", noticeService.readDetail(id));
		
		return "notice/readDetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, 
						 @RequestParam Integer id) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));

		model.addAttribute("notice", noticeService.readDetail(id));

		return "notice/update";
	}
	
	
	@PostMapping("/update")
	public String update(Notice notice) {
		
		noticeService.update(notice);
		
		return "redirect:/notice/readList";
	}
	
	 @GetMapping("/delete")
	   public String delete(@RequestParam Integer id) {
	      noticeService.delete(id);
	      return "redirect:/notice/readList";
	   }
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote")
	public String noticeVote(@RequestParam Integer id) {
		
		Notice notice = noticeService.readDetail(id);
		User user = userService.readDetailUsername();
		noticeService.vote(notice, user);
		
		return "redirect:/notice/readDetail?id=" + id;
	}
	
	


}
