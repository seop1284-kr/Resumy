package com.proj.resumy.spec.domin;

import java.util.List;

public interface SpecDAO {
	// 전체 학력사항 출력 SELECT
	public abstract List<SpecDTO> select();
	
	// 새 학력사항 작성 <-- DTO	(수정만 있는데 새 학령사항 작성...)
	public abstract int insert(SpecDTO dto);
	
	// 특정 id 학력사항 내용 읽기
	public abstract List<SpecDTO> selectByUid(int uid);
	
	// 특정 id 학력사항 수정
	public abstract int update(SpecDTO dto);
	
	// 특정 id 학력사항 삭제하기
	public abstract int deleteByUid(int uid);
}
