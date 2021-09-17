package com.proj.resumy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.service.FileService;
import com.proj.resumy.service.IntroService;

@Controller
@RequestMapping("/myp/file")
public class FileController {
	private FileService fileService;
	
	@Autowired
	public void setFileService(IntroService introService) {
		this.fileService = fileService;
	}
	
	public FileController() {
		System.out.println("FileController() 생성");
	}
	
}
