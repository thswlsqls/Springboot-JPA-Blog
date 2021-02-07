package com.cos.blog.controller.api;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import ch.qos.logback.core.encoder.Encoder;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping("/auth/joinProc") //회원가입을 수행합니다.
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 instert를 하고 아래에서 return이 되어야 한다.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
//	// 다음시간에 스프링 시큐리티 이용해서 로그인
//	@PostMapping("/api/user/login") //로그인을 수행합니다.
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//		System.out.println("UserApiController:login호출됨");
//		User principal = userService.로그인(user); //principal (접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바오브젝트를 JSON으로 변환해서 리턴(Jackson)
//}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ 
		userService.회원수정(user);
		//트랜잭션이 종료되기 때문에 DB값은 변경된다.
		//그러나 세션값은 변경되지 않은 상태이다. 직접 세션값을 변경해주어야 한다.
		//그래서 로그아웃 후 재로그인 해야지만 변경된 값을 화면에서 확인이 가능하다.
		//세션등록
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}