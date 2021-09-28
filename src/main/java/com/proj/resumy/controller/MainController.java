package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.domain.MemberDTO;
import com.proj.resumy.intro.service.AjaxIntroService;
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
	
	// main page
	@RequestMapping("/")
	public String mainPage(String headerMenu, Model model) {
		if (headerMenu == null) {
			model.addAttribute("headerMenu", "resume");
		} else {
			model.addAttribute("headerMenu", headerMenu);
		}
		return "index";
	}
	

	// Spring Security(이하 '시큐리티') 가 적용되면
	// /login 등의 url 로의 request 를  시큐리티가 모두 낚아 챕니다.
	// 나중에 SecurityConfig 가 설정되면 낚아 채지 않게 된다.
	@GetMapping("/login")
	//@ResponseBody
	public String login() {	
		return "loginForm";
	}
	
	@GetMapping("/join")
	public String join() {
		return "joinForm";
	}
	
	@PostMapping("/joinOk")
	public String joinOk(MemberDTO user) {
		System.out.println(user);
		
		String rawPassword = user.getPw();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		
		int cnt = memberService.addMember(user);
		
		return "redirect:/login";
	}
}
