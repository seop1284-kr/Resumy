package com.proj.resumy.fed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.intro.service.AjaxIntroService;

// FedBoardController 김진섭
@Controller
@RequestMapping("")	// context path
public class FedBoardController {
	private AjaxIntroService introService;

	@Autowired
	public void setIntroService(AjaxIntroService introService) {
		this.introService = introService;
	}
	
	public FedBoardController() {
		System.out.println("FedBoardController() 생성");
	}
	
	// 자소서 피드백 게시판
	@RequestMapping("/fedboard") 
	public String fedBoardPage(Model model) {
		//model.addAttribute("list", introService.list());

		return "resumeBoard"; // 문서 명
	}
}