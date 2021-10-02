package com.proj.resumy.fed.domain;

import java.util.List;

// 자소서 피드백 DAO 인터페이스 김진섭
public interface IntroFedDAO {
	// 자소서 피드백 SELECT
	public abstract List<IntroFedDTO> select();
	
	// 특정 자조서 id의 자소서 피드백 내용 읽기
	public abstract List<IntroFedDTO> selectByIid(int iid);
		
	// 피드백 작성 <-- DTO
	public abstract int insertFed(IntroFedDTO dto);
	
	// 피드백 아이디로 피드백 찾기
	public abstract IntroFedDTO selectFedById(int id);
	
	// 특정 id 피드백 수정
	//public abstract int update(IntroFedDTO dto);
	
	// 특정 id 피드백 삭제하기
	public abstract int deleteFedById(int id);
}
