package com.proj.resumy.qna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		List<QnaQDTO> list = qnaService.list();
		List<String> listName = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
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
		QnaQDTO qdto = qnaService.selectByUid(id);
		String userName = qnaService.findNameByUserId(qdto.getUserid());

		model.addAttribute("qdto", qdto);
		model.addAttribute("userName", userName);
		model.addAttribute("adto", qnaService.selectByQid(id));
		return "mainBoard/qna/qnaView";
	}

	// 고객센터 페이지 - 글 수정 : 특정 id 문의 글 수정
	// (로그인 계정과 작성자가 일치하고 답글이 없어야 수정가능)
	@RequestMapping("/qnaUpdate.do")
	public String update(int id, Model model, Authentication authentication) {
		// request
		QnaQDTO qdto = qnaService.selectByUid(id);
		QnaADTO adto = qnaService.selectByQid(id);
		String userName = qnaService.findNameByUserId(qdto.getUserid());
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // 현재 로그인한 계정의 정보를 담은 객체

		// response (리턴문까지 전부 response)
		model.addAttribute("qdto", qdto);
		model.addAttribute("userName", userName);
		
		// ( 현재 로그인한 계정 == 문의글 작성한 계정 ) && ( 문의글에 답글 없음 )
		if (qdto.getUserid().equals(userDetails.getUsername()) && ( adto.getReply().isEmpty() || adto.getReply() == null )) {
			model.addAttribute("result", 0);
			
			return "mainBoard/qna/qnaUpdate"; // view 에서 글 수정 가능
		}
		
		// ( 현재 로그인한 계정 != 문의글 작성한 계정 ) || ( 문의글에 답글 있음 )
		model.addAttribute("result", -1);

		return "mainBoard/qna/qnaUpdate"; // view 에서 경고창 팝업
	}

	// 고객센터 페이지 - 글 수정 사항 DB에 반영하기
	@PostMapping("/qnaUpdateOk.do")
	public String updateOk(String id, String content, Model model) {
		QnaQDTO dto = new QnaQDTO();
		dto.setId(Integer.parseInt(id));
		dto.setContent(content);
		
		model.addAttribute("result", qnaService.update(dto));
		return "mainBoard/qna/qnaUpdateOk";
	}

	// 고객센터 페이지 - 글 삭제 : 특정 id 문의 글 삭제
	// (로그인 계정과 작성자가 일치하면 삭제가능)
	@GetMapping("/qnaQDeleteOk.do")
	public String qDeleteOk(int id, Model model, Authentication authentication) {
		// request
		QnaQDTO qdto = qnaService.selectByUid(id);	
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // 현재 로그인한 계정의 정보를 담은 객체

		// response (리턴문까지 전부 response)
		// 현재 로그인한 계정 == 문의글 작성한 계정
		if (qdto.getUserid().equals(userDetails.getUsername())) {
			model.addAttribute("result", qnaService.deleteByUid(id));
			
			return "mainBoard/qna/qnaQDeleteOk"; // view 에서 글 삭제 완료 팝업
		}
		
		model.addAttribute("result", -1);
		return "mainBoard/qna/qnaQDeleteOk"; // view 에서 글 삭제 불가 팝업
	}

	// 고객센터 관리 페이지 - 삭제 : 특정 q_id 문의 답글 삭제
	@GetMapping("/qnaADeleteOk.do")
	public String aDeleteOk(int id, Model model) {
		model.addAttribute("result", qnaService.deleteByQid(id));
		return "mainBoard/qna/qnaAdeleteOk";
	}

}