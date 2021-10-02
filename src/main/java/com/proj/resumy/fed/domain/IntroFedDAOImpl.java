package com.proj.resumy.fed.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IntroFedDAOImpl implements IntroFedDAO {

	private IntroFedDAO mapper;
	
	@Autowired
	public IntroFedDAOImpl(SqlSession sqlSession) {
		System.out.println("IntroFedDAOImpl() 생성");
		mapper = sqlSession.getMapper(IntroFedDAO.class);
	}
	
	@Override
	public List<IntroFedDTO> select() {
		return mapper.select();
	}
	
	// 특정 자소서 id의 자소서 피드백 가져오기
	@Override
	public List<IntroFedDTO> selectByIid(int iid) {
		return mapper.selectByIid(iid);
	}


	@Override
	public int insertFed(IntroFedDTO dto) {
		return mapper.insertFed(dto);
	}


//	@Override
//	public int update(IntroFedDTO dto) {
//		return mapper.update(dto);
//	}
//
	@Override
	public int deleteFedById(int id) {
		return mapper.deleteFedById(id);
	}

	@Override
	public IntroFedDTO selectFedById(int id) {
		return mapper.selectFedById(id);
	}

}
