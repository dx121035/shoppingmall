package com.example.shoppingmall.item;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@PostMapping("/create")
	public String create(Integer id, int price, String productId, String name, String itemImage) {
		
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		item.setProductId(productId);
		item.setItemImage(itemImage);
		item.setCreateDate(LocalDateTime.now());
		
		itemService.create(item);
		
		return "redirect:/product/readDetail?id=" + id;
	}
	
}
