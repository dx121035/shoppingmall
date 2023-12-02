package com.example.shoppingmall.product;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	void create(Product product, MultipartFile multipartFile) throws IOException;
	
	List<Product> readList();
	
	Product readDetail(Integer id);
	
	void update(Product product, MultipartFile multipartFile) throws IOException;
	
	void delete(Integer id);
	
	
}
