package com.proj.resumy.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.resumy.career.domain.CareerDTO;


@Repository
public class MemberDAOImpl implements MemberDAO {

	private MemberDAO mapper;
	
	@Autowired
	public MemberDAOImpl(SqlSession sqlSession) {
		System.out.println("MemberDAOImpl() 생성");
		mapper = sqlSession.getMapper(MemberDAO.class);
	}
	
	@Override
	public List<MemberDTO> selectAll() {
		return mapper.selectAll();
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
	
	// 특정 userid를 매개변수로 이용해서 user_name 반환
	@Override
	public String findNameByUserId(String userid) {
		return mapper.findNameByUserId(userid);
	}

	@Override
	public List<String> selectAuthoritiesById(String userid) {
		return mapper.selectAuthoritiesById(userid);
	}
	
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	}


}
