package com.example.shoppingmall.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.shoppingmall.delivery.Delivery;
import com.example.shoppingmall.user.User;

public interface AdminService {
	
	List<User> userReadList();
	
	Page<User> userReadList(int page);
	
	User userReadDetail(String username);
	
	List<Delivery> deliveryReadList();
	
	Page<Delivery> deliveryReadList(int page);
	
	void updateRole(String username, String newRole);

}
