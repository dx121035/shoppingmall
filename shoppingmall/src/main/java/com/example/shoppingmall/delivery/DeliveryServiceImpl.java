package com.example.shoppingmall.delivery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.cart.Cart;
import com.example.shoppingmall.cart.CartService;
import com.example.shoppingmall.item.Item;
import com.example.shoppingmall.item.ItemService;
import com.example.shoppingmall.status.Status;
import com.example.shoppingmall.status.StatusService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;

	@Override
	public void create(String uid) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Cart cart = cartService.readDetailUsername();
		List<Item> item = cart.getItemList();
		String itemName = item.get(0).getName();
		int itemSize = item.size() - 1;
		
		String allAbout = itemName + " 외 " + itemSize + "건";
		int total = itemService.findTotalAmount(cart);
		
		Delivery delivery = new Delivery();
		delivery.setCreateDate(LocalDateTime.now());
		delivery.setAllAbout(allAbout);
		delivery.setTotal(total);
		delivery.setUid(uid);
		delivery.setUsername(username);
		deliveryRepository.save(delivery);
		
		Status status = new Status();
		status.setStep(1);
		status.setUsername(username);
		status.setSno("");
		status.setDelivery(delivery);
		statusService.create(status);
		
		cartService.delete();
	}

	@Override
	public Page<Delivery> readList(int page) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		// 최신 게시글이 위로 올라오게 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		return deliveryRepository.findByUsername(username, pageable);
	}

	@Override
	public Delivery readDetail(Integer id) {
		
		Optional<Delivery> od = deliveryRepository.findById(id);
		Delivery delivery = od.get();
		
		return delivery;
	}

}
