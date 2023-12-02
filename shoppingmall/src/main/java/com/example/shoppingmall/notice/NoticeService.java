package com.example.shoppingmall.notice;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.shoppingmall.user.User;

public interface NoticeService {

	void create(Notice notice);
	
	List<Notice> readList();
	
	Notice readDetail(Integer id);
	
	void update(Notice notice);
	
	void delete(Integer id);
	
	Page<Notice> getList(int page);
	
	void vote(Notice notice, User user);
	
	void hit(Notice notice, User user);
	

	
	
}
