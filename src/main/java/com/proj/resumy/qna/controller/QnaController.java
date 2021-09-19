package com.proj.resumy.qna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.resumy.qna.domain.QnaADTO;
import com.proj.resumy.qna.domain.QnaQDTO;
import com.proj.resumy.qna.service.QnaService;

// QnaController(고객센터 페이지) 노수빈
@Controller
@RequestMapping("")
public class QnaController {
	private QnaService qnaService;

	@Autowired
	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	public QnaController() {
		System.out.println("QnaController() 생성");
	}
	
	// 고객센터 페이지 게시물 목록 : 고객센터 페이지 목록 출력 SELECT
	@RequestMapping("/qnaBoard.do")
	public String list(Model model) {
		List<QnaQDTO> list =  qnaService.list();
		List<String> listName = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			listName.add(qnaService.findNameByUserId(list.get(i).getUserid()));
		}
		model.addAttribute("list", list); // jsp로 2번 매개변수를 1번 매개변수 이름으로 값을 보냄
		model.addAttribute("listName", listName);
		
		return "mainBoard/qna/qnaBoard";
	}
	
	// 고객센터 페이지 - 문의하기 : 문의하기 글 작성 <-- DTO
	@RequestMapping("/qnaWrite.do")
	public String write(Model model) {
		return "mainBoard/qna/qnaWrite";
	}
	
	// 고객센터 페이지 - 글 읽기 : 특정 id 문의 내용 읽기 (답글도 보여야 됨)
	@GetMapping("/qnaView.do")
	public String view(int id, Model model) {
		List<QnaQDTO> qlist =  qnaService.selectByUid(id);
		String userName = qnaService.findNameByUserId(qlist.get(0).getUserid());
		
		model.addAttribute("qlist", qlist);
		model.addAttribute("userName", userName);
		model.addAttribute("alist", qnaService.selectByQid(id));
		return "mainBoard/qna/qnaView";
	}
	
	// 고객센터 페이지 - 글 읽기 - 수정 : 특정 id 문의 글 수정
	@RequestMapping("/qnaUpdate.do")
	public String update(int id, Model model) {
		List<QnaQDTO> qlist =  qnaService.selectByUid(id);
		String userName = qnaService.findNameByUserId(qlist.get(0).getUserid());
		
		model.addAttribute("qlist", qlist);
		model.addAttribute("userName", userName);
		return "mainBoard/qna/qnaUpdate";
	}
	
	// 고객센터 페이지 - 글 읽기 - 삭제 : 특정 id 문의 글 삭제
	@GetMapping("/qnaQDeleteOk.do")
	public String qDeleteOk(int id, Model model) {
		model.addAttribute("result", qnaService.deleteByUid(id));
		return "mainBoard/qna/qnaQdeleteOk";		
	}
	
	// 고센터 관리 페이지 - 삭제 : 특정 q_id 문의 답글 삭제
	@GetMapping("/qnaADeleteOk.do")
	public String aDeleteOk(int id, Model model) {
		model.addAttribute("result", qnaService.deleteByQid(id));
		return "mainBoard/qna/qnaAdeleteOk";		
	}
	
}