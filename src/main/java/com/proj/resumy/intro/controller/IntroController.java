package com.proj.resumy.intro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.domain.IntroDTO;
import com.proj.resumy.service.IntroService;

// IntroController(자소서 관리 페이지) 김진섭
@Controller
@RequestMapping("/myp/resume")
public class IntroController {
	private IntroService introService;

	@Autowired
	public void setIntroService(IntroService introService) {
		this.introService = introService;
	}
	
	public IntroController() {
		System.out.println("IntroController() 생성");
	}
	
	
	// 자소서 관리 페이지
	@RequestMapping("/")
	public String list(Model model) {
		return "myp/mypageTemplate";
	}
	
//	@RequestMapping("/write")
//	public String list(Model model) {
//		
//		model.addAttribute("list", introService.list());
//
//		return "view";
//	}
}
