package com.proj.resumy.intro.domain;

import java.util.List;

//자소서 DAO 인터페이스 김진섭
public interface IntroDAO {
	// 자기 자소서 SELECT
	public abstract List<IntroDTO> selectResume(String userid);
	
	// 특정 id 자소서 내용 읽기
	public abstract IntroDTO selectResumeById(int id);
	
	// 특정 id 자소서 삭제
	public abstract int deleteResumeById(int id);

	// 자소서 추가
	public abstract int insertResume(IntroDTO introDto);
	
	// 자소서 수정
	public abstract int updateResumeById(IntroDTO introDto);
	
	
}
