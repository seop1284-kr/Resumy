package com.proj.resumy.mng.fed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.intro.domain.IntroResult;
import com.proj.resumy.mng.fed.domain.AjaxMngFedList;
import com.proj.resumy.mng.fed.domain.AjaxMngFedResult;
import com.proj.resumy.mng.fed.domain.MngFedResult;
import com.proj.resumy.mng.fed.service.AjaxMngFedService;

@RestController
@RequestMapping("/AjaxMngFed")
public class AjaxMngFedController {
	
	@Autowired
	AjaxMngFedService ajaxMngFedService;
	
	// 글 목록 페이징
	@GetMapping("/{page}/{pageRows}")    // URI : /mng/fed/{page}/{pageRows}
	public AjaxMngFedList list(
			@PathVariable int page, 
			@PathVariable int pageRows) {
		
		List<MngFedResult> list = null;
		
		// message 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 페이징 관련 세팅 값들
		//page : 현재 페이지
		//pageRows : 한 '페이지'에 몇개의 글을 리스트 할것인가?
		int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가? 
		int totalCnt = 0;  // 글은 총 몇개인가?
		
		
		try {			
			// 글 전체 개수 구하기
			totalCnt = ajaxMngFedService.count();
			
			// 총 몇페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from: 몇번째 row 부터?
			int from = (page - 1) * pageRows;  // MySQL 의 Limit 는 0-base 
			
			list = ajaxMngFedService.list(from, pageRows);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
		}
		
		AjaxMngFedList result = new AjaxMngFedList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setMngFedResultList(list);
		}
		
		result.setPage(page);
		result.setTotalPage(totalPage);
		result.setWritePages(writePages);
		result.setPageRows(pageRows);
		result.setTotalCnt(totalCnt);
		
		return result;
	}
	
	// 글 삭제
	@DeleteMapping("")
	public AjaxMngFedResult deleteOk(int [] uid) {
		System.out.println("hello");
		int count = 0;
		
		// message 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			if(uid != null) {
				count = ajaxMngFedService.deleteByUid(uid);
				status = "OK";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
		}
		
		AjaxMngFedResult result = new AjaxMngFedResult();
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		return result;
	}
	
}
