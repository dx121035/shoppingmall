package com.example.shoppingmall.comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.notice.Notice;
import com.example.shoppingmall.notice.NoticeService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private JavaMailSender emailSender;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void create(Integer id, String content) {
    	
    	//현재 로그인 하여 사용중인 사람의 정보 추출
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();									

        
        Notice notice = noticeService.readDetail(id);

        Comment comment = new Comment();
            comment.setContent(content);
            comment.setNotice(notice);
            comment.setUsername(username);
            comment.setCreateDate(LocalDateTime.now());
            commentRepository.save(comment);

    }

   	@Override
	public Comment readDetail(Integer id) {
		Optional<Comment> oc = commentRepository.findById(id);
		return oc.get();
	}
   	
 
   	
   	

    
}
