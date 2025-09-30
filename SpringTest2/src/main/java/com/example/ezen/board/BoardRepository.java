package com.example.ezen.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//model
//데이터베이스에 접근하는 클래스
//컨트롤러에서 호출
//마이바티스를 이용해서 쿼리를 실행하고 결과값을 컨트롤러에 돌려준다.
@Repository
public class BoardRepository {
	
	private final SqlSession template;

	@Autowired
	public BoardRepository(SqlSession template) {
		this.template = template;
	}
	
	//컨트롤러에서 호출할 메서드
	//1. 게시글 목록조회
	public List<BoardVO> selectAllBoard(){
		return template.selectList("boardMapper.selectAllBoard");
	}
	
	//3. 게시글 단건조회
	public BoardVO selectBoardByBno(int bno){
		return template.selectOne("boardMapper.selectBoardByBno", bno);
	}
	
	//2. 게시글 입력
	public int insertBoardOne(BoardVO vo){
		return template.insert("boardMapper.insertBoardOne", vo);
	}
	
	//4. 게시글 수정
	public int updateBoardOne(BoardVO vo){
		return template.update("boardMapper.updateBoardOne", vo);
	}
	
	//5. 게시글 삭제
	public int deleteBoardOne(int bno){
		return template.delete("boardMapper.deleteBoardOne", bno);
	}
}
