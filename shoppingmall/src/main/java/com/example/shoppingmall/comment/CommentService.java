package com.example.shoppingmall.comment;

public interface CommentService {
    
    void create(Integer id, String content);

    Comment readDetail(Integer id);
    
	
}
