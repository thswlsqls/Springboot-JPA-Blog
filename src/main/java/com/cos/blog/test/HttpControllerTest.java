package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController //문자를 리턴
public class HttpControllerTest {
	
	private  static  final  String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public  String  lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();	//빌더패턴을 사용하여 순서를 지키지 않고도 값을 넣을 수 있다.	
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter : "+m.getUsername());
		return "lombok test 완료";
	}
	
	
	//인터넷 브라우저 요청은 get요청만 가능하다.
	//http://localhost:8000/blog/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {  //?id=1&username=ssar&password=1234&email=ssar@nate.com 이것을 스프링이 멤버변수에 매칭한다.
		return "get요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")  //text/plain, application/json
	public String postTest(@RequestBody Member m) {  //MessageConverter (스프링부트)
		return "post요청 :"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail(); 
		}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청 :" +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail(); 
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
