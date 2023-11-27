package com.example.shoppingmall.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUsername(String username);
}
