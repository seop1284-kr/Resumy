package com.proj.resumy.intro.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IntroDAOImpl implements IntroDAO {

	private IntroDAO mapper;
	
	@Autowired
	public IntroDAOImpl(SqlSession sqlSession) {
		System.out.println("IntroDAOImpl() 생성");
		mapper = sqlSession.getMapper(IntroDAO.class);
	}
	

	@Override
	public List<IntroDTO> selectResume(String userid) {
		return mapper.selectResume(userid);
	}
	
	@Override
	public IntroDTO selectResumeById(int id) {
		return mapper.selectResumeById(id);
	}


	@Override
	public int deleteResumeById(int id) {
		return mapper.deleteResumeById(id);
	}


	@Override
	public int updateResumeById(IntroDTO introDto) {
		return mapper.updateResumeById(introDto);
	}


	@Override
	public int insertResume(IntroDTO introDto) {
		return mapper.insertResume(introDto);
	}
	
	
	

}
