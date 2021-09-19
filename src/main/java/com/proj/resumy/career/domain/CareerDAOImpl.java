package com.proj.resumy.career.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CareerDAOImpl implements CareerDAO {

	private CareerDAO mapper;
	
	@Autowired
	public CareerDAOImpl(SqlSession sqlSession) {
		System.out.println("CareerDAOImpl() 생성");
		mapper = sqlSession.getMapper(CareerDAO.class);
	}
	
	@Override
	public List<CareerDTO> select(int mid) {
		return mapper.select(mid);
	}

	@Override
	public int insert(CareerDTO dto) {
		return mapper.insert(dto);
	}

//	@Override
//	public List<CareerDTO> selectByUid(int id) {
//		return mapper.selectByUid(id);
//	}

	@Override
	public int update(CareerDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(CareerDTO dto) {
		return mapper.delete(dto);
	}

}
