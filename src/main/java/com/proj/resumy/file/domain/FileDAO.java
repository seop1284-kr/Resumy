package com.proj.resumy.file.domain;

import java.util.List;

//AjaxFileController (파일관리) 하병노

public interface FileDAO {
	
	// 특정 회원(mem_id)의 첨부파일들 SELECT
	public abstract List<FileDTO> selectByMid(int mid);
	
	


	// 새 파일 업로드
	public abstract int insert(FileDTO dto);

	// 특정 id 파일 다운로드 ?
	
	
	// 특정 id 파일 삭제

	public abstract int deleteByUid(int uid);









	

	

	
	
	
	

}
