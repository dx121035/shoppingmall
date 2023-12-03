package com.example.shoppingmall.chatreply;

public interface ChatreplyService {
    
    void create(Integer id, String content, String username);
    
    Chatreply readdetail(Integer id);
}
