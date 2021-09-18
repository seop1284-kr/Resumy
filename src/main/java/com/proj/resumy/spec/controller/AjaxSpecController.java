package com.proj.resumy.spec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.spec.domin.SpecDTO;
import com.proj.resumy.spec.service.SpecService;

//학력사항 컨트롤러 (김민수)

@RestController
@RequestMapping("/myp/basic")
public class AjaxSpecController {
	private SpecService specService;

	@Autowired
	public void setSpecService(SpecService specService) {
		this.specService = specService;
	}
	
	public AjaxSpecController() {
		System.out.println("AjaxSpecController() 생성");
	}
	
	@RequestMapping("/jsonlist")
	public SpecDTO[] list(Model model) {
		
		model.addAttribute("list", specService.list());
		List<SpecDTO> list = specService.list();

		SpecDTO [] arr = new SpecDTO[list.size()];
		return list.toArray(arr);
	}
}
