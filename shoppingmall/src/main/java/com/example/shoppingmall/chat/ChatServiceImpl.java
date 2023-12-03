package com.example.shoppingmall.chat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public void create(Chat chat) {

		chat.setCreateDate(LocalDateTime.now());
		chatRepository.save(chat);

	}

	@Override
	public List<Chat> readlist() {
		return chatRepository.findAll();
	}

	@Override
	public Chat readdetailusername() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Optional<Chat> optionalChat = chatRepository.findByUsername(username);

		if (optionalChat.isPresent()) {
			// 사용자에 대한 채팅이 이미 존재하는 경우, 존재하는 채팅을 반환
			return optionalChat.get();
		} else {
			// 사용자에 대한 채팅이 없는 경우, 새로운 채팅 생성
			Chat newChat = new Chat();
			newChat.setUsername(username);
			newChat.setCreateDate(LocalDateTime.now());
			// 다른 필요한 초기화 작업 수행
			chatRepository.save(newChat); // 새로운 채팅을 저장
			return newChat;
		}
	}

	@Override
	public Chat readdetail(Integer id) {

		Optional<Chat> on = chatRepository.findById(id);

		

		return on.get();
	}

	@Override
	public void update(Chat chat) {

		chat.setCreateDate(LocalDateTime.now());
		chatRepository.save(chat);

	}

	@Override
	public void delete(Integer id) {
		Optional<Chat> on = chatRepository.findById(id);

		chatRepository.delete(on.get());

	}

	public Page<Chat> getList(int page) {
		// 게시물의 나열 순서가 최근 자료가 위로 올라오게 즉 역순으로 설정
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));

		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return chatRepository.findAll(pageable);
	}

}
