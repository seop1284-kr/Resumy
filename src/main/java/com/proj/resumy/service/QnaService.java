package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.QnaADTO;
import com.proj.resumy.domain.QnaDAO;
import com.proj.resumy.domain.QnaQDTO;

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

	@Autowired
	public void setDao(QnaDAO dao) {
		this.dao = dao;
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
	public List<QnaQDTO> selectByUid(int uid) {
		return dao.selectByUid(uid);
	}

	// 특정 q_id 문의 답글 읽기
	public List<QnaADTO> selectByQid(int uid) {
		return dao.selectByQid(uid);
	}
	
	// 특정 id 문의 글 수정
	public int update(QnaQDTO dto) {
		return dao.update(dto);
	}

	// 특정 id 문의 글 삭제
	public int deleteByUid(int uid) {
		return dao.deleteByUid(uid);
	}
	
	// 특정 q_id 문의 답글 삭제
	public int deleteByQid(int uid) {
		return dao.deleteByQid(uid);
	}

}
