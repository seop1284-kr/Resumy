package com.proj.resumy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.service.MailService;
import com.proj.resumy.service.MemberService;

@RestController
@RequestMapping("/checkUserAjax")
public class AjaxCheckUserController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/checkId/{userid}")
	public boolean checkId(@PathVariable String userid) {
		// userid 중복 체크(true: 중복아님, false: 중복)
		if (memberService.findById(userid) == null) {
			return true;
		}
		return false;
	}
	
	// 이메일 인증
	@PostMapping("/sendCode")
	private int sendEmail(HttpServletRequest request, String userEmail) {
		System.out.println(userEmail);
		HttpSession session = request.getSession();
				
		mailService.mailSend(session, userEmail);
		int result = 1;
		return result;
	}
	
	@PostMapping("/checkCode")
	private boolean emailCertification(HttpServletRequest request, String userEmail, String inputCode) {
		System.out.println(inputCode + "/" + userEmail);
		HttpSession session = request.getSession();
		boolean result = mailService.emailCertification(session, userEmail, Integer.parseInt(inputCode));
		return result;
	}
	
}
