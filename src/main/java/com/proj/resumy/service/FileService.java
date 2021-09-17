package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.FileDAO;
import com.proj.resumy.domain.FileDTO;

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
