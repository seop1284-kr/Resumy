package com.proj.resumy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// MypageController(마이페이지) 김진섭
@Controller
@RequestMapping("/myp")
public class MypageController {

	public MypageController() {
		System.out.println("MypageController() 생성");
	}
	
	// 자소서 관리 페이지(메인) 김진섭
	@RequestMapping("/resume")
	public String resumeBoard(Model model) {
		return "myp/resume/resumeBoard";
	}
	
	// 자소서 읽기, 수정, 작성 페이지 김진섭
	@RequestMapping("/resume/crud")
	public String resumeCrud(Model model) {
		return "myp/resume/resumeCrud";
	}
}
