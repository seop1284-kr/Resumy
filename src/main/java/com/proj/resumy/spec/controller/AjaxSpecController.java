package com.proj.resumy.spec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.spec.domain.SpecDTO;
import com.proj.resumy.spec.domain.SpecResult;
import com.proj.resumy.spec.service.SpecService;

@RestController
@RequestMapping("/specAjax")
public class AjaxSpecController {
	@Autowired
	SpecService specService;
	
	public AjaxSpecController() {
		System.out.println("AjaxSpecController() 생성");
	}
	
	// 특정 mid 글 읽기
	@GetMapping("/list/")   // URI:  /myp/history
	public List<SpecDTO> view(Authentication authentication) {
		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//System.out.println("" + userDetails.getUsername());
		List<SpecDTO> list = new ArrayList<>();
		list = specService.view(userDetails.getUsername());
		System.out.println("specList : " +  list.get(0));
		

		return list;
	}
	/*public SpecList view(@PathVariable int mid) {
		List<SpecDTO> list = null;
		
		// message 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {			
			list = specService.view(mid);  
			
			if(list == null || list.size() == 0) {
				message.append("[해당 데이터가 없습니다]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
		}
		
		SpecList result = new SpecList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		return result;		
	}
	*/	
	// 학력추가
		@PostMapping("")  // URI: /myp/history
		public SpecResult writeOk(SpecDTO dto, Authentication authentication) {
			int count = 0;
			// 로그인한 사람의 정보를 담은 객체
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			dto.setUserid(userDetails.getUsername());
			
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				System.out.println("학력 DTO: " + dto.getCat());
				count = specService.write(dto);
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 insert]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			SpecResult result = new SpecResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}
		
		// 글 수정
		@PutMapping("")  // URI: /myp/history
		public SpecResult updateOk(SpecDTO dto, Authentication authentication) {
			int count = 0;
			
			// 로그인한 사람의 정보를 담은 객체
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			dto.setUserid(userDetails.getUsername());
			
			System.out.println("dto 뭔가 : " + dto);
				
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				count = specService.update(dto);
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 update]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			SpecResult result = new SpecResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}

}