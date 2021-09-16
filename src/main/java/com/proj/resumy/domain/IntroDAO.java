package com.proj.resumy.domain;

import java.util.List;

public interface IntroDAO {
	// 전체 자소서 출력 SELECT
	public abstract List<IntroDTO> select();
	
	// 새 자소서 작성 <-- DTO
	public abstract int insert(IntroDTO dto);
	
	// 특정 id 자소서 내용 읽기,
	public abstract List<IntroDTO> selectByUid(int uid);
	
	// 특정 id 자소서 수정
	public abstract int update(IntroDTO dto);
	
	// 특정 id 자소서 삭제하기
	public abstract int deleteByUid(int uid);
}
