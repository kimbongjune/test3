package com.example.ezen.board;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//컨트롤러와 서비스클래스간의 결합도를 낮추기 위해 인터페이스를 사용
//결합도 : 클래스간의 의존성이 너무 강하게 묶여있는 정도
//결합도를 낮추면? 컨트롤러 클래스를 수정하지 않고, 주입되는 서비스클래스만 변경가능

//메서드의 시그니쳐(이름, 파라미터타입, 반환타입)을 하위 서비스클래스에서 동일하게 하기위해
public interface BoardService {
	//게시글 목록 조회 추상메서드
	List<BoardVO> selectAllBoard();
	
	//게시글 단건 조회 추상메서드
	BoardVO selectBoardByBno(int bno);
	
	//게시글 수정 추상메서드
	boolean updateBoardOne(BoardVO vo);
	
	//게시글 삭제 추상메서드
	int deleteBoardOne(int bno, String id);
	
	//게시글 작성 추상메서드
	void insertBoardOne(BoardVO vo, List<MultipartFile> file)
		throws IllegalArgumentException, IOException;
}
