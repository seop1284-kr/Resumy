package com.proj.resumy.fed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.fed.service.IntroFedService;
import com.proj.resumy.intro.domain.IntroDTO;
import com.proj.resumy.intro.service.AjaxIntroService;

@RestController
@RequestMapping("/mp/fed")
public class AjaxIntroFedController {
	private IntroFedService introFedService;

	@Autowired
	public void setIntroFedService(IntroFedService introFedService) {
		this.introFedService = introFedService;
	}
	
	public AjaxIntroFedController() {
		System.out.println("AjaxIntroFedController() 생성");
	}
	
	@RequestMapping("/jsonlist")
	public IntroFedDTO[] list(Model model) {
		
		model.addAttribute("list", introFedService.list());
		List<IntroFedDTO> list = introFedService.list();

		IntroFedDTO [] arr = new IntroFedDTO[list.size()];
		return list.toArray(arr);
	}
}
