package com.proj.resumy.career.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.career.domin.CareerDTO;
import com.proj.resumy.career.service.CareerService;


// 경력사항 컨트롤러 (김민수)
@RestController
@RequestMapping("/myp/history")
public class AjaxCareerController {
	private CareerService careerService;

	@Autowired
	public void setCareerService(CareerService careerService) {
		this.careerService = careerService;
	}
	
	public AjaxCareerController() {
		System.out.println("AjaxCareerController() 생성");
	}
	
	@RequestMapping("/jsonlist")
	public CareerDTO[] list(Model model) {
		
		model.addAttribute("list", careerService.list());
		List<CareerDTO> list = careerService.list();

		CareerDTO [] arr = new CareerDTO[list.size()];
		return list.toArray(arr);
	}
}
