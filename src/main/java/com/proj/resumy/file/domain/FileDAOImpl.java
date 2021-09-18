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
	
	@Override
	public List<FileDTO> select() {
		return mapper.select();
	}
	
	@Override
	public int insert(FileDTO dto) {
		return mapper.insert(dto);
	}


	@Override
	public int deleteByUid(int uid) {
		return mapper.deleteByUid(uid);
	}
	
	
}
