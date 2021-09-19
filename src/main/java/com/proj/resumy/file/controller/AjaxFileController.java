package com.proj.resumy.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.file.domain.FileDTO;
import com.proj.resumy.file.service.AjaxFileService;

// AjaxFileController (파일관리) 하병노

@RestController
@RequestMapping("/fileAjax")
public class AjaxFileController {
	private AjaxFileService ajaxFileService;
	
	@Autowired
	public void setAjaxFileService(AjaxFileService ajaxFileService) {
		this.ajaxFileService = ajaxFileService;
	}

	public AjaxFileController() {
		System.out.println("AjaxFileController() 생성");
	}
	
	// 특정 유저의 첨부된 파일 리스트
	
	@RequestMapping("/filelist")
	public FileDTO[] filelist(Model model) {
		
		// test용 id가 1인 사람
		List<FileDTO> filelist  = ajaxFileService.selectByMid(1);
		
		FileDTO [] arr = new FileDTO[filelist.size()];
		
		return filelist.toArray(arr);
		
	}
	
	
	
	
	
}
