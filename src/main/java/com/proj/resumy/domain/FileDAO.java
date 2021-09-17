package com.proj.resumy.domain;

import java.util.List;

public interface FileDAO {
	
	// 전체 파일 리스트 출력 SELECT
	public abstract List<FileDTO> select();

	// 새 파일 업로드
	public abstract int insert(FileDTO dto);

	// 특정 id 파일 다운로드 ?
	
	
	// 특정 id 파일 삭제

	public abstract int deleteByUid(int uid);



	

	

	
	
	
	

}
