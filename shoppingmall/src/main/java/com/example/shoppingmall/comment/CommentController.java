package com.example.shoppingmall.comment;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @PostMapping("/create")
    public String create(Principal principal, @RequestParam Integer id,
                         @RequestParam String content) {
    	
            commentService.create(id, content);
             
            String username = principal.getName();

            commentService.sendSimpleMessage(
                  "dlwognsek55@naver.com",
                  "공지게시판에" + username + "이 댓글을 작성하였습니다",
                  "작성하신 내용 :" + content
              );

        return "redirect:/notice/readDetail?id=" + id;
    }
    
    


   


}