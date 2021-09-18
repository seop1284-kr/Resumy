package com.proj.resumy.intro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.intro.domain.IntroConDAO;
import com.proj.resumy.intro.domain.IntroConDTO;
import com.proj.resumy.intro.domain.IntroDAO;
import com.proj.resumy.intro.domain.IntroDTO;

//Service 단.
//JSP MVC model2 의 Command 역할 비슷
//  Controller -> Commmand -> DAO

//- Transaction 담당
//Spring
//@Controller -> @Service -> DAO -> JdbcTemplate

// IntroService (자소서 관리 페이지) 김진섭
@Service
public class AjaxIntroService {
	IntroDAO introDao;
	IntroConDAO introConDao;

	@Autowired
	public void setIntroDao(IntroDAO introDao) {
		this.introDao = introDao;
	}
	
	@Autowired
	public void setIntroConDAO(IntroConDAO introConDao) {
		this.introConDao = introConDao;
	}
	
	public AjaxIntroService() {
		System.out.println("IntroService() 생성");
		
	}

	public List<IntroDTO> selectFinResume(int mid) {
		
		return introDao.selectFinResume(mid);
	}
	
	public List<IntroDTO> selectNotFinResume(int mid) {
		
		return introDao.selectNotFinResume(mid);
	}
	
	public List<IntroConDTO> selectByUid(int id) {
		return introConDao.selectByUid(id);
	}

//	public int write(IntroDTO dto) {
//		return dao.insert(dto);
//	}
//
//	public List<IntroDTO> selectByUid(int uid) {
//		return dao.selectByUid(uid);
//	}
//
//	public int update(IntroDTO dto) {
//		return dao.update(dto);
//	}
//
//	public int deleteByUid(int uid) {
//		return dao.deleteByUid(uid);
//	}
	
}
