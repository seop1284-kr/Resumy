package com.proj.resumy.intro.domain;

import java.util.List;

//자소서 내용 DAO 인터페이스 김진섭
public interface IntroConDAO {
	// 특정 id 자소서의 내용 선택
	public abstract List<IntroConDTO> selectConByIid(int iid);
	
	// 새 자소서 내용 작성 <-- DTO
	public abstract int insertCon(IntroConDTO dto);
	
	// 키워드로 자소서 내용 검색
	public abstract List<IntroConDTO> selectConByKeyword(String keyword);

	// 특정 id 자소서 내용 수정
	public abstract int updateConById(IntroConDTO dto);
	
	//  특정 iid 자소서 내용 삭제
	public abstract int deleteConById(int iid);
}
