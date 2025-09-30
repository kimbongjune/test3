package com.example.ezen.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.ezen.file.FileRepository;
import com.example.ezen.file.FileVO;

//컨트롤러에서 호출하여 비즈니스로직(개발코드)
//파일업로드, 결제처리 등을 실행하는 클래스
//repository와 연결하여 db CRUD작업 수행

//레이어드 아키텍쳐(구조)
//controller <-> service <-> repository

//controller <--X--> repository

//서비스 레이어의 필요성
//역할의 분담(개발자가 유지보수하기 편하게)
//요청과 응답은 controller
//데이터베이스 접근과 데이터 반환은 repository
//개발자가 구현해야하는 비즈니스로직은 service
//비즈니스로직 : 요구사항을 코드로 구현하는거
//ex) 결제할 때 잔액이 충분해야한다.
//ex2) 쇼핑몰에서 재고가 있어야 주문이 가능하다
@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	private final ServletContext context;
	private final FileRepository fileRepository;
	
	@Autowired
	public BoardServiceImpl(
			BoardRepository boardRepository,
			ServletContext context,
			FileRepository fileRepository
	) {
		this.boardRepository = boardRepository;
		this.context = context;
		this.fileRepository = fileRepository;
	}
	
	//게시글 목록 조회
	public List<BoardVO> selectAllBoard(){
		return boardRepository.selectAllBoard();
	}
	
	//게시글 단건 조회
	public BoardVO selectBoardByBno(int bno){
		if(bno <= 0) {
			return null;
		}
		return boardRepository.selectBoardByBno(bno);
	}
	
	//게시글 수정처리
	public boolean updateBoardOne(BoardVO vo){
		if(vo.getTitle() == null || vo.getTitle().equals("")) {
			return false;
		}
		int result = boardRepository.updateBoardOne(vo);
		if(result <= 0) {
			return false;
		}else {
			return true;
		}
	}
	
	//게시글 삭제처리
	public int deleteBoardOne(int bno, String id) {
		//게시글 작성자의 id랑, 삭제요청한 사람의 id랑 같은지 확인
		BoardVO vo = boardRepository.selectBoardByBno(bno);
		if(!vo.getAuthor().equals(id)) {
			return 0;
		}
		return boardRepository.deleteBoardOne(bno);
	}
	
	//게시글 작성 + 파일업로드 + 파일 inert
	@Transactional
	//선언적 트랜잭션
	//트랜잭션 : 데이터베이스에서 실행되는 작업 단위
	//서비스 메서드 내부에서 호출하는 두가지 쿼리(게시글 insert, 파일 insert)를
	//하나의 작업단위로 묶는 어노테이션
	//둘 중 하나라도 실패하면 둘다 rollback(취소)시킴
	public void insertBoardOne(BoardVO vo, List<MultipartFile> file) throws IllegalStateException, IOException {
		//첨부파일 업로드를 위한 폴더 생성
		String path = context.getRealPath("/uploads/");
		//~~~~~~~/webapp/uploads/
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//게시글 insert
		boardRepository.insertBoardOne(vo);
		
		//첨부파일 업로드
		List<FileVO> list = new ArrayList<>();
		
		for(MultipartFile f : file) {
			if(f.isEmpty()) {
				continue;
			}
			//파일 업로드
			
			String originalName = f.getOriginalFilename();
			String ext 
				= originalName.substring(originalName.lastIndexOf("."));
			//image - 복사본.jpg
			//indexOf(".") -> 11
			//lastIndexOf(".") -> 11
			//subString(11)
			
			String savedName = UUID.randomUUID().toString() + ext;
			long fileSize = f.getSize();
			String contentType = f.getContentType();
			
			f.transferTo(new File(path+savedName));
			//~~~~~/webapp/uploads/04afb2af-c19c8e4c.jpg
			
			FileVO fileVO = new FileVO();
			fileVO.setBno(vo.getBno());
			fileVO.setContentType(contentType);
			fileVO.setFileSize(fileSize);
			fileVO.setOriginalName(originalName);
			fileVO.setSavedName(savedName);
			fileVO.setUploadPath(path);
			
			list.add(fileVO);
		}
		
		if(!list.isEmpty()) {
			fileRepository.insertFiles(list);
		}
	}
}
