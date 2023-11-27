package com.example.shoppingmall.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.user.User;


@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public void create(User user) {

		Cart cart = new Cart();
		
		cart.setUsername(user.getUsername());
		
		cartRepository.save(cart);
	}

	@Override
	public Cart readDetailUsername() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Optional<Cart> oc = cartRepository.findByUsername(username);

		return oc.get();
	}

	@Override
	public void delete() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Optional<Cart> oc = cartRepository.findByUsername(username);
		Cart cart = oc.get();
		cartRepository.delete(cart);
		
		Cart cart2 = new Cart();
		cart2.setUsername(username);
		cartRepository.save(cart2);
	}

}
