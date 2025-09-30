package com.example.ezen.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ezen.Human;
import com.example.ezen.board.BoardRepository;
import com.example.ezen.board.BoardVO;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/rest")
public class TestController {
	
	private final BoardRepository repository;
	
	@Autowired
	public TestController(BoardRepository repository) {
		this.repository = repository;
	}

	//API(Application Programming Interface)
	//����Ʈ����(���α׷�)���� ����ϰ� �����͸� ��ȯ�� �� �ֵ��� �ϴ� ���������� ����
	
	//REST API(Representation State Transfer API)
	//�ڿ�(������)�� �̸�(url)�� �����Ͽ� �ڿ��� ���¸� �ְ�޴� ��� ��
	
	//boards(�Խñ�) �ڿ����� get(�䱸)
	//�Խñ� ��� ��ȸ -> �����͸� ��ȯ
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public List<BoardVO> boards(){
		return repository.selectAllBoard();
	}
	
	//boards(�Խñ�) �ڿ� �ϳ��� get(�䱸)
	//�Խñ� �ܰ� ��ȸ
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.GET)
	public BoardVO board() {
		return null;
	}
	
	//boards(�Խñ�) �ڿ� �ϳ��� ���ؼ� PUT(����) // PATCH(����)
	//�Խñ� �Ѱ� ����
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.PUT)
	public int updateBoard() {
		return 0;
	}
	
	//boards(�Խñ�) �ڿ� �ϳ��� ���ؼ� DELETE(����)
	//�Խñ� �Ѱ� ����
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.DELETE)
	public int deleteBoard() {
		return 0;
	}
	
	//boards(�Խñ�) �ڿ��� ���ؼ� POST(�Է�)
	//�Խñ� �ۼ�
	@RequestMapping(value="/boards", method=RequestMethod.POST)
	public int insertBoard() {
		return 0;
	}
	
	@RequestMapping("/a")
	public String a() {
		return "������.....";
	}
	
	@GetMapping("/human")
	public String t(@Validated @ModelAttribute Human h) {
		return "a";
	}
}
