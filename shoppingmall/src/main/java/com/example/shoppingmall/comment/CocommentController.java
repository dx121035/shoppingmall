package com.example.shoppingmall.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/cocomment")
@Controller
public class CocommentController {
	 @Autowired
	    private CocommentService cocommentService;


	    
	    @PostMapping("/create")
	    public String create(@RequestParam Integer cid,
	    					 @RequestParam Integer nid,
	                         @RequestParam String content) {

	            cocommentService.create(cid, content);

	        return "redirect:/notice/readDetail?id=" + nid;
	    }

}
