package com.proj.resumy.intro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.domain.IntroDTO;
import com.proj.resumy.service.IntroService;

@RestController
@RequestMapping("/myp/resume")
public class AjaxIntroController {
	private IntroService introService;

	@Autowired
	public void setIntroService(IntroService introService) {
		this.introService = introService;
	}
	
	public AjaxIntroController() {
		System.out.println("AjaxIntroController() 생성");
	}
	
	@RequestMapping("/finlist")
	public IntroDTO[] finlist(Model model) {
		
		//model.addAttribute("finList", introService.selectNotFinResume());
		
		// test id가 1일 사람
		List<IntroDTO> finList = introService.selectFinResume(1);
		
		IntroDTO [] arr = new IntroDTO[finList.size()];
		return finList.toArray(arr);
	}
	
	@RequestMapping("/notfinlist")
	public IntroDTO[] notfinlist(Model model) {
		
		//model.addAttribute("notFinList", introService.selectNotFinResume());
		
		// test id가 1일 사람
		List<IntroDTO> notFinList = introService.selectNotFinResume(1);

		IntroDTO [] arr = new IntroDTO[notFinList.size()];
		return notFinList.toArray(arr);
	}
}
