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

//��Ʈ�ѷ����� ȣ���Ͽ� ����Ͻ�����(�����ڵ�)
//���Ͼ��ε�, ����ó�� ���� �����ϴ� Ŭ����
//repository�� �����Ͽ� db CRUD�۾� ����

//���̾�� ��Ű����(����)
//controller <-> service <-> repository

//controller <--X--> repository

//���� ���̾��� �ʿ伺
//������ �д�(�����ڰ� ���������ϱ� ���ϰ�)
//��û�� ������ controller
//�����ͺ��̽� ���ٰ� ������ ��ȯ�� repository
//�����ڰ� �����ؾ��ϴ� ����Ͻ������� service
//����Ͻ����� : �䱸������ �ڵ�� �����ϴ°�
//ex) ������ �� �ܾ��� ����ؾ��Ѵ�.
//ex2) ���θ����� ��� �־�� �ֹ��� �����ϴ�
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
	
	//�Խñ� ��� ��ȸ
	public List<BoardVO> selectAllBoard(){
		return boardRepository.selectAllBoard();
	}
	
	//�Խñ� �ܰ� ��ȸ
	public BoardVO selectBoardByBno(int bno){
		if(bno <= 0) {
			return null;
		}
		return boardRepository.selectBoardByBno(bno);
	}
	
	//�Խñ� ����ó��
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
	
	//�Խñ� ����ó��
	public int deleteBoardOne(int bno, String id) {
		//�Խñ� �ۼ����� id��, ������û�� ����� id�� ������ Ȯ��
		BoardVO vo = boardRepository.selectBoardByBno(bno);
		if(!vo.getAuthor().equals(id)) {
			return 0;
		}
		return boardRepository.deleteBoardOne(bno);
	}
	
	//�Խñ� �ۼ� + ���Ͼ��ε� + ���� inert
	@Transactional
	//������ Ʈ�����
	//Ʈ����� : �����ͺ��̽����� ����Ǵ� �۾� ����
	//���� �޼��� ���ο��� ȣ���ϴ� �ΰ��� ����(�Խñ� insert, ���� insert)��
	//�ϳ��� �۾������� ���� ������̼�
	//�� �� �ϳ��� �����ϸ� �Ѵ� rollback(���)��Ŵ
	public void insertBoardOne(BoardVO vo, List<MultipartFile> file) throws IllegalStateException, IOException {
		//÷������ ���ε带 ���� ���� ����
		String path = context.getRealPath("/uploads/");
		//~~~~~~~/webapp/uploads/
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//�Խñ� insert
		boardRepository.insertBoardOne(vo);
		
		//÷������ ���ε�
		List<FileVO> list = new ArrayList<>();
		
		for(MultipartFile f : file) {
			if(f.isEmpty()) {
				continue;
			}
			//���� ���ε�
			
			String originalName = f.getOriginalFilename();
			String ext 
				= originalName.substring(originalName.lastIndexOf("."));
			//image - ���纻.jpg
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
