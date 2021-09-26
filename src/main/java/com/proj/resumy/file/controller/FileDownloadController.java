package com.proj.resumy.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	// 파일 저장 위치 지정
	private static String CURR_FILE_REPO_PATH = "C:\\DevRoot\\Resumy\\Resumy\\files";
	
	// 다운로드할 파일 이름 전달
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response)throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = CURR_FILE_REPO_PATH + "\\" + imageFileName;
		File file = new File(downFile); // 다운로드할 파일 객체 생성
		
		response.setHeader("Cache-Control", "no-cache");
		//헤더에 파일 이름 설정
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024*8]; // 버퍼를 사용해 한번에 8Kb 씩 브라우저로 전송
		while (true) {
			int count = in.read(buffer);
			if (count == -1) break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
		
	}
}
