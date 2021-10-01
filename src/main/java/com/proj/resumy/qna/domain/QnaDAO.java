package com.proj.resumy.qna.domain;

import java.util.List;

// QnaDAO(고객센터 페이지, 고객센터 답글) 노수빈
public interface QnaDAO {
	// 페이징용 SELECT (from : 몇 번째부터, pageRows : 몇 개의 데이터를)
	public List<QnaQDTO> selectFromRow(int from, int pageRows);
	
	// 전체 글 개수
	public int countAll();
	
	// 고객센터 테이블 목록 출력 SELECT
	public abstract List<QnaQDTO> selectQnaQ();
	
	// 고객센터 관리 테이블 목록 출력 SELECT
	public abstract List<QnaADTO> selectQnaA();
	
	// 문의하기 글 작성 <-- DTO
	public abstract int insertQnaQ(QnaQDTO dto);
	
	// 답변 작성 <-- DTO
	// (controller 의 /mng/qna/updateOk.do 에 코드 위치)
	public abstract int insertQnaA(QnaADTO dto);
	
	// 특정 id 문의 내용 읽기
	public abstract QnaQDTO selectByUid(int id);
	
	// 특정 q_id 문의 답글 읽기
	public abstract QnaADTO selectByQid(int id);
	
	// 특정 id 문의 글 수정
	public abstract int updateQnaQ(QnaQDTO dto);
	
	// 특정 id 문의 답변 수정
	public abstract int updateQnaA(QnaADTO dto);
	
	// 특정 q_id 게시물의 답변 상태 수정
	public abstract boolean updateReplyState(QnaQDTO dto);
	
	// 특정 id 문의 글 삭제
	public abstract int deleteByUid(int id);
	
	// 특정 q_id 문의 답글 삭제
	public abstract int deleteByQid(int id);
}
