package com.example.shoppingmall.cart;

import com.example.shoppingmall.user.User;

public interface CartService {
	
	void create(User user);
	
	Cart readDetailUsername();
	
	void delete();

}
