package com.proj.resumy.controller;

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
	
	@RequestMapping("/jsonlist")
	public IntroDTO[] list(Model model) {
		
		model.addAttribute("list", introService.list());
		List<IntroDTO> list = introService.list();

		IntroDTO [] arr = new IntroDTO[list.size()];
		return list.toArray(arr);
	}
}
