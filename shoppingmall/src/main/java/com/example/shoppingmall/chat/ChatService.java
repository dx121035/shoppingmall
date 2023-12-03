package com.example.shoppingmall.chat;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ChatService {

	void create(Chat chat);
	
	List<Chat> readlist();
	
	Chat readdetailusername();
	
	Chat readdetail(Integer id);
	
	void update(Chat chat);
	
	void delete(Integer id);
	
	Page<Chat> getList(int page);
}
