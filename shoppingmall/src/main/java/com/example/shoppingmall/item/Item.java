package com.example.shoppingmall.item;

import java.time.LocalDateTime;

import com.example.shoppingmall.cart.Cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private int price;
	
	private String productId;
	
	private String itemImage;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Cart cart;

}
