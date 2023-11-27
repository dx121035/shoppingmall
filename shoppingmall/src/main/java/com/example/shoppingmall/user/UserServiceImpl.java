package com.example.shoppingmall.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.shoppingmall.cart.CartService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("bucket-va1rkc")
	private String bucketName;

	@Override
	public void create(User user, MultipartFile file) throws IOException {
		
		File file2 = new File(file.getOriginalFilename());
		
		//aws s3 multipartfile을 막바로 올릴 수 없게 되어있다.
		//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다.
		try (FileOutputStream fos = new FileOutputStream(file2)) {
			fos.write(file.getBytes());
		}
		
		//역시 보안등의 이유로 uuid를 사용해도 좋지만 이번엔 다른 방법을 사용해보자
		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, file2));
		
		//파일을 s3으로 올리고 서버에 저장했던 파일은 이제 완전히 삭제한다.
		file2.delete();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreateDate(LocalDateTime.now());
		user.setImage(filename);
		user.setRole(Role.ROLE_USER);
		
		userRepository.save(user);
		
		// 카트 생성
		cartService.create(user);
		
	}

	@Override
	public User readDetailUsername() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
				
		Optional<User> ou = userRepository.findByUsername(username);
		User user = ou.get();
				
		return user;
	}

	@Override
	public void sendSms(String tel, int number) throws NoSuchAlgorithmException, IOException {

		String charsetType = "UTF-8";
		
	    String sms_url = "";
	    sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
	    String user_id = "austiny"; // SMS아이디
	    String secure = "b05c71225043952fdd34a1a93abaf4ab";//인증키
	    String msg = "[쇼핑몰] 본인확인 인증번호(" + number + ")를 입력해 주세요.";
	    String rphone = tel;
	    String sphone1 = "010";
	    String sphone2 = "2737";
	    String sphone3 = "3944";
	    String mode = "1";
	    String smsType = "5";
	    
	    // sms 서버 변수값 설정
	    String[] host_info = sms_url.split("/");
	    String host = host_info[2];
	    String path = "/" + host_info[3];
	    int port = 80;

	    // 데이터 맵핑 변수 정의
	    String arrKey[]
	        = new String[] {"user_id","secure","msg", "rphone",
	        					 "sphone1","sphone2","sphone3","mode","smsType"};
	    String valKey[]= new String[arrKey.length] ;
	    valKey[0] = user_id;
	    valKey[1] = secure;
	    valKey[2] = msg;
	    valKey[3] = rphone;
	    valKey[4] = sphone1;
	    valKey[5] = sphone2;
	    valKey[6] = sphone3;
	    valKey[7] = mode;
	    valKey[8] = smsType;
	   
	    String boundary = "";
	    Random rnd = new Random();
	    String rndKey = Integer.toString(rnd.nextInt(32000));
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] bytData = rndKey.getBytes();
	    md.update(bytData);
	    byte[] digest = md.digest();
	    for(int i =0;i<digest.length;i++)
	    {
	        boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
	    }
	    boundary = "---------------------"+boundary.substring(0,11);

	    // 본문 생성
	    String data = "";
	    String index = "";
	    String value = "";
	    for (int i=0;i<arrKey.length; i++)
	    {
	        index =  arrKey[i];
	        value = valKey[i];
	        data +="--"+boundary+"\r\n";
	        data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
	        data += "\r\n"+value+"\r\n";
	        data +="--"+boundary+"\r\n";
	    }

	    InetAddress addr = InetAddress.getByName(host);
	    Socket socket = new Socket(host, port);
	    // 헤더 전송
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
	    wr.write("POST "+path+" HTTP/1.0\r\n");
	    wr.write("Content-Length: "+data.length()+"\r\n");
	    wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
	    wr.write("\r\n");
	    // 데이터 전송
	    wr.write(data);
	    wr.flush();
	    
	    wr.close();
	}

	@Override
	public void update(User user, MultipartFile file) throws IOException {
		
		String filecheck = file.getOriginalFilename();
		
		if(filecheck != null && !filecheck.trim().isEmpty()) {
			
			File file2 = new File(file.getOriginalFilename());
			
			//aws s3 multipartfile을 막바로 올릴 수 없게 되어있다.
			//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다.
			try (FileOutputStream fos = new FileOutputStream(file2)) {
				fos.write(file.getBytes());
			}
			
			//역시 보안등의 이유로 uuid를 사용해도 좋지만 이번엔 다른 방법을 사용해보자
			String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			amazonS3.putObject(new PutObjectRequest(bucketName, filename, file2));
			
			//파일을 s3으로 올리고 서버에 저장했던 파일은 이제 완전히 삭제한다.
			file2.delete();
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setImage(filename);
			
			userRepository.save(user);
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
	}

	@Override
	public void delete() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
				
		Optional<User> ou = userRepository.findByUsername(username);
		User user = ou.get();
		
		userRepository.delete(user);
	}
	
}
