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
	public List<IntroDTO> selectNotFinResume(int mid) {
		return mapper.selectNotFinResume(mid);
	}

	@Override
	public List<IntroDTO> selectFinResume(int mid) {
		return mapper.selectFinResume(mid);
	}
	
	@Override
	public IntroDTO getIntroById(int id) {
		return mapper.getIntroById(id);
	}
	
	@Override
	public int insert(IntroDTO dto) {
		return mapper.insert(dto);
	}



	@Override
	public int update(IntroDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int deleteByUid(int uid) {
		return mapper.deleteByUid(uid);
	}

}
