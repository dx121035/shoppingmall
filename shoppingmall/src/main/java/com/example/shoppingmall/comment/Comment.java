package com.example.shoppingmall.comment;

import java.time.LocalDateTime;
import java.util.List;


import com.example.shoppingmall.notice.Notice;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String content;

	private LocalDateTime createDate;
	
	private String username;
	
	@ManyToOne
	private Notice notice;
	

}
