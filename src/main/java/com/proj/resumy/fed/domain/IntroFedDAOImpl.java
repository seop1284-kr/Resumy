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
	public List<IntroFedDTO> selectById(int iid) {
		return mapper.selectById(iid);
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
//	@Override
//	public int deleteByUid(int uid) {
//		return mapper.deleteByUid(uid);
//	}

}
