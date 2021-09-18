package com.proj.resumy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.resumy.domain.MemberDAO;
import com.proj.resumy.domain.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDao;
	
	public MemberService() {
		System.out.println("MemberService() 생성");
	}
	
	// 회원가입
	// ROLE_MEMBER 권한 부여
	@Transactional
	public int addMember(MemberDTO user) {
		int cnt = memberDao.addUser(user);
		memberDao.addAuth(user.getUserid(), "ROLE_MEMBER");
		return cnt;
	}
	
	// 회원삭제
	@Transactional
	public int deleteMember(MemberDTO user) {
		memberDao.deleteAuths(user.getUserid());  // 권한(들) 먼저 삭제
		int cnt = memberDao.deleteUser(user);
		return cnt;
	}
	
	// 특정 id(username) 의 정보 가져오기
	public MemberDTO findById(String userId) {
		return memberDao.findById(userId);
	}
	
	// 특정 id 의 권한(들) 정보 가져오기
	public List<String> selectAuthoritiesById(String userId){
		return memberDao.selectAuthoritiesById(userId);
	}
}
