package com.proj.resumy.intro.domain;

import java.util.List;

//자소서 내용 DAO 인터페이스 김진섭
public interface IntroConDAO {
	// 특정 id 자소서 내용 읽기
	public abstract List<IntroConDTO> selectByUid(int uid);
	
	// 새 자소서 작성 <-- DTO
	public abstract int insert(IntroConDTO dto);
	
	// 특정 id 자소서 수정
	public abstract int update(IntroConDTO dto);
	
	// 특정 id 자소서 삭제하기
	public abstract int deleteByUid(int uid);
}
