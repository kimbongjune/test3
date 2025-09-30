package com.example.ezen.board;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//��Ʈ�ѷ��� ����Ŭ�������� ���յ��� ���߱� ���� �������̽��� ���
//���յ� : Ŭ�������� �������� �ʹ� ���ϰ� �����ִ� ����
//���յ��� ���߸�? ��Ʈ�ѷ� Ŭ������ �������� �ʰ�, ���ԵǴ� ����Ŭ������ ���氡��

//�޼����� �ñ״���(�̸�, �Ķ����Ÿ��, ��ȯŸ��)�� ���� ����Ŭ�������� �����ϰ� �ϱ�����
public interface BoardService {
	//�Խñ� ��� ��ȸ �߻�޼���
	List<BoardVO> selectAllBoard();
	
	//�Խñ� �ܰ� ��ȸ �߻�޼���
	BoardVO selectBoardByBno(int bno);
	
	//�Խñ� ���� �߻�޼���
	boolean updateBoardOne(BoardVO vo);
	
	//�Խñ� ���� �߻�޼���
	int deleteBoardOne(int bno, String id);
	
	//�Խñ� �ۼ� �߻�޼���
	void insertBoardOne(BoardVO vo, List<MultipartFile> file)
		throws IllegalArgumentException, IOException;
}
