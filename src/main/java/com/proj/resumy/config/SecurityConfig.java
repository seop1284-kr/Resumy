package com.proj.resumy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 스프링 시큐리티 설정

@Configuration   // 일단 컨테이너에 생성되어야 한다.
@EnableWebSecurity // Web Security 를 활성화 해준다.
                // 스프링 시큐리티 필터가 스프링 필터 체인에 등록이 된다.

             // ↓ 등록할 필터 객체
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// PasswordEncoder 를 bean 으로 IoC 에 등록
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();   // CSRF 비활성화
		http.authorizeRequests()
			// 인증에대한 세팅
		
			// ↓ /qnaView.do 로 들어오는 요청은 인증이 필요.
			.antMatchers("/main/qna/view.do").authenticated()
			
			// ↓ /qnaWrtie.do 로 들어오는 요청은 인증이 필요.
			.antMatchers("/main/qna/write.do").authenticated()
			
			// ↓ /myp/** 주소로 들어오는 요청은 인증이 필요.
			//.antMatchers("/myp/**").authenticated()
		
			// ↓ /myp/** 주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_MEMBER 나 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
			.antMatchers("/myp/**").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_ADMIN')")
			
			// ↓ /mng/** 주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_MEMBER와 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
			.antMatchers("/mng/**").access("hasRole('ROLE_MEMBER') and hasRole('ROLE_ADMIN')")
			

			// ↓ /sample/admin/**  주소로 들어오는 요청은 '인증' 뿐 아니라 ROLE_ADMIN 권한을 갖고 있어야 한다 ('인가')
			//.antMatchers("/sample/admin/**").access("hasRole('ROLE_ADMIN')")
			//.antMatchers("/resumeAjax/**").access("hasRole('ROLE_ADMIN')")
			
			// ↓ 그 밖의 다른 요청은 모두 permit!
			.anyRequest().permitAll()
			
			// 로그아웃 처리 로그아웃 후 메인페이지로
			.and()
			.logout()
			.logoutSuccessUrl("/")
			
			// 접근오류(권한오류) 발생시 /login 으로 이동시키기 
			.and()
			.formLogin()
	
			// 로그인 처리
			.loginPage("/login")
			.usernameParameter("userid")   // 만약 로그인 username 이 name="username" 이 아닌경우
			.loginProcessingUrl("/loginOk")  // "/loginOk" url 로 request 가 들어오면 시큐리티가 낚아채서 처리, 대신 로그인을 진행해준다.
									// 이와 같이 하면 Controller 에서 /longinOk 를 만들지 않아도 된다!
			.defaultSuccessUrl("/")   // 직접 /login → /loginOk 에서 성공하면 "/" 로 이동시키기
				// 만약 다른 특정페이지에 진입하려다 로그인 하여 성공하면 해당 페이지로 이동 (너무 편리!)
			.successHandler(new MyAuthenticationSuccessHandler())
			;
	}
}



















