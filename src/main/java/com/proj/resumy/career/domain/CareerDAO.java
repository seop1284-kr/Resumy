package com.proj.resumy.career.domain;

import java.util.List;

public interface CareerDAO {
	// 전체 학력사항 출력 SELECT
	public abstract List<CareerDTO> select(int mid);
	
	// 새 학력사항 작성 <-- DTO 
	public abstract int insert(CareerDTO dto);
	
	// 특정 id 학력사항 내용 읽기
	// public abstract List<CareerDTO> selectByUid(int uid);
	
	// 특정 id 학력사항 수정
	public abstract int update(CareerDTO dto);
	
	// 특정 uid 글(들) 삭제하기
	public int delete(CareerDTO dto);
	

}
