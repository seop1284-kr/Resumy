package com.proj.resumy.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.file.domain.FileDAO;
import com.proj.resumy.file.domain.FileDTO;

//Service 단.
//JSP MVC model2 의 Command 역할 비슷
//Controller -> Commmand -> DAO

//- Transaction 담당
//Spring
//@Controller -> @Service -> DAO -> JdbcTemplate


//AjaxFileController (파일관리) 하병노

@Service
public class AjaxFileService {

	FileDAO filedao;
	
	@Autowired
	public void setFileDao(FileDAO filedao) {
		this.filedao = filedao;		
	}
	
	public AjaxFileService() {
		System.out.println("FileService() 생성");	
	}
	
	// 특정 회원(mem_id)의  첨부파일들
	public List<FileDTO> selectByUserID(String userid) {
		return filedao.selectByUserID(userid);
	}
	
	// 특정 파일 한개의 정보
	public int selectByFid(int id) {
		return filedao.selectByFid(id);
	}
	
	// 새파일 업로드
	public int insert(FileDTO dto) {
		return filedao.insert(dto);
	}
	
	// 특정 file_id 파일 삭제
	public int deleteById(int id) {
		return filedao.deleteById(id);
	}
	
	// 특정 file_id 파일들 삭제
	public int deleteByIds(int[] id) {
		return filedao.deleteByIds(id);
	}
	
	
	
}
