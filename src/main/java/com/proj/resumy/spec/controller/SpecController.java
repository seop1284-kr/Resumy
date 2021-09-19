package com.proj.resumy.spec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.spec.domain.SpecDTO;
import com.proj.resumy.spec.domain.SpecList;
import com.proj.resumy.spec.domain.SpecResult;
import com.proj.resumy.spec.service.SpecService;

@Controller
@RequestMapping("/myp/history/spec")
public class SpecController {
	@Autowired
	SpecService specService;
	
	// 특정 mid 글 읽기
	@GetMapping("/{mid}")   // URI:  /myp/history/{mid}
	public SpecList view(@PathVariable int mid) {
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
	
	// 글 작성
		@PostMapping("")  // URI: /myp/history
		public SpecResult writeOk(SpecDTO dto) {
			int count = 0;
					
			// message 
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			try {
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
		public SpecResult updateOk(SpecDTO dto) {
			int count = 0;
				
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