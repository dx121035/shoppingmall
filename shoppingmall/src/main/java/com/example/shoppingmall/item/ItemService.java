package com.example.shoppingmall.item;

import com.example.shoppingmall.cart.Cart;

public interface ItemService {
	
	void create(Item item);
	
	Integer findTotalAmount(Cart cart);
	
}
