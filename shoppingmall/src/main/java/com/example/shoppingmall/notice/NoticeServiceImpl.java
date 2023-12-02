package com.example.shoppingmall.notice;

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
import org.springframework.stereotype.Service;

import com.example.shoppingmall.user.User;


@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;

	@Override
	public void create(Notice notice) {
		
		notice.setCreateDate(LocalDateTime.now());
		noticeRepository.save(notice);

	}

	@Override
	public List<Notice> readList() {
		return noticeRepository.findAll();
	}

	@Override
	public Notice readDetail(Integer id) {
		Optional<Notice> on = noticeRepository.findById(id);
		
		return on.get();
	}

	@Override
	public void update(Notice notice) {
		
		
		noticeRepository.save(notice);

	}

	@Override
	public void delete(Integer id) {
		Optional<Notice> on = noticeRepository.findById(id);
		
		noticeRepository.delete(on.get());

	}
	
	public Page<Notice> getList(int page) {
	
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return noticeRepository.findAll(pageable);
	}

	@Override
	public void vote(Notice notice, User user) {
		Set<User> a = notice.getVoter();
		
		a.add(user);
		
		noticeRepository.save(notice);
		
	}
	
	

	@Override
	public void hit(Notice notice, User user) {
		Set<User> a = notice.getHitter();
		
		a.add(user);
		
		noticeRepository.save(notice);
		
	}
	
	


}
