package com.example.shoppingmall.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	
	Page<Delivery> findByUsername(String username, Pageable pageable);
	
	Page<Delivery> findAll(Pageable pageable);
}
