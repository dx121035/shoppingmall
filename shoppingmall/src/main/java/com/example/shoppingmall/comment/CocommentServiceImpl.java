package com.example.shoppingmall.comment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CocommentServiceImpl implements CocommentService{

    @Autowired
    private CommentService commentService;

    @Autowired
    private CocommentRepository cocommentRepository;

    @Override
    public void create(Integer id, String content) {
        
        Comment comment = commentService.readDetail(id);

      //현재 로그인 하여 사용중인 사람의 정보 추출
      		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      		String username = auth.getName();
        
        Cocomment c = new Cocomment();
            c.setContent(content);
            c.setComment(comment);
            c.setUsername(username);
            c.setCreateDate(LocalDateTime.now());
            cocommentRepository.save(c);

    }

  
}
