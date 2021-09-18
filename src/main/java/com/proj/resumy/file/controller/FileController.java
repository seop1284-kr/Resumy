package com.proj.resumy.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//AjaxFileController (파일관리) 하병노

@Controller
@RequestMapping("/myp")
public class FileController {
	
	@RequestMapping("/file")
	public String file() {
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
