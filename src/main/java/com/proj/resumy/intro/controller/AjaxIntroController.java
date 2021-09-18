package com.proj.resumy.intro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.intro.domain.IntroConDTO;
import com.proj.resumy.intro.domain.IntroDTO;
import com.proj.resumy.intro.service.AjaxIntroService;

@RestController
@RequestMapping("/resumeAjax")
public class AjaxIntroController {
	private AjaxIntroService ajaxIntroService;

	@Autowired
	public void setAjaxIntroService(AjaxIntroService ajaxIntroService) {
		this.ajaxIntroService = ajaxIntroService;
	}
	
	public AjaxIntroController() {
		System.out.println("AjaxIntroController() 생성");
	}
	
	
	// 완성된 자소서 목록
	@RequestMapping("/finlist")
	public IntroDTO[] finlist(Model model) {
		
		//model.addAttribute("finList", introService.selectNotFinResume());
		
		// test id가 1일 사람
		List<IntroDTO> finList = ajaxIntroService.selectFinResume(1);
		
		IntroDTO [] arr = new IntroDTO[finList.size()];
		return finList.toArray(arr);
	}
	
	// 미완성된 자소서 목록
	@RequestMapping("/notfinlist")
	public IntroDTO[] notfinlist(Model model) {
		
		//model.addAttribute("notFinList", introService.selectNotFinResume());
		
		// test id가 1일 사람
		List<IntroDTO> notFinList = ajaxIntroService.selectNotFinResume(1);

		IntroDTO [] arr = new IntroDTO[notFinList.size()];
		return notFinList.toArray(arr);
	}
	
	// 특정 id 자소서 읽기
	@GetMapping("/{iid}")
	public IntroConDTO[] view(@PathVariable int iid) {
		List<IntroConDTO> list = ajaxIntroService.selectByUid(iid);
		
		IntroConDTO [] arr = new IntroConDTO[list.size()];
		
		return list.toArray(arr);		
	}
}
