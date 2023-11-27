package com.example.shoppingmall.cart;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shoppingmall.item.Item;
import com.example.shoppingmall.item.ItemService;
import com.example.shoppingmall.user.UserService;


@RequestMapping("/cart")
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/readDetail")
	public String readDetail(Model model, Principal pricipal) {
		
		if (pricipal != null) {
		
		Cart cart = cartService.readDetailUsername();
		List<Item> item = cart.getItemList();
		
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		
		if (itemService.findTotalAmount(cart) != null) {
			
			model.addAttribute("itemName", item.get(0).getName());
			model.addAttribute("itemSize", item.size() - 1);
			
			return "cart/readDetail";
		}
		
		return "cart/readDetailNoItem";
		}
		
		return "/login";
	}
	
	@GetMapping("/delete")
	public String delete() {
		
		cartService.delete();
		
		return "redirect:/cart/readDetail";
	}
	
}
