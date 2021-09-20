package com.proj.resumy.intro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.intro.domain.IntroConDTO;
import com.proj.resumy.intro.domain.IntroDTO;
import com.proj.resumy.intro.domain.IntroResult;
import com.proj.resumy.intro.service.AjaxIntroService;

@RestController
@RequestMapping("/resumeAjax")
public class AjaxIntroController {
	private AjaxIntroService ajaxIntroService;

	@Autowired
	public void setAjaxIntroService(AjaxIntroService ajaxIntroService) {
		this.ajaxIntroService = ajaxIntroService;
	}
	
	public AjaxIntroController() {
		System.out.println("AjaxIntroController() 생성");
	}
	
	// 자소서 목록 (list)
	@RequestMapping("/list")
	public List<IntroDTO> list(Model model, Authentication authentication) {
		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<IntroDTO> list = new ArrayList<>();
		list = ajaxIntroService.selectResume(userDetails.getUsername());

		return list;
	}
	
	// 특정 id 자소서 읽기 (view)
	@GetMapping("/{iid}")
	public IntroResult view(@PathVariable int iid) {
		IntroDTO intro = ajaxIntroService.selectResumeById(iid);
		List<IntroConDTO> list = ajaxIntroService.selectConByIid(iid);
		
		// 자소서 제목 + 내용 객체
		IntroResult introViewResult = new IntroResult();
		introViewResult.setConList(list);
		introViewResult.setIntro(intro);
		
		return introViewResult;		
	}
	
	// 특정 id 자소서 삭제 (delete)
	@DeleteMapping("")
	public String delete(int id) {
		String result = "fail";
		int count = 0;
		count = ajaxIntroService.deleteResumeById(id);
		
		if (count == 1) {
			result = "success";
		}
		
		return result;
	}
	
	// 특정 id 자소서 수정 (update)
	// 글 수정
	@PutMapping("")
	public String update(IntroDTO introDto, IntroConDTO introConDto) {
		String result = "fail";
		int count = 0;
		count += ajaxIntroService.updateResumeById(introDto);

		if (count == 1) {
			result = "success";
		}
		
		return result;
	}
	
	
	// 자소서 작성
//	@PostMapping("")
//	public IntroResult writeOk(IntroConDTO introConDTO) {
//		int count = 0;
//				
//		// message 
//		StringBuffer message = new StringBuffer();
//		String status = "FAIL";
//		
//		try {
//			count = ajaxIntroService.write(introConDTO);
//			if(count == 0) {
//				message.append("[트랜잭션 실패 : 0 insert]");
//			} else {
//				status = "OK";
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
//		}
//		
//		IntroResult result = new IntroResult();
//
//		return result;
//	}
}
