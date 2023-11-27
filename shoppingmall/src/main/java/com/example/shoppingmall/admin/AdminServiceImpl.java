package com.example.shoppingmall.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.delivery.Delivery;
import com.example.shoppingmall.delivery.DeliveryRepository;
import com.example.shoppingmall.user.Role;
import com.example.shoppingmall.user.User;
import com.example.shoppingmall.user.UserRepository;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public List<User> userReadList() {
		
		return userRepository.findAll();
	}
	
	@Override
	public Page<User> userReadList(int page) {
		
		// 최신 게시글이 위로 올라오게 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		return userRepository.findAll(pageable);
	}

	@Override
	public User userReadDetail(String username) {
		
		Optional<User> ou = userRepository.findByUsername(username);
		User user = ou.get();

		return user;
	}
	
	@Override
	public List<Delivery> deliveryReadList() {
		
		return deliveryRepository.findAll();
	}

	@Override
	public Page<Delivery> deliveryReadList(int page) {
		
		// 최신 게시글이 위로 올라오게 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		return deliveryRepository.findAll(pageable);
	}


	@Override
	public void updateRole(String username, String newRole) {

		Optional<User> ou = userRepository.findByUsername(username);
		User user = ou.get();
		
		if (newRole.equals("ROLE_ADMIN")) {
			
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
			
		} else {
			user.setRole(Role.ROLE_USER);
			userRepository.save(user);
		}
	}

}
