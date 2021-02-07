package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록이란: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것이다.
@Configuration //빈 등록(IoC관리)
@EnableWebSecurity //시큐리티 필터가 등록이 된다. 그 설정을 아래에서 한다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 의미이다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean //IoC가 된다. 해쉬암호화된 비밀번호를 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해줄때 password를 가로챈다.
	// 이때 해당 password가 무엇으로 해쉬되어 회원가입이 되었는지 알아야만
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
			.authorizeRequests() //request가 들어오면,
				.antMatchers("/","/auth/**", "/js/**","/css/**", "/image/**" ) //이 경로로는(주소로는) 
				.permitAll() //모두 혀용한다.
				.anyRequest() //다른 모든 요청들은(다른 모든 경로,주소로의 요청들은)
				.authenticated() //인증을 받아야한다.
			.and() //인증이 필요한 경우에는
				.formLogin() //로그인폼으로 가는데,
				.loginPage("/auth/loginForm") //로그인 폼에 해당하는 주소는 이 주소이다.
				.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로  요청오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/") //그리고 로그인이 성공했을 때, 이 주소로 이동한다.
				.failureUrl("/fail"); //로그인 실패시에는 이 주소로 간다.
	}
}

