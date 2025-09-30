package com.example.ezen.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//model
//�����ͺ��̽��� �����ϴ� Ŭ����
//��Ʈ�ѷ����� ȣ��
//���̹�Ƽ���� �̿��ؼ� ������ �����ϰ� ������� ��Ʈ�ѷ��� �����ش�.
@Repository
public class BoardRepository {
	
	private final SqlSession template;

	@Autowired
	public BoardRepository(SqlSession template) {
		this.template = template;
	}
	
	//��Ʈ�ѷ����� ȣ���� �޼���
	//1. �Խñ� �����ȸ
	public List<BoardVO> selectAllBoard(){
		return template.selectList("boardMapper.selectAllBoard");
	}
	
	//3. �Խñ� �ܰ���ȸ
	public BoardVO selectBoardByBno(int bno){
		return template.selectOne("boardMapper.selectBoardByBno", bno);
	}
	
	//2. �Խñ� �Է�
	public int insertBoardOne(BoardVO vo){
		return template.insert("boardMapper.insertBoardOne", vo);
	}
	
	//4. �Խñ� ����
	public int updateBoardOne(BoardVO vo){
		return template.update("boardMapper.updateBoardOne", vo);
	}
	
	//5. �Խñ� ����
	public int deleteBoardOne(int bno){
		return template.delete("boardMapper.deleteBoardOne", bno);
	}
}
