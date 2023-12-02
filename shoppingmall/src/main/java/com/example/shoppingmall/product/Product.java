package com.example.shoppingmall.product;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		private String name;
		
		private int price;
		
		@Column(columnDefinition = "LONGTEXT")
		private String about;
		
		private String productId;
			
		private String imageName;
		
		private String message;
		
		private LocalDateTime createDate;
		
		
}
