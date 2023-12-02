package com.example.shoppingmall.product;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shoppingmall.cart.Cart;
import com.example.shoppingmall.cart.CartService;
import com.example.shoppingmall.item.ItemService;
import com.example.shoppingmall.user.UserService;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/create")
	public String create(Model model) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		
		return "product/create";
	}
	
	@PostMapping("/create")
	public String create(Product product,
						 @RequestParam("filename") MultipartFile file
			             ) throws IOException {
		
		productService.create(product, file);
		
		return "redirect:/product/readList";
	}
	
	
	@GetMapping("/readList")
	public String readList(Model model) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		model.addAttribute("lists", productService.readList());
		
		return "product/readList";
	}
	
	
	@GetMapping("/readDetail")
	public String readdetail(Model model, @RequestParam("id") Integer id) {
		
		Cart cart = cartService.readDetailUsername();
		
		model.addAttribute("cart", cartService.readDetailUsername());
		model.addAttribute("user", userService.readDetailUsername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		model.addAttribute("details", productService.readDetail(id));
		
		return "product/readDetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, @RequestParam("id") Integer id) {
		
		model.addAttribute("details", productService.readDetail(id));
		return "product/update";
	}
	
	@PostMapping("/update")
	public String update(Product product,
						 @RequestParam("filename") MultipartFile file
			             ) throws IOException{
		
		productService.update(product, file);
		
		return "redirect:/product/readList";
	}
	
	 @GetMapping("/delete")
	   public String delete(@RequestParam Integer id) {
	      productService.delete(id);
	      return "redirect:/product/readList";
	   }

	
}
