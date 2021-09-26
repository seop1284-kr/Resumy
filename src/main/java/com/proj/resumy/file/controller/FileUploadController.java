package com.proj.resumy.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {
	// 파일 저장 위치 지정
	private static final String CURR_FILE_REPO_PATH = "C:\\DevRoot\\Resumy\\Resumy\\files";
	
	@RequestMapping(value="/form")
	public String form() {		//업로드 창인 uploadFrom.jsp를 반환
		return "uploadForm";
	}
}
