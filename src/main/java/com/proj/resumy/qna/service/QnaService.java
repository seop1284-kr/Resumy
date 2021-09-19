package com.proj.resumy.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.MemberDAO;
import com.proj.resumy.domain.MemberDTO;
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
		System.out.println("QnaService() 생성");

	}

	// 고객센터 페이지 목록 출력 SELECT
	public List<QnaQDTO> list() {
		return dao.select();
	}
	
	// 문의하기 글 작성 <-- DTO
	public int write(QnaQDTO dto) {
		return dao.insert(dto);
	}

	// 특정 id 문의 내용 읽기
	public List<QnaQDTO> selectByUid(int id) {
		return dao.selectByUid(id);
	}

	// 특정 q_id 문의 답글 읽기
	public List<QnaADTO> selectByQid(int id) {
		return dao.selectByQid(id);
	}
	
	// 특정 id 문의 글 수정
	public int update(QnaQDTO dto) {
		return dao.update(dto);
	}

	// 특정 id 문의 글 삭제
	public int deleteByUid(int id) {
		return dao.deleteByUid(id);
	}
	
	// 특정 q_id 문의 답글 삭제
	public int deleteByQid(int id) {
		return dao.deleteByQid(id);
	}

	// 특정 userid 를 이용해서 회원정보 가져오기
	public String findNameByUserId(String userid) {
		return memberDao.findNameByUserId(userid);
	}

}
