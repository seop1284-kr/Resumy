package com.proj.resumy.qna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.MemberDAO;
import com.proj.resumy.qna.domain.QnaADTO;
import com.proj.resumy.qna.domain.QnaDAO;
import com.proj.resumy.qna.domain.QnaQDTO;

//Service 단.
//JSP MVC model2 의 Command 역할 비슷
//  Controller -> Commmand -> DAO

//- Transaction 담당
//Spring
//@Controller -> @Service -> DAO -> JdbcTemplate

// QnaService(고객센터 페이지, 고객센터 답글) 노수빈
@Service
public class QnaService {
	QnaDAO dao;
	MemberDAO memberDao;

	@Autowired
	public void setDao(QnaDAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	public QnaService() {
		;
	}

	// 고객센터 테이블 목록 출력 SELECT
	public List<QnaQDTO> listQnaQ() {
		return dao.selectQnaQ();
	}
	
	// 고객센터 관리 테이블 목록 출력 SELECT
	public List<QnaADTO> listQnaA() {
		return dao.selectQnaA();
	}
	
	// 문의하기 글 작성 <-- DTO
	public int insertQnaQ(QnaQDTO dto) {
		return dao.insertQnaQ(dto);
	}
	
	// 답변 작성 <-- DTO
	// (controller 의 /mng/qna/updateOk.do 에 코드 위치)
	public int insertQnaA(QnaADTO dto) {
		return dao.insertQnaA(dto);
	}

	// 특정 id 문의 내용 읽기
	public QnaQDTO selectByUid(int id) {
		return dao.selectByUid(id);
	}

	// 특정 q_id 문의 답글 읽기
	public QnaADTO selectByQid(int id) {
		return dao.selectByQid(id);
	}
	
	// 특정 id 문의 글 수정
	public int updateQnaQ(QnaQDTO dto) {
		return dao.updateQnaQ(dto);
	}
	
	// 특정 id 문의 답변 수정
	public int updateQnaA(QnaADTO dto) {
		return dao.updateQnaA(dto);
	}

	// 특정 q_id 게시물의 답변 상태 수정
	public boolean updateReplyState(QnaQDTO dto) {
		return dao.updateReplyState(dto);
	}

	// 특정 id 문의 글 삭제
	public int deleteByUid(int id) {
		return dao.deleteByUid(id);
	}
	
	// 특정 q_id 문의 답글 삭제
	public int deleteByQid(int id) {
		return dao.deleteByQid(id);
	}
	
	// 특정 userid 의 작성자 이름 뽑기
	public String findNameByUserId(String userid) {
		return memberDao.findNameByUserId(userid);
	}
}