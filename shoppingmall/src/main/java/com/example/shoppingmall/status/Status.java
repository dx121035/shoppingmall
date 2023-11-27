package com.example.shoppingmall.status;

import java.time.LocalDateTime;

import com.example.shoppingmall.delivery.Delivery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String sno;	// 송장번호
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Delivery delivery;
	
	private String username;
	
	private int step;

}
