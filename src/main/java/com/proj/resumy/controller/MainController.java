package com.proj.resumy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	public MainController() {
		System.out.println("MainController() 생성");
	}
	
	// main page
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		return "view";
	}

}
