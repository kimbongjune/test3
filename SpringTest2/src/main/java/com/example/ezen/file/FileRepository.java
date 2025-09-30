package com.example.ezen.file;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository {
	
	private final SqlSession template;
	
	@Autowired
	public FileRepository(SqlSession template) {
		this.template = template;
	}

	public int insertFiles(List<FileVO> list) {
		return template.insert("fileMapper.insertFiles", list);
	}
	
	public List<FileVO> selectFileByBno(int bno){
		return template.selectList("fileMapper.selectFileByBno", bno);
	}
}
