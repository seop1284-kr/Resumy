package com.proj.resumy.qna.domain;

import java.util.List;

import com.proj.resumy.domain.MemberDAO;

// QnaDAO(고객센터 페이지, 고객센터 답글) 노수빈
public interface QnaDAO {
	// 고객센터 페이지 목록 출력 SELECT
	public abstract List<QnaQDTO> select();
	
	// 문의하기 글 작성 <-- DTO
	public abstract int insert(QnaQDTO dto);
	
	// 특정 id 문의 내용 읽기
	public abstract QnaQDTO selectByUid(int id);
	
	// 특정 q_id 문의 답글 읽기
	public abstract QnaADTO selectByQid(int id);
	
	// 특정 id 문의 글 수정
	public abstract int update(QnaQDTO dto);
	
	// 특정 id 문의 글 삭제
	public abstract int deleteByUid(int id);
	
	// 특정 q_id 문의 답글 삭제
	public abstract int deleteByQid(int id);
}
