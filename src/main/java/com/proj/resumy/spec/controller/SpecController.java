package com.proj.resumy.spec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.spec.service.SpecService;

@Controller
@RequestMapping("/myp/basic")
public class SpecController {
	private SpecService specService;

	@Autowired
	public void setIntroService(SpecService specService) {
		this.specService = specService;
	}
	
	public SpecController() {
		System.out.println("SpecController() 생성");
	}
}