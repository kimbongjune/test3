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
	//소프트웨어(프로그램)들이 통신하고 데이터를 교환할 수 있도록 하는 프로토콜의 집합
	
	//REST API(Representation State Transfer API)
	//자원(데이터)을 이름(url)로 구분하여 자원의 상태를 주고받는 모든 것
	
	//boards(게시글) 자원들을 get(요구)
	//게시글 목록 조회 -> 데이터만 반환
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public List<BoardVO> boards(){
		return repository.selectAllBoard();
	}
	
	//boards(게시글) 자원 하나를 get(요구)
	//게시글 단건 조회
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.GET)
	public BoardVO board() {
		return null;
	}
	
	//boards(게시글) 자원 하나에 대해서 PUT(수정) // PATCH(수정)
	//게시글 한건 수정
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.PUT)
	public int updateBoard() {
		return 0;
	}
	
	//boards(게시글) 자원 하나에 대해서 DELETE(삭제)
	//게시글 한건 삭제
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.DELETE)
	public int deleteBoard() {
		return 0;
	}
	
	//boards(게시글) 자원에 대해서 POST(입력)
	//게시글 작성
	@RequestMapping(value="/boards", method=RequestMethod.POST)
	public int insertBoard() {
		return 0;
	}
	
	@RequestMapping("/a")
	public String a() {
		return "데이터.....";
	}
	
	@GetMapping("/human")
	public String t(@Validated @ModelAttribute Human h) {
		return "a";
	}
}
