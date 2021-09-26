package com.proj.resumy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// Mypage(마이페이지) 컨트롤러
@Controller
@RequestMapping("/myp")
public class MypageController {

	public MypageController() {
		System.out.println("MypageController() 생성");
	}
	
	// 자소서 관리 페이지
	@RequestMapping("/resume")
	public String resume(Model model) {
		return "myp/resume";
	}
	
	// 파일 관리 페이지
	@RequestMapping("/file")
	public String file(Model model) {
		return "myp/file";
	}
	
	// 이력 관리 페이지
	@RequestMapping("/historyMng")
	public String career(Model model) {
		return "myp/historyMng";
	}
}
