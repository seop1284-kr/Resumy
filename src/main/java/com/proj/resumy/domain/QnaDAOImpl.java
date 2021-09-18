package com.proj.resumy.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDAOImpl implements QnaDAO {

	private QnaDAO mapper;
	
	@Autowired
	public QnaDAOImpl(SqlSession sqlSession) {
		System.out.println("QnaDAOImpl() 생성");
		mapper = sqlSession.getMapper(QnaDAO.class);
	}
	
	@Override
	public List<QnaQDTO> select() {
		return mapper.select();
	}

	@Override
	public int insert(QnaQDTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public List<QnaQDTO> selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}

	@Override
	public List<QnaADTO> selectByQid(int uid) {
		return mapper.selectByQid(uid);
	}

	@Override
	public int update(QnaQDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int deleteByUid(int uid) {
		return mapper.deleteByQid(uid);
	}

	@Override
	public int deleteByQid(int uid) {
		return mapper.deleteByQid(uid);
	}

}
