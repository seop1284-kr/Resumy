package com.proj.resumy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.domain.MemberDTO;
import com.proj.resumy.service.MailService;
import com.proj.resumy.service.MemberService;

@Controller
@RequestMapping("")
public class MainController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberService memberService;

	public MainController() {
		System.out.println("MainController() 생성");
	}
	
	// index page
	@RequestMapping("")
	public String indexPage(HttpServletRequest request) {
		//System.out.println(request.getSession().getMaxInactiveInterval());
		return "index";
	}
	
	// company board 콘텐츠
	@RequestMapping("/companyBoard")
	public String companyBoard(Model model) {

		return "mainBoard/company/companyBoard";
	}
	

	// Spring Security(이하 '시큐리티') 가 적용되면
	// /login 등의 url 로의 request 를  시큐리티가 모두 낚아 챕니다.
	// 나중에 SecurityConfig 가 설정되면 낚아 채지 않게 된다.
	
	
	// 로그인 페이지
	@GetMapping("/login")
	public String login() {
		return "loginForm";
	}
	
	// 회원가입 페이지
	@GetMapping("/join")
	public String join() {
		return "joinForm";
	}
	
	@PostMapping("/joinOk")
	public String joinOk(Model model, MemberDTO user) {
		System.out.println(user);
		
		// join id 중복 체크
		if (memberService.findById(user.getUserid()) != null) {
			return "redirect:/join";
		}
		// pw encode
		String rawPassword = user.getPw();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		
		System.out.println(user);
		
		int cnt = memberService.addMember(user);
		
		model.addAttribute("result", cnt); // 회원가입 성공/실패 결과
		
		return "joinOk";
	}
	

}
