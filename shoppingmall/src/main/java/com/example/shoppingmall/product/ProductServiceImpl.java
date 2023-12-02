package com.example.shoppingmall.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("bucket-va1rkc")
	private String bucketName;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void create(Product product, MultipartFile multipartFile) throws IOException{
		
		
		File file = new File(multipartFile.getOriginalFilename());
		
		
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		
		
		String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, file));
		
		file.delete();
		
		
		product.setImageName(filename);
		product.setCreateDate(LocalDateTime.now());
		
		productRepository.save(product);
	}

	@Override
	public List<Product> readList() {
		
		return productRepository.findAll();
	}

	@Override
	public Product readDetail(Integer id) {
		
		Optional<Product> op = productRepository.findById(id);
		Product product = op.get();
		
		return product;
	}

	@Override
	public void update(Product product, MultipartFile multipartFile) throws IOException{
		
		String filecheck = multipartFile.getOriginalFilename();

		if (filecheck != null && !filecheck.trim().isEmpty()) {
		
		
		File file = new File(multipartFile.getOriginalFilename());
		
		
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		
		
		String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, file));
		
		file.delete();
		
		
		
		product.setImageName(filename);
		product.setCreateDate(LocalDateTime.now());
		}
		productRepository.save(product);
	}
	
		

	
	@Override
	public void delete(Integer id) {
		Optional<Product> op = productRepository.findById(id);
		Product product = op.get();
		
		productRepository.delete(product);
	}


}
