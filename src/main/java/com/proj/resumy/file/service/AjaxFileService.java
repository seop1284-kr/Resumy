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
	
	public List<FileDTO> selectByMid(int mid) {
		return filedao.selectByMid(mid);
	}
	
}
