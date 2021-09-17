package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.service.IntroService;

// FedBoardController 김진섭
@Controller
@RequestMapping("/fedboard")
public class FedBoardController {
	private IntroService introService;

	@Autowired
	public void setIntroService(IntroService introService) {
		this.introService = introService;
	}
	
	public FedBoardController() {
		System.out.println("FedBoardController() 생성");
	}
	
	// 자소서 피드백 게시판
	@RequestMapping("/")
	public String mainPage(Model model) {
		model.addAttribute("list", introService.list());

		return "view";
	}
}