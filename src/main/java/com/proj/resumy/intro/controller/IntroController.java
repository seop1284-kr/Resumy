package com.proj.resumy.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// IntroController(자소서 관리 페이지) 김진섭
@Controller
@RequestMapping("/myp/resume")
public class IntroController {

	public IntroController() {
		System.out.println("IntroController() 생성");
	}
	
	// TODO id(회원 고유 번호)를 가진 자소서 데이터를 id(회원 고유 번호)를 가진 회원으로 로그인 되어 있어야 접근 가능
	// 자소서 관리 페이지
	@RequestMapping("/")
	public String list(Model model) {
		return "myp/resume/resume";
	}
	
	
	// TODO iid(자소서 고유 번호)를 가진 자소서 데이터의 mid(회원 고유 번호)를 가진 회원으로 로그인 되어 있어야 접근 가능
	// 자소서 읽기 페이지
	@GetMapping("/{iid}")
	public String read(Model model, @PathVariable int iid) {
		model.addAttribute("iid", iid);
		return "myp/resume/read";
	}
	
//	@RequestMapping("/write")
//	public String list(Model model) {
//		
//		model.addAttribute("list", introService.list());
//
//		return "view";
//	}
}
