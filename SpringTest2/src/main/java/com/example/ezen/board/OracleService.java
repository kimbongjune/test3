package com.example.ezen.board;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//@Service
public class OracleService implements BoardService{
	public List<BoardVO> selectAllBoard(){
		return null;
	}
	
	public BoardVO selectBoardByBno(int bno) {
		return null;
	}

	@Override
	public boolean updateBoardOne(BoardVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteBoardOne(int bno, String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBoardOne(BoardVO vo, List<MultipartFile> file) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		
	}
}
