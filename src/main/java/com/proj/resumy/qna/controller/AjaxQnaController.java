package com.proj.resumy.qna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.resumy.qna.domain.AjaxQnaPaging;
import com.proj.resumy.qna.domain.QnaADTO;
import com.proj.resumy.qna.domain.QnaDTO;
import com.proj.resumy.qna.domain.QnaQDTO;
import com.proj.resumy.qna.service.QnaService;

// AjaxQnaController(고객센터 페이지, 고객센터 관리자 페이지) 노수빈
@RestController
@RequestMapping("/AjaxQnaBoard")
public class AjaxQnaController {
	
	private QnaService qnaService;
	
	@Autowired
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	public AjaxQnaController() {
		;
	}
	
	// 글 목록 페이징
	@GetMapping("/{page}/{pageRows}")
	public AjaxQnaPaging list(@PathVariable int page, @PathVariable int pageRows) {
		// 페이징 될 데이터
		List<QnaDTO> list = null;
		// 처리 결과 메세지
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		// 페이징 관련 세팅
		AjaxQnaPaging paging = new AjaxQnaPaging();
		int totalCnt = 0; // 글은 총 몇개인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가? 
		int from = 0; // 페이징이 시작되는 데이터의 번호
		int writePage = 10; // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		
		try {
			totalCnt = qnaService.count();
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			from = (page - 1) * pageRows;
			
			// list 객체에 QnaQDTO 객체 삽입
			list = qnaService.list(from, pageRows);
			
			// list 객체에 name 삽입
			for (int i = 0; i < list.size(); i++) {// (문의글) 작성자
				String userid = list.get(i).getQdto().getUserid();
				String name = qnaService.findNameByUserId(userid);
				list.get(i).setName(name);
			}
			
			if (list == null || list.isEmpty()) {
				message.append("[데이터가 없습니다]");
			} else {
				status = "OK";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error: " + e.getCause() + "]");
		}

		paging.setStatus(status);
		paging.setMessage(message.toString());
		
		if(list != null) {
			paging.setCount(list.size());
			paging.setList(list);
		}
		
		paging.setPage(page);
		paging.setPageRows(pageRows);
		paging.setTotalCnt(totalCnt);
		paging.setTotalPage(totalPage);
		paging.setWritePage(writePage);
		paging.setList(list);
		
		return paging;
	}
	
	// 글 하나에 대하여 모든 필드 가져옴 : 고객센터 관리자 페이지
	@GetMapping("qnaAjax/list/{id}")
	public QnaDTO list(@PathVariable int id, Model model) {
		QnaDTO dto = new QnaDTO();
		
		// 특정 id 의 QnaQDTO
		QnaQDTO qdto = qnaService.selectByUid(id);
		// 특정 id 의 문의글 작성자 이름
		String name = qnaService.findNameByUserId(qdto.getUserid());
		// 특정 id 의 QnaADTO
		QnaADTO adto = qnaService.selectByQid(id);
		
		dto.setQdto(qdto);
		dto.setName(name);
		dto.setAdto(adto);
		
		return dto;
	}
	
}
