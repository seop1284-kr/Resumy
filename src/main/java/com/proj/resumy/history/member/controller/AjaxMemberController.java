package com.proj.resumy.history.member.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.domain.MemberDTO;
import com.proj.resumy.domain.MemberResult;
import com.proj.resumy.service.MemberService;

@RestController
@RequestMapping("/memberAjax")
public class AjaxMemberController {
	@Autowired
	MemberService memberService;
	Authentication authentication;
	
	// 이력관리(경력사항) 목록 (list)
	@GetMapping("/list/")
	public List<MemberDTO> view(Authentication authentication) {
		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("" + userDetails.getUsername());
		List<MemberDTO> list = new ArrayList<>();
		MemberDTO listDto = null;
		listDto = memberService.findById(userDetails.getUsername());
		list.add(listDto);
		System.out.println("list : " +  list.get(0));

		return list;
	}
	
		// 글 수정
		@PutMapping("")  // URI: /myp/history
		public MemberResult updateOk(MemberDTO dto, Authentication authentication) {
			int count = 0;
			// 로그인한 사람의 정보를 담은 객체
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			dto.setUserid(userDetails.getUsername());
				
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				count = memberService.update(dto);
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 update]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			MemberResult result = new MemberResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}

}