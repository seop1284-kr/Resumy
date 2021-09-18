package com.proj.resumy.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAOImpl implements MemberDAO {

	private MemberDAO mapper;
	
	@Autowired
	public MemberDAOImpl(SqlSession sqlSession) {
		System.out.println("MemberDAOImpl() 생성");
		mapper = sqlSession.getMapper(MemberDAO.class);
	}
	
	
	@Override
	public int addUser(MemberDTO user) {
		return mapper.addUser(user);
	}

	@Override
	public int addAuth(String userid, String auth) {
		return mapper.addAuth(userid, auth);
	}

	@Override
	public int deleteUser(MemberDTO user) {
		return mapper.deleteUser(user);
	}

	@Override
	public int deleteAuth(String userid, String auth) {
		return mapper.deleteAuth(userid, auth);
	}

	@Override
	public int deleteAuths(String userid) {
		return mapper.deleteAuths(userid);
	}

	@Override
	public MemberDTO findById(String userid) {
		return mapper.findById(userid);
	}

	@Override
	public List<String> selectAuthoritiesById(String userid) {
		return mapper.selectAuthoritiesById(userid);
	}

}