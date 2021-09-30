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
		;
	}

	// 고객센터 페이지 게시물 목록 : 고객센터 테이블 목록 출력 SELECT
	@RequestMapping("main/qna/board.do")
	public String listQnaQ(Model model) {
		List<QnaQDTO> listQ = qnaService.listQnaQ();
		List<String> listName = this.findNameAll(listQ); // (문의글) 작성자
		
		model.addAttribute("list", listQ); // jsp로 2번 매개변수를 1번 매개변수 이름으로 값을 보냄
		model.addAttribute("listName", listName);

		return "mainBoard/qna/qnaBoard";
	}
	
	// 고객센터 관리 페이지 게시물 목록 : 고객센터 관리 테이블 목록 출력 SELECT
	@RequestMapping("mng/qna/board.do")
	public String listQnaA(Model model) {
		List<QnaQDTO> listQ = qnaService.listQnaQ();
		List<QnaADTO> listA = qnaService.listQnaA();

		model.addAttribute("listQ", listQ);
		model.addAttribute("listA", listA);
		
		return "mng/qna/qnaMng";
	}

	// 고객센터 페이지 - 문의하기 : 문의하기 글 작성 <-- DTO
	@RequestMapping("main/qna/write.do")
	public String write(Model model) {
		return "mainBoard/qna/qnaWrite";
	}
	
	// 고객센터 페이지 - 문의글 : DB에 반영하기
	@RequestMapping("main/qna/writeOk.do")
	public String writeOk3(String subject, String content, Model model, Authentication authentication) {
		QnaQDTO dto = new QnaQDTO();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // 현재 로그인한 계정의 정보를 담은 객체

		dto.setSubject(subject);
		dto.setContent(content);
		dto.setUserid(userDetails.getUsername());
		
		model.addAttribute("result", qnaService.insertQnaQ(dto));
		return "redirect:/main/qna/board.do";
	}
	
	// 고객센터 답글 페이지 - 답변 DB에 반영하기
	@PostMapping("mng/qna/writeOk.do")
	public String writeOk2(String subject, String content, Model model) {
		QnaQDTO dto = new QnaQDTO();
		
		dto.setSubject(subject);
		dto.setContent(content);
		
		model.addAttribute("result", qnaService.insertQnaQ(dto));
		
		return "redirect:/mng/qna/board.do";
	}

	// 고객센터 페이지 - 글 읽기 : 특정 id 문의 내용 읽기 (답글도 보여야 됨)
	@GetMapping("main/qna/view.do")
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
	@RequestMapping("main/qna/update.do")
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
		if (qdto.getUserid().equals(userDetails.getUsername()) && ( adto == null )) {
			model.addAttribute("result", 0);
			
			return "mainBoard/qna/qnaQUpdate"; // view 에서 글 수정 가능
		}
		
		// ( 현재 로그인한 계정 != 문의글 작성한 계정 ) || ( 문의글에 답글 있음 )
		model.addAttribute("result", -1);

		return "mainBoard/qna/qnaQUpdate"; // view 에서 경고창 팝업
	}

	// 고객센터 페이지 - 글 수정 사항 DB에 반영하기
	@PostMapping("main/qna/updateOk.do")
	public String updateQnaQOk(String id, String content, Model model) {
		QnaQDTO dto = new QnaQDTO();
		
		dto.setId(Integer.parseInt(id));
		dto.setContent(content);
		
		model.addAttribute("result", qnaService.updateQnaQ(dto));
		
		return "mainBoard/qna/qnaQUpdateOk";
	}
	
	// 고객센터 관리 페이지 - 답변 update (또는 insert) DB에 반영하기
	// 특정 q_id 게시물의 답변 상태 true 로 수정
	@PostMapping("mng/qna/updateOk.do")
	public String updateQnaAOk(QnaADTO dto, Model model) {
		boolean chk = true;
		
		// response
		if(qnaService.selectByQid(dto.getId()) == null) { // 해당 게시물에 대한 답변 존재 여부 확인
			chk = false;
		}
		
		model.addAttribute("result", qnaService.updateQnaA(chk, dto)); 
		
		return "mng/qna/qnaAUpdateOk";
	}

	// 고객센터 페이지 - 글 삭제 : 특정 id 문의 글 삭제
	// (로그인 계정과 작성자가 일치하면 삭제가능)
	@GetMapping("main/qna/deleteOk.do")
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
	
	// 고객센터 관리 페이지 - 삭제 : 다수의 문의글 삭제
	@GetMapping("mng/qna/qnaQDeleteOk.do")
	public String qDeleteOk2(Model model, int... id) {
		int temp = 0;
		int result = 0;
		
		for (int i : id) {
			temp = qnaService.deleteByUid(i);
			if (temp == 1) {
				result++;
			}
		}
		
		model.addAttribute("result", result);
		
		return "mng/qna/qnaQDeleteOk";
	}

	// 고객센터 관리 페이지 - 삭제 : 특정 q_id 문의 답글 삭제
	// 특정 q_id 게시물의 답변 상태 false 로 수정
	@GetMapping("mng/qna/qnaADeleteOk.do")
	public String aDeleteOk(int id, Model model) {
		model.addAttribute("result", qnaService.deleteByQid(id));
		
		return "redirect:/mng/qna/board.do";
	}
	
	// 모든 게시물의 작성자 이름 뽑기
	public List<String> findNameAll(List<QnaQDTO> list) {
		List<String> listName = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			listName.add(qnaService.findNameByUserId(list.get(i).getUserid()));
		}
		
		return listName;
	}
	
	// userid 로부터 사용자 이름 뽑기
	public String findNameByUserId(String userid) {
		String name = qnaService.findNameByUserId(userid);
		return name;
	}

}