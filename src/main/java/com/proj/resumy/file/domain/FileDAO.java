package com.proj.resumy.file.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

//AjaxFileController (파일관리) 하병노

public interface FileDAO {
	
	// 특정 회원(mem_id)의 첨부파일들 SELECT
	public abstract List<FileDTO> selectByUserID(String userid);
	
	// 특정 첨부파일 (file_id) 한개 SELECT
	public abstract FileDTO selectByFid(int id);
	
	// 새파일 업로드
	public abstract int insert(FileDTO dto);

	
	// 특정 파일(file_id) 삭제
//	public abstract int deleteById(int id);

	// 복수개의 특정 파일(file_id)들 삭제
	public abstract int deleteByIds(int[] id);

	// 한 아이디당 올린 파일의 갯수 
	public abstract int filesById(String userid);

	







	

	

	
	
	
	

}
