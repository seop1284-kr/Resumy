package com.proj.resumy.spec.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpecDAOImpl implements SpecDAO {

	private SpecDAO mapper;
	
	@Autowired
	public SpecDAOImpl(SqlSession sqlSession) {
		System.out.println("SpecDAOImpl() 생성");
		mapper = sqlSession.getMapper(SpecDAO.class);
	}
	
	@Override
	public List<SpecDTO> select(String userid) {
		return mapper.select(userid);
	}

	@Override
	public int insert(SpecDTO dto) {
		return mapper.insert(dto);
	}

//	@Override
//	public List<SpecDTO> selectByUid(int id) {
//		return mapper.selectByUid(id);
//	}

	@Override
	public int update(SpecDTO dto) {
		return mapper.update(dto);
	}


}
