package com.example.ezen.board;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.ezen.HomeController;
import com.example.ezen.file.FileRepository;
import com.example.ezen.file.FileVO;
import com.example.ezen.user.UserVO;

//�Խ��ǿ� ���� ��� ��û�� ó���ϴ� ��Ʈ�ѷ�
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//1.�Խñ� �ۼ�ȭ�� ��Ʈ�ѷ�
	//��û�� ������ jspȭ������ ������
	
	//@RequestMapping("/write")
	///write get, post��û�� ���� �� ����
	
	//SqlSessionTemplate�� �ƴ� BoardRepository�� �̿��ؼ� �����ͺ��̽��� ����
	
	//������������ �޾Ƽ� ���ԵǴ� �ʵ�� �������̽� Ÿ������ ����
	//BoardServiceImpl�� ���Եɼ���, OracleService�� ���Եɼ��� �ֱ� ������
	//�ʵ�� Ư��Ÿ���� �����ϸ� ������ �����
	
	//���������� autowired�� ���� ������̼����� �������� ������ ��
	//����� ����(�ʵ�, �Ķ����)Ÿ���� �̿��ؼ� ã�Ƽ� �����Ѵ�.
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//�������� BoardController �����ڸ� ȣ���ϸ鼭 SqlSessionTemplate
	//Ÿ���� ���� �̹� �����Ѵٸ� �Ķ���ͷ� �־��ش�.
	//root-context.xml���� ���� ����ϰ� ����
	
	

	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session){
		//session�� userŰ�� ��� �����Ͱ� null���� �ƴ���
//		if(session.getAttribute("user") != null) {
//			return "board/write";
//		}else {
//			return "redirect:/board/boards";
//		}
		return "board/write";
		///WEB-INF/views/board/write.jsp�� ������
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePost(@ModelAttribute BoardVO vo, 
			@RequestParam("file") List<MultipartFile> file,
			@SessionAttribute("user") UserVO user
	) throws IllegalStateException, IOException {
		
		vo.setAuthor(user.getId());
		boardService.insertBoardOne(vo, file);
		
		//ȭ�鿡�� �����ϴ� ������ ���� name�Ӽ��� ���� Ư�� Ŭ������
		//�ʵ� �̸��� �����ϴٸ� �Ķ���ͷ� �ش�Ŭ���� Ÿ���� ������
		//Ŭ������ �����ڸ� ȣ���ϰ� set�޼��带 �̿��� �Ķ���Ͱ��� �־��ش�.
		//BoardVO vo = new BoardVO();
		//vo.setTitle(reuqest.getParameter("title"));
		//vo.setBody(reuqest.getParameter("body"));
		//�����͹��ε�
		
		//localhost:8080/c/board/write?text=2
		//String text = request.getParameter("text")
		//DI -> Dependency Injection(������ ����)
		//�������� IOC�����̳ʿ��� �����ϰ��ִ� Bean���� ���� �������� �ʿ��� ���� ������
		//���������� �˾Ƽ� �Ķ���ͷ� �������� �������ش�.
		
		//ó�� -> �۾��� ó�� -> write.jsp���� �Ѱ��� �����͸� �޾Ƽ�
		//�����ͺ��̽��� insert
		//template.insert("boardMapper.insertBoardOne", vo);
		
		//���� ���ε�
		//���� �μ�Ʈ
		
		
		//redirect -> �Խñ� ����ȭ��
		//PRG���� -> Post Redirect Get
		//Post�޼���� ��û�̿��� ó���ϰ�, Get����� �������� redirect
		return "redirect:/board/boards/"+vo.getBno();
		// /board/view url�� ��û�ض� ��� ����� ���������� ����
	}
	
	//2. �Խñ� ����ȭ�� ��Ʈ�ѷ�
	//��û�� ������ jspȭ������ ������
	@RequestMapping(value="/modify/{bno}", method=RequestMethod.GET)
	public String modify(
			@PathVariable("bno") int bno,
			Model model
	) {
		
		BoardVO vo = boardService.selectBoardByBno(bno);
		model.addAttribute("board", vo);
		return "board/modify";
		///WEB-INF/views/board/modify.jsp�� ������
	}
	
	@RequestMapping(value="/modify/{bno}", method=RequestMethod.POST)
	public String modifyPost(
			@PathVariable("bno") int bno,
			BoardVO vo
		) {
		vo.setBno(bno);
		boolean result = boardService.updateBoardOne(vo);
		if(!result) {
			return "redirect:/board/boards/";
		}
		//ȭ�鿡�� ������ �����͸� �ް�
		//�����ͺ��̽����� updateó��
		//�ݹ� ������ �Խñ۷� �����̷�Ʈ
		return "redirect:/board/boards/"+bno;
	}
	
	//3. �Խñ� ���ȭ�� ��Ʈ�ѷ�
	//��û�� ������ jspȭ������ ������
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public String list(Model model) throws ClassNotFoundException, SQLException {
		//�������� �Խñ��� ��ȸ�ؼ� jsp�� ������ �� �� �����͸� ����ش�.
		
		//�������� BoardVO�� �������� ������ Ÿ��
		//1. �迭
		//������ ������ �ִ�.(��Ȯ�� �Խñ� ������ �𸣸� ��� �Ұ�)

		//2. ����Ʈ
		//����
		
		List<BoardVO> list = boardService.selectAllBoard();
		//�������丮�� ���� �������� �ʰ� ���� ���̾ ���� �������丮�� ����
		
		//���� �� boardMapper namespace�� id�� selectAllBoard�� �ش��ϴ�
		//sql�� �����ϰ� ������� �ڹٰ�ü�� ��ȯ�Ͽ� �����ش�.
		
		model.addAttribute("boardList", list);
		
		return "board/list";
	}
	
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.GET)
	public String view(
		@PathVariable("bno") int bno,
		Model model
	) {
		//@RequestParam
		//1. �Ķ������ �ʼ� ���θ� ������ �� �ִ�.
		//2. �Ķ���͸� ���ڿ��� �ƴ� �ٸ� Ÿ������ ���� �� �ִ�.
		//3. �Ķ���Ϳ� ���� ���� ��� �⺻���� �־��� �� �ִ�.
		//4. �Ķ���ͷ� �Ѿ�� urlŰ���� �Ķ���� ������ �����ϴٸ� ������̼��� ��� �ȴ�.
		
		//@PathVariable
		//�ش� url�� �����ϱ� ���� �ݵ�� �ʿ��� �Ķ���Ͱ� �ִٸ� PathVariable��
		//�����Ѵ�.
		
		logger.info("bno : {}", bno);
		//�Խñ� �� ��ȸ(�Ѱ�)
		//�����ͺ��̽� ���� �ؼ� �Խñ� �� ��ȸ
		//��ȸ�� �Խñ� �����͸� jsp�� ������ �ϱ� ���� setAttribute
		//����, ����, �ۼ���, ��¥
		BoardVO vo = boardService.selectBoardByBno(bno);
		
		model.addAttribute("board", vo);
		return "board/view";
	}
	
	//���� ��Ʈ�ѷ�
	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	public String delete(
			@PathVariable("bno") int bno,
			@SessionAttribute("user") UserVO user
	) {
		//delete from board where bno = ?
		int result = boardService.deleteBoardOne(bno, user.getId());
		if(result <= 0) {
			return "redirect:/board/boards/"+bno;
		}
		
		return "redirect:/board/boards";
	}
	
	//~~�� �� �������� �ڵ����� �޼��带 ȣ��
	//����
	//AOP(Aspect Oriented Programming, ���� ���� ���α׷���)
	// /view�� ��û�� "��" �������� 
	//IOC�����̳ʿ� ����ִ� BoardController�� view�޼��带 ��Ž���
	
}
