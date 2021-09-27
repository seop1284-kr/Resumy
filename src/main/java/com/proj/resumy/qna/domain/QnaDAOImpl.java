package com.proj.resumy.qna.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDAOImpl implements QnaDAO {

	private QnaDAO mapper;
	
	@Autowired
	public QnaDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(QnaDAO.class);
	}
	
	@Override
	public List<QnaQDTO> selectQnaQ() {
		return mapper.selectQnaQ();
	}
	
	@Override
	public List<QnaADTO> selectQnaA() {
		return mapper.selectQnaA();
	}

	@Override
	public int insertQnaQ(QnaQDTO dto) {
		return mapper.insertQnaQ(dto);
	}

	// (controller 의 /mng/qna/updateOk.do 에 코드 위치)
	@Override
	public int insertQnaA(QnaADTO dto) {
		return mapper.insertQnaA(dto);
	}

	@Override
	public QnaQDTO selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}

	@Override
	public QnaADTO selectByQid(int uid) {
		return mapper.selectByQid(uid);
	}

	@Override
	public int updateQnaQ(QnaQDTO dto) {
		return mapper.updateQnaQ(dto);
	}
	
	@Override
	public int updateQnaA(QnaADTO dto) {
		return mapper.updateQnaA(dto);
	}
	
	@Override
	public boolean updateReplyState(QnaQDTO dto) {
		return mapper.updateReplyState(dto);
	}

	@Override
	public int deleteByUid(int uid) {
		return mapper.deleteByUid(uid);
	}

	@Override
	public int deleteByQid(int uid) {
		return mapper.deleteByQid(uid);
	}
}
