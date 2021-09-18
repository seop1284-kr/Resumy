package com.proj.resumy.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.file.domain.FileDTO;
import com.proj.resumy.file.service.FileService;

// AjaxFileController (파일관리) 하병노

@RestController
@RequestMapping("/myp/file")
public class AjaxFileController {
	
	@Autowired
	private FileService fileService;
	
	// 파일목록
//	@GetMapping("/file")
//	public AjaxFileList list() {
//		List<FileDTO> list = null;
//	}
	
	
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	public AjaxFileController() {
		System.out.println("AjaxFileController() 생성");
	}
	
	@RequestMapping("/jsonlist")
	public FileDTO[] list(Model model) {
		model.addAttribute("list", fileService.list());
		List<FileDTO> list = fileService.list();

		FileDTO [] arr = new FileDTO[list.size()];
		return list.toArray(arr);
	}

}
