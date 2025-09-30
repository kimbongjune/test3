package com.example.ezen.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ezen.Response;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	//회원가입
	//1. 회원가입 화면
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	//@GetMapping("/signup")
	public String signUp() {
		String text = "1234";
		String encText = passwordEncoder.encode(text);
		System.out.println(text);
		System.out.println(encText);

		System.out.println(passwordEncoder.matches(text, encText));
		
		return "user/signup";
	}

	//2. 회원가입 처리
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(UserVO vo) {
		//레파지토리 이용해서 insert처리
		userService.insertUsersOne(vo);
		return "redirect:/user/login";
	}
	
	//로그인
	//1. 로그인 화면
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	//2. 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		//1. 화면에서 전달한 데이터를 받아 회원정보가 있는지 조회
		
		//스프링 암호화는 암호화 할 때 마다 값이 매번 달라지기 때문에 id, pw일치 데이터 조회 쿼리로는 로그인 불가능
		
		//select * from user where id = ?;
		//조회한 pw와, 사용자가 입력한 pw가 match함수의 결과로 true가 나오면 로그인 아니면 실패
		UserVO user = userService.selectUsersOne(vo);
		//user가 null이면? 로그인 정보 불일치
		//user가 null이 아니면? 로그인 성공
		
		if(user == null) {
			return "redirect:/user/login";
		}
		//2. 회원정보가 있으면 로그인 처리(session)
		session.setAttribute("user", user);
		//3. 회원정보가 없으면 다시 로그인페이지로
		return "redirect:/";
	}
	
	//로그아웃
	//1. 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		//1. 세션 만료
		session.invalidate();
		//2. 로그인페이지로 redirect
		return "redirect:/user/login";
	}
	
	//아이디 중복 검사
	@PostMapping("/id-check")
	@ResponseBody
	public Response idCheck(@RequestParam("id") String id) {
		System.out.println(id);
		
		//파라미터로 받은 id가 db에 존재하는지 확인
		int cnt = userService.selectUsersCntById(id);
		System.out.println(cnt);
		
		//이미 존재한다면 result는 "success" code는 1
		//존재하지 않는다면 result는 "success" code는 0
		//조회가 안되었다면 result는 "fail" code는 -1
		Response res = new Response();
		res.setCode(cnt);
		res.setResult("success");
		
		return res;
	}
	
	//회원정보 수정
	//1. 회원정보 수정 화면
	//2. 회원정보 수정 처리
	
	//회원탈퇴
	//1. 회원탈퇴 처리
}
