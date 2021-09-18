package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.intro.service.AjaxIntroService;

@Controller
@RequestMapping("")
public class MainController {
	
	public MainController() {
		System.out.println("MainController() 생성");
	}
	
	// main page
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		return "index";
	}

}
