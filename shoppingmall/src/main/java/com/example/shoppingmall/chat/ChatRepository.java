package com.example.shoppingmall.chat;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	Page<Chat> findAll(Pageable pageable);
	
	Optional<Chat> findByUsername(String id);
}
