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

//게시판에 대한 모든 요청을 처리하는 컨트롤러
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//1.게시글 작성화면 컨트롤러
	//요청을 받으면 jsp화면으로 포워딩
	
	//@RequestMapping("/write")
	///write get, post요청이 왔을 때 응답
	
	//SqlSessionTemplate이 아닌 BoardRepository를 이용해서 데이터베이스에 접근
	
	//의존성주입을 받아서 대입되는 필드는 인터페이스 타입으로 선언
	//BoardServiceImpl이 주입될수도, OracleService가 주입될수도 있기 때문에
	//필드로 특정타입을 선언하면 주입이 어려움
	
	//스프링에서 autowired와 같은 어노테이션으로 의존성을 주입할 때
	//선언된 변수(필드, 파라미터)타입을 이용해서 찾아서 주입한다.
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//스프링이 BoardController 생성자를 호출하면서 SqlSessionTemplate
	//타입의 빈이 이미 존재한다면 파라미터로 넣어준다.
	//root-context.xml에서 빈을 등록하고 있음
	
	

	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session){
		//session의 user키에 담긴 데이터가 null인지 아닌지
//		if(session.getAttribute("user") != null) {
//			return "board/write";
//		}else {
//			return "redirect:/board/boards";
//		}
		return "board/write";
		///WEB-INF/views/board/write.jsp로 포워딩
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePost(@ModelAttribute BoardVO vo, 
			@RequestParam("file") List<MultipartFile> file,
			@SessionAttribute("user") UserVO user
	) throws IllegalStateException, IOException {
		
		vo.setAuthor(user.getId());
		boardService.insertBoardOne(vo, file);
		
		//화면에서 전달하는 데이터 들의 name속성의 값이 특정 클래스의
		//필드 이름과 동일하다면 파라미터로 해당클래스 타입을 받으면
		//클래스의 생성자를 호출하고 set메서드를 이용해 파라미터값을 넣어준다.
		//BoardVO vo = new BoardVO();
		//vo.setTitle(reuqest.getParameter("title"));
		//vo.setBody(reuqest.getParameter("body"));
		//데이터바인딩
		
		//localhost:8080/c/board/write?text=2
		//String text = request.getParameter("text")
		//DI -> Dependency Injection(의존성 주입)
		//스프링의 IOC컨테이너에서 관리하고있는 Bean들은 서로 의존성이 필요할 때가 있으면
		//스프링에서 알아서 파라미터로 의존성을 주입해준다.
		
		//처리 -> 글쓰기 처리 -> write.jsp에서 넘겨준 데이터를 받아서
		//데이터베이스에 insert
		//template.insert("boardMapper.insertBoardOne", vo);
		
		//파일 업로드
		//파일 인서트
		
		
		//redirect -> 게시글 보기화면
		//PRG패턴 -> Post Redirect Get
		//Post메서드로 요청이오면 처리하고, Get방식의 페이지에 redirect
		return "redirect:/board/boards/"+vo.getBno();
		// /board/view url에 요청해라 라는 명령을 브라우저에게 응답
	}
	
	//2. 게시글 수정화면 컨트롤러
	//요청을 받으면 jsp화면으로 포워딩
	@RequestMapping(value="/modify/{bno}", method=RequestMethod.GET)
	public String modify(
			@PathVariable("bno") int bno,
			Model model
	) {
		
		BoardVO vo = boardService.selectBoardByBno(bno);
		model.addAttribute("board", vo);
		return "board/modify";
		///WEB-INF/views/board/modify.jsp로 포워딩
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
		//화면에서 전달한 데이터를 받고
		//데이터베이스에서 update처리
		//금방 수정한 게시글로 리다이렉트
		return "redirect:/board/boards/"+bno;
	}
	
	//3. 게시글 목록화면 컨트롤러
	//요청을 받으면 jsp화면으로 포워딩
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public String list(Model model) throws ClassNotFoundException, SQLException {
		//여러개의 게시글을 조회해서 jsp에 포워딩 할 때 데이터를 담아준다.
		
		//여러개의 BoardVO를 담을만한 데이터 타입
		//1. 배열
		//길이의 제한이 있다.(정확한 게시글 개수를 모르면 사용 불가)

		//2. 리스트
		//적합
		
		List<BoardVO> list = boardService.selectAllBoard();
		//레파지토리에 직접 접근하지 않고 서비스 레이어를 통해 레파지토리에 접근
		
		//매퍼 중 boardMapper namespace의 id가 selectAllBoard에 해당하는
		//sql을 실행하고 결과값을 자바객체로 변환하여 돌려준다.
		
		model.addAttribute("boardList", list);
		
		return "board/list";
	}
	
	@RequestMapping(value="/boards/{bno}", method=RequestMethod.GET)
	public String view(
		@PathVariable("bno") int bno,
		Model model
	) {
		//@RequestParam
		//1. 파라미터의 필수 여부를 지정할 수 있다.
		//2. 파라미터를 문자열이 아닌 다른 타입으로 받을 수 있다.
		//3. 파라미터에 값이 없을 경우 기본값을 넣어줄 수 있다.
		//4. 파라미터로 넘어온 url키값이 파라미터 변수와 동일하다면 어노테이션이 없어도 된다.
		
		//@PathVariable
		//해당 url로 접근하기 위해 반드시 필요한 파라미터가 있다면 PathVariable로
		//지정한다.
		
		logger.info("bno : {}", bno);
		//게시글 상세 조회(한건)
		//데이터베이스 연결 해서 게시글 상세 조회
		//조회한 게시글 데이터를 jsp에 포워딩 하기 전에 setAttribute
		//제목, 본문, 작성자, 날짜
		BoardVO vo = boardService.selectBoardByBno(bno);
		
		model.addAttribute("board", vo);
		return "board/view";
	}
	
	//삭제 컨트롤러
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
	
	//~~할 때 스프링이 자동으로 메서드를 호출
	//관점
	//AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)
	// /view로 요청할 "때" 스프링이 
	//IOC컨테이너에 들어있는 BoardController의 view메서드를 대신실행
	
}
