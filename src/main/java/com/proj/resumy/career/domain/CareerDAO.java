package com.proj.resumy.career.domain;

import java.util.List;

public interface CareerDAO {
	// 전체 경력사항 출력 SELECT
	public abstract List<CareerDTO> select();
	
	// 새 경력사항 작성 <-- DTO 
	public abstract int insert(CareerDTO dto);
	
	// 특정 id 경력사항 내용 읽기
	public abstract List<CareerDTO> selectByUid(int uid);
	
	// 특정 id 경력사항 수정
	public abstract int update(CareerDTO dto);
	
	// 특정 id 경력사항 삭제하기
	public abstract int deleteByUid(int uid);
}
