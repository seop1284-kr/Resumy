package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.domain.NewsInfo;
import com.proj.resumy.domain.RecruitInfo;
import com.proj.resumy.service.APIService;

import retrofit2.Response;

@RestController
@RequestMapping("/indexAjax")
public class AjaxAPIController {
	
	@Autowired
	APIService commonService;
	
	@PostMapping("")
	public RecruitInfo test(Model model) throws Exception {
		Response<RecruitInfo> response = commonService.recruitAPI();
		return response.body();
	}
	
	@PostMapping("/news")
	public NewsInfo test2(Model model, String keyword) throws Exception {
		
		System.out.println(keyword);
		Response<NewsInfo> response = commonService.newsAPI(keyword);
		return response.body();
	}
}
