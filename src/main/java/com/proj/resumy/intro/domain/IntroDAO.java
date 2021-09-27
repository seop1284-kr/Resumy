package com.proj.resumy.intro.domain;

import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.proj.resumy.fed.domain.IntroFedDTO;

//자소서 DAO 인터페이스 김진섭
public interface IntroDAO {
	// 로그인한 userid의 자소서 select
	public abstract List<IntroDTO> selectResume(String userid);
	
	// 공개로 설정된 자소서 select
	public abstract List<IntroDTO> selectResumeInPublic();
	
	// 공개로 설정된 자소서 select
	// 페이징용 SELECT
	// from : 몇번째 row 부터 
	// pageRows : 몇개의 데이터
	public List<IntroDTO> selectResumeInPublicFromRow(
			@Param("from") int from, 
			@Param("pageRows") int pageRows
			);
	
	// 공개로 설정되고 keyword
	public List<IntroDTO> selectResumesByIdFromRow(
			@Param("from") int from, 
			@Param("pageRows") int pageRows,
			HashSet<Integer> iidSet
			);
	// 전체 글의 개수
	public int countAllResumeInPublic();
	
	// 특정 id 자소서 select
	public abstract IntroDTO selectResumeById(int id);
	
	// public으로 된 여러 특정 id 자소서 select
	public abstract List<IntroDTO> selectResumesById(HashSet<Integer> iidSet);
	
	// 특정 userid의 여러 특정 id 자소서 select
	public abstract List<IntroDTO> selectMyResumesById(HashSet<Integer> iidSet, String userid);
		
	
	// 특정 id 자소서 삭제
	public abstract int deleteResumeById(int id);

	// 자소서 추가
	public abstract int insertResume(IntroDTO introDto);
	
	// 키워드로 자소서 내용 검색
	public abstract List<IntroDTO> selectResumeByKeyword(String keyword);
	
	
	
	
	// 자소서 수정
	public abstract int updateResumeById(IntroDTO introDto);
	
	
}
