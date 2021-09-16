package com.proj.resumy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.domain.IntroDTO;
import com.proj.resumy.service.IntroService;

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
	
//	@RequestMapping("/list")
//	public String list(Model model) {
//		
//		model.addAttribute("list", introService.list());
//
//		return "view";
//	}
}
