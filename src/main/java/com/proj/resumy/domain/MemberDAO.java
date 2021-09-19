package com.proj.resumy.domain;

import java.util.List;

public interface MemberDAO {
	// 사용자 추가
	int addUser(MemberDTO user);
	
	// 사용자 권한 추가
	int addAuth(String userid, String auth);
	
	// 사용자 삭제
	int deleteUser(MemberDTO user);
	
	// 특정 사용자 권한 삭제
	int deleteAuth(String userid, String auth);
	
	// 특정 사용자 권한(들) 전부 삭제
	int deleteAuths(String userid);
	
	// 특정 userid (username) 의 사용자 찾기
	MemberDTO findById(String userid);
	
	// 특정 userid를 매개변수로 이용해서 user_name 반환
	String findNameByUserId(String userid);
	
	// 특정 userid (username) 의 권한(들) 뽑기
	List<String> selectAuthoritiesById(String userid);
}
