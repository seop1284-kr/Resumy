package com.proj.resumy.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//AjaxFileController (파일관리) 하병노

@Controller
@RequestMapping("/myp")
public class FileController {
	
	// 파일 관리 페이지
	@RequestMapping("/file")
	public String file(Model model) {
		return "myp/file";
	}
	
	
//	
//	private FileService fileService;
//	
//	@Autowired
//	public void setFileService(FileService fileService) {
//		this.fileService = fileService;
//	}
//	
//	public FileController() {
//		System.out.println("FileController() 생성");
//	}
	
}
