package com.proj.resumy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// IntroController(자소서 관리 페이지) 김진섭
@Controller
@RequestMapping("/myp")
public class MypageController {

	public MypageController() {
		System.out.println("IntroController() 생성");
	}
	
	// TODO id(회원 고유 번호)를 가진 자소서 데이터를 id(회원 고유 번호)를 가진 회원으로 로그인 되어 있어야 접근 가능
	// TODO iid(자소서 고유 번호)를 가진 자소서 데이터의 mid(회원 고유 번호)를 가진 회원으로 로그인 되어 있어야 접근 가능
	// 자소서 관리 페이지
	@RequestMapping("/resume")
	public String resume(Model model) {
		return "myp/resume";
	}
	
//	// 파일 관리 페이지
//	@RequestMapping("/file")
//	public String file(Model model) {
//		return "myp/file";
//	}
//	
//	// 이력 관리 페이지
//	@RequestMapping("/career")
//	public String career(Model model) {
//		return "myp/career";
//	}
}
