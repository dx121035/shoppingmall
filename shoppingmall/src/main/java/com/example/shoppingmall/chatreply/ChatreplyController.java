package com.example.shoppingmall.chatreply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.chat.Chat;
import com.example.shoppingmall.chat.ChatService;
import com.example.shoppingmall.mail.MailService;




@RequestMapping("/chatreply")
@Controller
public class ChatreplyController {

	@Autowired
	private ChatService chatService;
	
    @Autowired
    private ChatreplyService chatreplyService;
    
    @Autowired
    private MailService mailService;


    
    @PostMapping("/create")
    public String create(@RequestParam String username,
                         @RequestParam String content,
                         @RequestParam Integer id,
                         @RequestParam String chatuser) {

    	
		
    	chatreplyService.create(id, content, username);
    	
    	
    	
    	
    	Chat chat = chatService.readdetail(id);
    	
    	int chatid = chat.getId();

    	System.out.println(username);
    	
    	if(!username.equals("admin@naver.com")) {
    		
    		return "redirect:/chat/readdetail?username=" + username;
    		
    	} else {
    		
    		mailService.sendSimpleMessage(chatuser, chatuser + "님에게 채팅이 왔습니다", "작성하신 내용 :" + content);
    		
    		return "redirect:/chat/adminreaddetail?id=" + chatid;
    	}
    }





}