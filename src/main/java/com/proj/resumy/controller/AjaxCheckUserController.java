package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.service.MemberService;

@RestController
@RequestMapping("/checkUserAjax")
public class AjaxCheckUserController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/checkId/{userid}")
	public boolean checkId(@PathVariable String userid) {
		// userid 중복 체크(true: 중복, false: 중복 아님)
		if (memberService.findById(userid) == null) {
			return false;
		}
		return true;
	}
	
}
