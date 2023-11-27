package com.example.shoppingmall.delivery;

import org.springframework.data.domain.Page;

public interface DeliveryService {
	
	void create(String uid);
	
	Page<Delivery> readList(int page);
	
	Delivery readDetail(Integer id);
}
