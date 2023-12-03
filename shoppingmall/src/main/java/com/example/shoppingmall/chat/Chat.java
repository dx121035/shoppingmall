package com.example.shoppingmall.chat;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shoppingmall.chatreply.Chatreply;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime createDate;
	
	private String username;
	
	@OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
	private List<Chatreply> chatreplyList;
	
}
