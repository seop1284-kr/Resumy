package com.proj.resumy.fed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.fed.service.IntroFedService;
import com.proj.resumy.intro.service.AjaxIntroService;

// FedBoardController 김진섭
@Controller
@RequestMapping("")	// context path
public class FedBoardController {
	private AjaxIntroService ajaxIntroService;
	private IntroFedService introFedService;

	@Autowired
	public void setAjaxIntroService(AjaxIntroService ajaxIntroService) {
		this.ajaxIntroService = ajaxIntroService;
	}
	
	@Autowired
	public void setIntroFedService(IntroFedService introFedService) {
		this.introFedService = introFedService;
	}
	
	public FedBoardController() {
		System.out.println("FedBoardController() 생성");
	}
	
	// 자소서 피드백 게시판
	@RequestMapping("/fedBoard") 
	public String fedBoardPage(Model model) {
		model.addAttribute("list", introFedService.selectResumeInPublic());
		return "mainBoard/fed/fedBoard"; // 문서 명
	}
	
	// 자소서 피드백 뷰
	@RequestMapping("/fedView") 
	public String view(int id, Model model) {
		model.addAttribute("introResult", introFedService.selectFed(id));
		return "mainBoard/fed/fedView"; // 문서 명
	}
	
	// 검색된 자소서 피드백 게시판
	@GetMapping("/fedSearch") 
	public String search(String keyword, Model model) {
		model.addAttribute("list", introFedService.selectResumeByKeyword(keyword));
		return "mainBoard/fed/fedSearch"; // 문서 명
	}
}
