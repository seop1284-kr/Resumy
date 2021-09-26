package com.proj.resumy.mng.fed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.intro.domain.IntroDAO;
import com.proj.resumy.intro.domain.IntroResult;
import com.proj.resumy.mng.fed.domain.AjaxMngFedDAO;
import com.proj.resumy.mng.fed.domain.MngFedResult;

@Service
public class AjaxMngFedService {
	
	AjaxMngFedDAO ajaxMngFedDao;
	IntroDAO introDao;
	
	@Autowired
	public void setAjaxMngFedDao(AjaxMngFedDAO ajaxMngFedDao) {
		this.ajaxMngFedDao = ajaxMngFedDao;
	}
	
	@Autowired
	public void setIntroDao(IntroDAO introDao) {
		this.introDao = introDao;
	}
	

	
	public List<MngFedResult> list(int from, int pageRows) {
		List<MngFedResult> mngFedResultList = new ArrayList<>();
		List<IntroFedDTO> introFedList = ajaxMngFedDao.selectFedFromRow(from, pageRows);
		for (int i = 0; i < introFedList.size(); i++) {
			MngFedResult mngFedResult = new MngFedResult();
			mngFedResult.setFedDto(introFedList.get(i));
			mngFedResult.setIntroDto(introDao.selectResumeById(introFedList.get(i).getIid()));
				
			mngFedResultList.add(mngFedResult);
		}
		return mngFedResultList;		
	}
	
	public int count() {
		return ajaxMngFedDao.countAllFed();
	}
	
	public int deleteByUid(int [] uids) {// 복수개의 uid (들)		
		return ajaxMngFedDao.deleteByUid(uids);
	}
}
