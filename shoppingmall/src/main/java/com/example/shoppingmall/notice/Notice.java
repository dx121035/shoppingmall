package com.example.shoppingmall.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.shoppingmall.comment.Comment;
import com.example.shoppingmall.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
		
	private String content;
	
	private String category;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;
	
	private LocalDateTime createDate;
	
	@ManyToMany
	Set<User> voter;
			
	@ManyToMany
	Set<User> hitter;
	
}