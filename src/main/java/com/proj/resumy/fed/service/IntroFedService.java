package com.proj.resumy.fed.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.fed.domain.IntroFedDAO;
import com.proj.resumy.fed.domain.IntroFedDTO;
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

//IntroFedService (자소서 피드백 페이지) 김진섭
@Service
public class IntroFedService {
	
	IntroFedDAO introFedDao;
	IntroDAO introDao;
	IntroConDAO introConDao;

	@Autowired
	public void setIntroFedDao(IntroFedDAO introFedDao) {
		this.introFedDao = introFedDao;
	}

	@Autowired
	public void setIntroDao(IntroDAO introDao) {
		this.introDao = introDao;
	}
	
	@Autowired
	public void setIntroConDao(IntroConDAO introConDao) {
		this.introConDao = introConDao;
	}
	
	public IntroFedService() {
		System.out.println("IntroFedService() 생성");
	}
	
	// public으로 설정된 자소서 가져오기(자소서, 내용)
	public List<IntroResult> selectResumeInPublic() {
		List<IntroResult> introResultList = new ArrayList<IntroResult>();
		List<IntroDTO> introList = introDao.selectResumeInPublic();
		
		for (int i = 0; i < introList.size(); i++) {
			IntroResult introResult = new IntroResult();
			introResult.setIntro(introList.get(i));
			introResult.setConList(introConDao.selectConByIid(introList.get(i).getId()));
			introResultList.add(introResult);
			
		}	
		return introResultList;
	}
	
	// 자소서 내용과 피드백 내용 가져오기(자소서, 내용, 피드백)
	public IntroResult selectFed(int id) {
		IntroResult introResult = new IntroResult();
		IntroDTO intro = introDao.selectResumeById(id);
		
		introResult.setIntro(intro);
		introResult.setConList(introConDao.selectConByIid(intro.getId()));
		introResult.setFedList(introFedDao.selectByIid(intro.getId()));

		return introResult;
	}
	
	// public으로 된 키워드로 검색한 자소서 가져오기
	public List<IntroResult> selectResumeByKeyword(String keyword) {
		List<IntroResult> introResultList = new ArrayList<IntroResult>();
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
		
		// selectResumesById : public으로 된 여러 특정 자소서 id의 자소서 select
		List<IntroDTO> introList = introDao.selectResumesById(iidSet);
		for (int i = 0; i < introList.size(); i++) {
			IntroResult introResult = new IntroResult();
			introResult.setIntro(introList.get(i));
			introResult.setConList(introConDao.selectConByIid(introList.get(i).getId()));
			introResultList.add(introResult);
			
		}	
		
		
		return introResultList;
	}
	// 피드백 아이디로 select
	public IntroFedDTO selectFedById(int id) {
		return introFedDao.selectFedById(id);
	}
	
	// 피드백 작성
	public int insertFed(IntroFedDTO fedDto) {
		return introFedDao.insertFed(fedDto);
	}
	
	// 피드백 삭제
	public int deleteFedById(int id) {
		return introFedDao.deleteFedById(id);
	}
	
	
	
	


	
}
