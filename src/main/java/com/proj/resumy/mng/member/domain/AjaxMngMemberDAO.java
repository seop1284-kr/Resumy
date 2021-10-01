package com.proj.resumy.mng.member.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.proj.resumy.domain.MemberDTO;

@MapperScan
public interface AjaxMngMemberDAO {
	
	// 페이징용 SELECT
	// from : 몇번째 row 부터 
	// pageRows : 몇개의 데이터
	public List<MemberDTO> selectMemberFromRow(
			@Param("from") int from, 
			@Param("pageRows") int pageRows 
			);
	
	// 전체 글의 개수
	public int countAllMember();
	
	// 특정 uid 글(들) 삭제하기
	public int deleteByUid(int [] uids);
	
}
