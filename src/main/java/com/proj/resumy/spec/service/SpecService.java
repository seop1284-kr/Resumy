package com.proj.resumy.spec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.spec.domain.SpecDAO;
import com.proj.resumy.spec.domain.SpecDTO;


@Service
public class SpecService {
	SpecDAO dao;

	@Autowired
	public void setDao(SpecDAO dao) {
		this.dao = dao;
	}
	
	public SpecService() {
		System.out.println("CareerService() 생성");	
	}

	public List<SpecDTO> view(String userid) {
		return dao.select(userid);
	}
	
	public int write(SpecDTO dto) {
		return dao.insert(dto); 
	}
	
	public int update(SpecDTO dto) {
		return dao.update(dto);
	}

}
