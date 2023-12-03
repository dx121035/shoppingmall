package com.example.shoppingmall.chatreply;

import java.time.LocalDateTime;

import com.example.shoppingmall.chat.Chat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Chatreply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String content;

	private LocalDateTime createDate;
	
	private String username;
	
	@ManyToOne
	private Chat chat;

	
	
	
}
