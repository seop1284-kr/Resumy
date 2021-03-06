package com.proj.resumy.intro.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


// IntroConDAOImpl (자소서 내용) 김진섭
@Repository
public class IntroConDAOImpl implements IntroConDAO {

	private IntroConDAO mapper;
	
	@Autowired
	public IntroConDAOImpl(SqlSession sqlSession) {
		System.out.println("IntroConDAOImpl() 생성");
		mapper = sqlSession.getMapper(IntroConDAO.class);
	}
	
	@Override
	public List<IntroConDTO> selectConByIid(int iid) {
		return mapper.selectConByIid(iid);
	}
	
	@Override
	public int insertCon(IntroConDTO dto) {
		return mapper.insertCon(dto);
	}

	@Override
	public List<IntroConDTO> selectConByKeyword(String keyword) {
		return mapper.selectConByKeyword(keyword);
	}

	@Override
	public int updateConById(IntroConDTO dto) {
		return mapper.updateConById(dto);
	}

	@Override
	public int deleteConById(int iid) {
		return mapper.deleteConById(iid);
	}

}
