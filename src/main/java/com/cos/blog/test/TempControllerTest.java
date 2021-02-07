package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller //파일을 리턴
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명: /home.html
		//풀경로: src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public  String  tempImg() {
		return "/a.PNG";
	}
	
	@GetMapping("/temp/jsp") 
	public  String  tempJsp() {
		
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/test.jsp
		return "test"; //컴파일을 해야 하는 동적인 자바 파일을 static폴더 아래에 두면 브라우저가 이것을 찾지 못한다.
	}
}
