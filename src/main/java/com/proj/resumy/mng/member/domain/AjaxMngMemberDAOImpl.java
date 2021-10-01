package com.proj.resumy.mng.member.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.resumy.domain.MemberDTO;

@Repository
public class AjaxMngMemberDAOImpl implements AjaxMngMemberDAO {

	private AjaxMngMemberDAO mapper;
	
	@Autowired
	public AjaxMngMemberDAOImpl(SqlSession sqlSession) {
		System.out.println("AjaxMngMemberDAOImpl() 생성");
		mapper = sqlSession.getMapper(AjaxMngMemberDAO.class);
	}
	
	@Override
	public List<MemberDTO> selectMemberFromRow(int from, int pageRows) {
		return mapper.selectMemberFromRow(from, pageRows);
	}

	@Override
	public int countAllMember() {
		return mapper.countAllMember();
	}
	
	@Override
	public int deleteByUid(int[] uids) {
		return mapper.deleteByUid(uids);
	}
}
