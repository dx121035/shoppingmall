package com.example.shoppingmall.status;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public void create(Status status) {

		status.setCreateDate(LocalDateTime.now());
		
		statusRepository.save(status);
	}

}
