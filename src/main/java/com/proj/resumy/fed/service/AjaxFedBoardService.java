package com.proj.resumy.fed.service;

import java.util.ArrayList;
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
import com.proj.resumy.mng.fed.domain.MngFedResult;

@Service
public class AjaxFedBoardService {
	
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
	
	
	public List<IntroResult> list(int from, int pageRows) {
		List<IntroResult> introResultList = new ArrayList<IntroResult>();
		List<IntroDTO> introList = introDao.selectResumeInPublicFromRow(from, pageRows);
		
		for (int i = 0; i < introList.size(); i++) {
			IntroResult introResult = new IntroResult();
			introResult.setIntro(introList.get(i));
			introResult.setConList(introConDao.selectConByIid(introList.get(i).getId()));
			introResultList.add(introResult);
			
		}	
		return introResultList;
	}
	
	public List<IntroResult> listWithKeyword(int from, int pageRows, String keyword) {
		List<IntroResult> introResultList = new ArrayList<IntroResult>();
		List<IntroDTO> resumes = introDao.selectResumeByKeyword(keyword);
		List<IntroConDTO> conList = introConDao.selectConByKeyword(keyword);
		
		HashSet<Integer> iidSet = new HashSet<Integer>();
		for (int i = 0; i < conList.size(); i++) {
			iidSet.add(conList.get(i).getIid());
		}
		for (int i = 0; i < resumes.size(); i++) {
			iidSet.add(resumes.get(i).getId());
		}
		
		
		// selectResumesByIdFromRow : public으로 된 여러 특정 자소서 id의 자소서 select(페이징)
		List<IntroDTO> introList = introDao.selectResumesByIdFromRow(from, pageRows, iidSet);					
		for (int i = 0; i < introList.size(); i++) {
			IntroResult introResult = new IntroResult();
			introResult.setIntro(introList.get(i));
			introResult.setConList(introConDao.selectConByIid(introList.get(i).getId()));
			introResultList.add(introResult);
			
		}	
		return introResultList;
	}
	
	public int count() {
		return introDao.countAllResumeInPublic();
	}
	
	public int countWithKeyword(String keyword) {
		List<IntroDTO> resumes = introDao.selectResumeByKeyword(keyword);
		List<IntroConDTO> conList = introConDao.selectConByKeyword(keyword);
		
		HashSet<Integer> iidSet = new HashSet<Integer>();
		for (int i = 0; i < conList.size(); i++) {
			iidSet.add(conList.get(i).getIid());
		}
		for (int i = 0; i < resumes.size(); i++) {
			iidSet.add(resumes.get(i).getId());
		}
		
		return iidSet.size();
	}
	
}
