package com.proj.resumy.intro.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.intro.domain.IntroConDAO;
import com.proj.resumy.intro.domain.IntroConDTO;
import com.proj.resumy.intro.domain.IntroDAO;
import com.proj.resumy.intro.domain.IntroDTO;
import com.proj.resumy.intro.domain.IntroResult;

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
	
	
	// 특정 iid 자소서 내용 가져오기
	public List<IntroConDTO> selectConByIid(int iid) {
		return introConDao.selectConByIid(iid);
	}
	
	// 자소서 쓰기
	public int writeResume(IntroDTO introDto, String[] question, String[] content) {
		introDao.insertResume(introDto);
		
		// 첫 질문을 채우지 않으면 데이터가 넘어오지 않음(이유 모름)
		if (question.length == 0) {
			IntroConDTO conDto = new IntroConDTO();
			conDto.setQuestion("");
			conDto.setContent("");
			conDto.setIid(introDto.getId());
			introConDao.insertCon(conDto);
		}

		for (int i = 0; i < question.length; i++) {
			IntroConDTO conDto = new IntroConDTO();
			conDto.setQuestion(question[i]);
			conDto.setContent(content[i]);
			conDto.setIid(introDto.getId());
			introConDao.insertCon(conDto);
		}
		
		return introDto.getId();
		
	}
	
	// 특정 userid의 키워드로 검색한 자소서 가져오기
	public List<IntroDTO> selectMyResumeByKeyword(String keyword, String userid) {
		List<IntroDTO> resumes = introDao.selectResumeByKeyword(keyword);
		List<IntroConDTO> conList = introConDao.selectConByKeyword(keyword);
		
		if (conList == null) {
			return null;
		}
		
		HashSet<Integer> iidSet = new HashSet<Integer>();
		for (int i = 0; i < conList.size(); i++) {
			iidSet.add(conList.get(i).getIid());
		}
		for (int i = 0; i < resumes.size(); i++) {
			iidSet.add(resumes.get(i).getId());
		}
		
		List<IntroDTO> introList = introDao.selectMyResumesById(iidSet, userid);					
		return introList;
	}
	
	
	public int update(IntroDTO introDto, String[] question, String[] content) {		
		introDao.updateResumeById(introDto);
		// 질문은 삭제 후 다시 생성
		introConDao.deleteConById(introDto.getId());
		
		// 첫 질문을 채우지 않으면 데이터가 넘어오지 않음(이유 모름)
		if (question.length == 0) {
			IntroConDTO conDto = new IntroConDTO();
			conDto.setQuestion("");
			conDto.setContent("");
			conDto.setIid(introDto.getId());
			introConDao.insertCon(conDto);
		}

		for (int i = 0; i < question.length; i++) {
			
			IntroConDTO conDto = new IntroConDTO();
			conDto.setQuestion(question[i]);
			conDto.setContent(content[i]);
			conDto.setIid(introDto.getId());
			introConDao.insertCon(conDto);
		}
		
		return introDto.getId();
	}
	
	
	
//
//	public List<IntroDTO> selectByUid(int uid) {
//		return dao.selectByUid(uid);
//	}
//

//
//	public int deleteByUid(int uid) {
//		return dao.deleteByUid(uid);
//	}
	
}
