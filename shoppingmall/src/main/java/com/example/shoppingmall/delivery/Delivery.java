package com.example.shoppingmall.delivery;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shoppingmall.status.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String uid;
	
	private String allAbout;
	
	@OneToMany(mappedBy = "delivery", cascade = CascadeType.REMOVE)
	private List<Status> statusList;
	
	private String username;
	
	private int total;
	
	private LocalDateTime createDate;

}
