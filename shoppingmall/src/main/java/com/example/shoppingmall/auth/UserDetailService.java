package com.example.shoppingmall.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.example.shoppingmall.user.User;
import com.example.shoppingmall.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("서비스에서 확인: " + username);
		
	Optional<User> ou = userDetailRepository.findByUsername(username);
	User user = ou.get();
	
		if(user == null) {
			throw new UsernameNotFoundException("username" + username + " not found");
		}
	
	return user;
	
	}
	
	@Autowired
	private HttpServletRequest req;
	
	public int loginCheck(String username) {
		
		Optional<User> ou = userDetailRepository.findByUsername(username);
		User user = new User();
		
		if (ou.isPresent()) {
			user = ou.get();
			
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			String role = user.getRole().value();
			list.add(new SimpleGrantedAuthority(role));
			
			SecurityContext sc = SecurityContextHolder.getContext();
			
			sc.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, list));
			
			
			HttpSession session = req.getSession(true);
			
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,sc);
		
			return 1;
			
		} else {
			
			return 0;
		}
	
	}
	
	public int loginCheck2() {
		
		User user = userService.readDetailUsername();
		
		if (user != null) {
			
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			String role = user.getRole().value();
			list.add(new SimpleGrantedAuthority(role));
			
			SecurityContext sc = SecurityContextHolder.getContext();
			
			sc.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, list));
			
			
			HttpSession session = req.getSession(true);
			
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,sc);
		
			return 1;
			
		} else {
			
			return 0;
		}
	
	}
	
}
