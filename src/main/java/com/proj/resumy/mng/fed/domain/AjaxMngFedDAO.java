package com.proj.resumy.mng.fed.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.proj.resumy.fed.domain.IntroFedDTO;
import com.proj.resumy.intro.domain.IntroResult;

@MapperScan
public interface AjaxMngFedDAO {
	
	// 페이징용 SELECT
	// from : 몇번째 row 부터 
	// pageRows : 몇개의 데이터
	public List<IntroFedDTO> selectFedFromRow(
			@Param("from") int from, 
			@Param("pageRows") int pageRows 
			);
	
	// 전체 글의 개수
	public int countAllFed();
	
	// 특정 uid 글(들) 삭제하기
	public int deleteByUid(int [] uids);
	
}
