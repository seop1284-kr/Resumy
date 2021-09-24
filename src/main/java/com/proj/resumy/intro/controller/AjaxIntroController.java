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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 자소서 작성
	// 자소서 수정(삭제 후 새로 저장)(수정 구현 귀찮)
	@PostMapping("")
	public int write(
			@RequestParam(value = "pub", defaultValue = "false") boolean pub, 
			@RequestParam(value = "fin", defaultValue = "false")boolean fin, 
			String title, String[] question, String[] content, Authentication authentication) {
		int iid = 0;
		System.out.println(pub + " / " + fin);
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
		IntroDTO resume = new IntroDTO();
		resume.setTitle(title);
		resume.setUserid(userDetails.getUsername());
		resume.setPub(pub);
		resume.setFin(fin);
		System.out.println(resume);

		iid = ajaxIntroService.writeResume(resume, question, content);

		return iid;
	}

}
