package com.proj.resumy.intro.domain;

import java.util.HashSet;
import java.util.List;

//자소서 DAO 인터페이스 김진섭
public interface IntroDAO {
	// 로그인한 userid의 자소서 select
	public abstract List<IntroDTO> selectResume(String userid);
	
	// 공개로 설정된 자소서 select
	public abstract List<IntroDTO> selectResumeInPublic();
	
	// 특정 id 자소서 select
	public abstract IntroDTO selectResumeById(int id);
	
	// 여러 특정 id 자소서 select
	public abstract List<IntroDTO> selectResumesById(HashSet<Integer> iidSet);
	
	// 특정 id 자소서 삭제
	public abstract int deleteResumeById(int id);

	// 자소서 추가
	public abstract int insertResume(IntroDTO introDto);
	
	// 키워드로 자소서 내용 검색
	public abstract List<IntroDTO> selectResumeByKeyword(String keyword);
	
	
	// 자소서 수정
	public abstract int updateResumeById(IntroDTO introDto);
	
	
}
