package com.example.shoppingmall.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	
	void create(User user, MultipartFile file) throws IOException;
	
	User readDetailUsername();
	
	void sendSms(String tel, int number) throws NoSuchAlgorithmException, IOException;
		
	void update(User user, MultipartFile file) throws IOException;
	
	void delete();

}
