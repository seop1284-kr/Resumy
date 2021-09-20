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
	
	// 모든 자기 자소서 가져오기
	public List<IntroDTO> selectResume(String userid) {
		return introDao.selectResume(userid);
	}
	
	// 특정 id 자소서 가져오기
	public IntroDTO selectResumeById(int id) {
		return introDao.selectResumeById(id);
	}

	// 특정 id 자소서 삭제
	public int deleteResumeById(int id) {
		return introDao.deleteResumeById(id);
	}
	
	// 특정 id 자소서 삭제
	public int updateResumeById(IntroDTO introDto) {
		return introDao.updateResumeById(introDto);
	}
	
	
	// 특정 iid 자소서 내용 가져오기
	public List<IntroConDTO> selectConByIid(int iid) {
		return introConDao.selectConByIid(iid);
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
