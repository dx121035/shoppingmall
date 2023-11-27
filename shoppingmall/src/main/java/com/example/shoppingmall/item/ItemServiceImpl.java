package com.example.shoppingmall.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.cart.Cart;
import com.example.shoppingmall.cart.CartService;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CartService cartService;

	@Override
	public void create(Item item) {
		
		Cart cart = cartService.readDetailUsername();

		item.setCart(cart);
		
		itemRepository.save(item);
	}

	@Override
	public Integer findTotalAmount(Cart cart) {

		return itemRepository.findTotalAmount(cart);
	}

}
