package com.proj.resumy.mng.fed.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.intro.domain.IntroResult;

@Repository
public class AjaxMngFedDAOImpl implements AjaxMngFedDAO {

	private AjaxMngFedDAO mapper;
	
	@Autowired
	public AjaxMngFedDAOImpl(SqlSession sqlSession) {
		System.out.println("AjaxMngFedDAOImpl() 생성");
		mapper = sqlSession.getMapper(AjaxMngFedDAO.class);
	}
	
	@Override
	public List<IntroFedDTO> selectFedFromRow(int from, int pageRows) {
		return mapper.selectFedFromRow(from, pageRows);
	}

	@Override
	public int countAllFed() {
		return mapper.countAllFed();
	}
	
	@Override
	public int deleteByUid(int[] uids) {
		return mapper.deleteByUid(uids);
	}
}
