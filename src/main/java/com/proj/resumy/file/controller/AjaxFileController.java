package com.proj.resumy.file.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proj.resumy.file.domain.FileDTO;
import com.proj.resumy.file.service.AjaxFileService;
import com.proj.resumy.file.service.FileService;
import com.proj.resumy.intro.domain.IntroDTO;

// AjaxFileController (파일관리) 하병노

@RestController
@RequestMapping("/fileAjax")
public class AjaxFileController {
	private AjaxFileService ajaxFileService;

	@Autowired
	public void setAjaxFileService(AjaxFileService ajaxFileService) {
		this.ajaxFileService = ajaxFileService;
	}
	
	private FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public AjaxFileController() {
		System.out.println("AjaxFileController() 생성");
	}

	// 로그인한 특정 회원(mem_id)의 첨부파일 리스트

	@RequestMapping("/filelist")
	public List<FileDTO> filelist(Model model, Authentication authentication) {

		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<FileDTO> filelist = new ArrayList<>();
		filelist = ajaxFileService.selectByUserID(userDetails.getUsername());

		return filelist;

//		// test용 id가 1인 사람
//		List<FileDTO> filelist = ajaxFileService.selectByMid(1);
//		System.out.println("파일리스트출력" + filelist);
//		FileDTO[] arr = new FileDTO[filelist.size()];
//
//		return filelist.toArray(arr);

	}

//	// 특정 첨부파일 (file_id) 한개 SELECT
//	@RequestMapping("/{mid}{fid}")
//	public FileDTO view(@PathVariable int id) {
//		FileDTO fileinfo = ajaxFileService.selectByFid(id);
//		
//		return null;
//		
//	}

	// 특정 회원(mem_id)의 새파일 업로드
	@PostMapping("")
	public int write(MultipartFile file, String memo, Authentication authentication) {
		int result = 0;
		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		FileDTO fileDTO = new FileDTO();
		
		
		fileDTO = fileService.fileUpload(file, userDetails, memo);
		result = ajaxFileService.insert(fileDTO);		
		
		return result;
	}

	// 특정 파일(file_id) 삭제
	@DeleteMapping("")
	public String delete(int[] id) {
		String result = "fail";
		int count = 0;
		count = ajaxFileService.deleteByIds(id);
		System.out.println(id);
		if (count == 1) {
			result = "success";
		}
		
		return result;
	}

	// 복수개의 특정 파일(file_id)들 삭제
	// TODO

	
	
}
