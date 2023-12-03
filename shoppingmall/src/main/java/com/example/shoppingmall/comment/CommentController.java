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


              

        return "redirect:/notice/readDetail?id=" + id;
    }
    
    


   


}