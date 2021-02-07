package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 를 기준으로 허용할 것이다.
// 주소가 /이면 index.jsp로 출입할 수 있도록 허용할 것이다.
//그리고 static 이하에 있는 /js/**, /css/**, /image/**로의 출입도 허용해 줄 것이다.

@Controller
public class UserController {

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "user/updateForm";
	}
	
	
}
