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
   	
   	public void sendSimpleMessage(
		      String to, String subject, String text) {
		        
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("dlwognsek55@naver.com"); // 실제 네이버 이메일 주소
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(text);
		        emailSender.send(message);
		    }
   	
   	

    
}
