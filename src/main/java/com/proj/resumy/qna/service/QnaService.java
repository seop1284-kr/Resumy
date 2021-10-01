package com.proj.resumy.qna.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.MemberDAO;
import com.proj.resumy.qna.domain.QnaADTO;
import com.proj.resumy.qna.domain.QnaDAO;
import com.proj.resumy.qna.domain.QnaDTO;
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

	// 페이징용 SELECT (from : 몇 번째부터, pageRows : 몇 개의 데이터를)
	public List<QnaDTO> list(int from, int pageRows) {
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		List<QnaQDTO> listQ = dao.selectFromRow(from, pageRows);
		
		// QnaDTO 에 QnaQDTO 넣어서 반환
		for (int i = 0; i < listQ.size(); i++) {
			QnaDTO dto = new QnaDTO();
			dto.setQdto(listQ.get(i));
			
			list.add(dto);
		}
		
		return list;
	}
	
	// 전체 글 개수
	public int count() {
		return dao.countAll();
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
	
	// 특정 id 문의 답변 수정/삽입
	// 특정 q_id 게시물의 답변 상태 true 로 수정
	@Transactional
	public int updateQnaA(boolean chk, QnaADTO dto) {
		int result = 0;
		QnaQDTO qdto = new QnaQDTO();
		
		// 고객센터 답변 테이블에 답변 등록
		if(!chk) { // 특정 게시물에 답변이 없을 때
			result = dao.insertQnaA(dto); // 답변 insert
		} else {
			result = dao.updateQnaA(dto); // 답변 update	
		}
		
		// 고겍센터 테이블의 답변상태 true로 업데이트
		qdto.setId(dto.getId());
		qdto.setReplyState(true);
		
		dao.updateReplyState(qdto);
		
		return result;
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
	// 특정 q_id 게시물의 답변 상태 false 로 수정
	@Transactional
	public int deleteByQid(int id) {
		int result = 0;
		QnaQDTO qdto = new QnaQDTO();

		// 답글 삭제
		result = dao.deleteByQid(id);
		
		// 답변 상태 업데이트
		qdto.setId(id);
		qdto.setReplyState(false);
		
		dao.updateReplyState(qdto);
		
		return result;
	}
	
	// 모든 게시물의 작성자 이름 뽑기
	public List<String> findNameAll(List<QnaQDTO> list) {
		List<String> listName = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			listName.add(memberDao.findNameByUserId(list.get(i).getUserid()));
		}
		
		return listName;
	}
	
	// 특정 userid 의 작성자 이름 뽑기
	public String findNameByUserId(String userid) {
		return memberDao.findNameByUserId(userid);
	}
}