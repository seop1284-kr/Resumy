package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.fed.domain.IntroFedDAO;
import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.intro.domain.IntroDAO;
import com.proj.resumy.intro.domain.IntroDTO;

//Service 단.
//JSP MVC model2 의 Command 역할 비슷
//  Controller -> Commmand -> DAO

//- Transaction 담당
//Spring
//@Controller -> @Service -> DAO -> JdbcTemplate

@Service
public class IntroFedService {
	IntroFedDAO dao;

	@Autowired
	public void setDao(IntroFedDAO dao) {
		this.dao = dao;
	}
	
	public IntroFedService() {
		System.out.println("IntroFedService() 생성");
		
	}

	public List<IntroFedDTO> list() {
		
		return dao.select();
	}
	
}
