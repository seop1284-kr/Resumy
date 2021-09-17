package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.service.CareerService;

@Controller
@RequestMapping("/myp/history")
public class CareerController {
	private CareerService careerService;

	@Autowired
	public void setCareerService(CareerService careerService) {
		this.careerService = careerService;
	}
	
	public CareerController() {
		System.out.println("CareerController() 생성");
	}
}