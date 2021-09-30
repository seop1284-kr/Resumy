package com.proj.resumy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 관리자 페이지 컨트롤러
@Controller
@RequestMapping("/mng")
public class MngpageController {

	public MngpageController() {
		System.out.println("MngpageController() 생성");
	}
	
	// 자소서 피드백 관리자 페이지
	@RequestMapping("fed")
	public String mngFed(Model model) {
		return "mng/mngFed";
	}
	
	// 회원 관리자 페이지
	@RequestMapping("member")
	public String mngMember(Model model) {
		return "mng/mngMember";
	}
	
	// mng page
	@RequestMapping("")
	public String mngPage(String menu, Model model) {
		if (menu == null) {
			model.addAttribute("menu", "fed");
		} else {
			model.addAttribute("menu", menu);
		}
		return "mng/mngpage";
	}
}
