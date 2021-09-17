package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.SpecDAO;
import com.proj.resumy.domain.SpecDTO;


@Service
public class SpecService {
	SpecDAO dao;

	@Autowired
	public void setDao(SpecDAO dao) {
		this.dao = dao;
	}
	
	public SpecService() {
		System.out.println("SpecService() 생성");
		
	}

	public List<SpecDTO> list() {
		
		return dao.select();
	}

}
