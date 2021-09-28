package com.proj.resumy.file.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

//AjaxFileController (파일관리) 하병노

@Repository
public class FileDAOImpl implements FileDAO {

	private FileDAO mapper;

	public FileDAOImpl(SqlSession sqlSession) {
		System.out.println("FileDAOImpl() 생성");
		mapper = sqlSession.getMapper(FileDAO.class);
	}
	
	// 특정 회원(mem_id)의  첨부파일들 SELECT
	@Override
	public List<FileDTO> selectByUserID(String userid) {
		return mapper.selectByUserID(userid);
	}
	
	// 특정 파일 한개의 정보
	@Override
	public FileDTO selectByFid(int id) {
		return mapper.selectByFid(id);
	}
	
	// 새파일 업로드
	@Override
	public int insert(FileDTO dto) {
		return mapper.insert(dto);
	}

	// 특정 file_id 파일 삭제
	@Override
	public int deleteById(int id) {
		return mapper.deleteById(id);
	}
	
	// 특정 file_id 파일들 삭제
	@Override
	public int deleteByIds(int[] id) {
		return mapper.deleteByIds(id);
	}

	// 특정 파일(file_id) 다운로드 ? 이걸 구현할 수 있나?
	@Override
	public int download() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
