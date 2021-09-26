package com.proj.resumy.career.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.career.domain.CareerDTO;
import com.proj.resumy.career.domain.CareerResult;
import com.proj.resumy.career.service.CareerService;
import com.proj.resumy.intro.service.AjaxIntroService;

@RestController
@RequestMapping("/careerAjax")
public class AjaxCareerController {
	@Autowired
	CareerService careerService;
	Authentication authentication;
	
	// 이력관리 목록 (list)
	@GetMapping("/list/")
	public List<CareerDTO> view(Authentication authentication) {
		
		// 로그인한 사람의 정보를 담은 객체
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//System.out.println("" + userDetails.getUsername());
		List<CareerDTO> list = new ArrayList<>();
		list = careerService.view(userDetails.getUsername());
		System.out.println("list : " +  list.get(0));
		

		return list;
	}
	
	
	
	// 특정 mid 글 읽기
//	@GetMapping("/list/{mid}")   // URI:  /myp/history/{mid}
//	public String view(@PathVariable int mid) {
//		List<CareerDTO> list = null;
//		System.out.println("Career 입력");
//		// message 
//		StringBuffer message = new StringBuffer();
//		String status = "FAIL";
//		
//		try {			
//			list = careerService.view(mid);  
//			//System.out.println("Career 리스트에 들어옴?" );
//			//System.out.println("list 리스트에 들어옴?" + list.size());
//			if(list == null || list.size() == 0) {
//				message.append("[해당 데이터가 없습니다]");
//			} else {
//				status = "OK";
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
//		}
//		
//		CareerList result = new CareerList();
//		
//		result.setStatus(status);
//		result.setMessage(message.toString());
//		
//		if(list != null) {
//			result.setCount(list.size());
//			result.setList(list);
//		}
//		
//		return "/myp/historyMng";	
//		
//		
//		
//		
//	}
	
	// 글 작성
		@PostMapping("")  // URI: /myp/history
		public CareerResult writeOk(CareerDTO dto) {
			int count = 0;
					
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				System.out.println("$$ insert dto Userid : "+ dto.getUserid());
				count = careerService.write(dto);
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 insert]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			CareerResult result = new CareerResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}
		
		// 글 수정
		@PutMapping("")  // URI: /myp/history
		public CareerResult updateOk(CareerDTO dto) {
			int count = 0;
				
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				count = careerService.update(dto);
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 update]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			CareerResult result = new CareerResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}

		// 글 삭제
		@DeleteMapping("")  // URI: /myp/history
		public CareerResult deleteOk(CareerDTO dto) {
			int count = 0;
			
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
				System.out.println("$$ delete dto Userid : "+ dto.getUserid());
				if(dto != null) {
					count = careerService.delete(dto);
					status = "OK";
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러: " + e.getMessage() + "]");
			}
			
			CareerResult result = new CareerResult();
			result.setStatus(status);
			result.setMessage(message.toString());
			result.setCount(count);
			return result;
		}
}