package com.proj.resumy.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.file.domain.FileDAO;
import com.proj.resumy.file.domain.FileDTO;

//AjaxFileController (파일관리) 하병노

@Service
public class FileService {

	FileDAO dao;
	
	@Autowired
	public void setDao(FileDAO dao) {
		this.dao = dao;		
	}
	
	public FileService() {
		System.out.println("FileService() 생성");	
	}
	
	public List<FileDTO> list() {	
		return dao.select();
	}
	
}
