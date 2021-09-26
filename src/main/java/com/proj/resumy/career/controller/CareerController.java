package com.proj.resumy.career.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//CareerController(이력 관리 페이지) 김민수
@Controller
@RequestMapping("/myp/historyMng")
public class CareerController {

	public CareerController () {
		System.out.println("CareerController() 생성");
	}
	
	
	@RequestMapping("/{userid}")
	public String resume(Model model) {
		return "/myp/historyMng";
	}
	

}