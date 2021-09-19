package com.proj.resumy.spec.domain;

import java.util.List;

public interface SpecDAO {
	// 전체 경력사항 출력 SELECT
	public abstract List<SpecDTO> select(int mid);
	
	// 새 경력사항 작성 <-- DTO 
	public abstract int insert(SpecDTO dto);
	
	// 특정 id 경력사항 내용 읽기
	// public abstract List<CareerDTO> selectByUid(int uid);
	
	// 특정 id 경력사항 수정
	public abstract int update(SpecDTO dto);
}
