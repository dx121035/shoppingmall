package com.example.shoppingmall.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shoppingmall.cart.Cart;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	//@Query("SELECT SUM(price) FROM Item WHERE cart = cart")
	@Query("SELECT SUM(i.price) FROM Item i WHERE i.cart = :cart")
	Integer findTotalAmount(@Param("cart") Cart cart);

}
