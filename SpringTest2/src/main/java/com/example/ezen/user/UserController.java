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

	//ȸ������
	//1. ȸ������ ȭ��
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

	//2. ȸ������ ó��
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(UserVO vo) {
		//�������丮 �̿��ؼ� insertó��
		userService.insertUsersOne(vo);
		return "redirect:/user/login";
	}
	
	//�α���
	//1. �α��� ȭ��
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	//2. �α��� ó��
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		//1. ȭ�鿡�� ������ �����͸� �޾� ȸ�������� �ִ��� ��ȸ
		
		//������ ��ȣȭ�� ��ȣȭ �� �� ���� ���� �Ź� �޶����� ������ id, pw��ġ ������ ��ȸ �����δ� �α��� �Ұ���
		
		//select * from user where id = ?;
		//��ȸ�� pw��, ����ڰ� �Է��� pw�� match�Լ��� ����� true�� ������ �α��� �ƴϸ� ����
		UserVO user = userService.selectUsersOne(vo);
		//user�� null�̸�? �α��� ���� ����ġ
		//user�� null�� �ƴϸ�? �α��� ����
		
		if(user == null) {
			return "redirect:/user/login";
		}
		//2. ȸ�������� ������ �α��� ó��(session)
		session.setAttribute("user", user);
		//3. ȸ�������� ������ �ٽ� �α�����������
		return "redirect:/";
	}
	
	//�α׾ƿ�
	//1. �α׾ƿ� ó��
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		//1. ���� ����
		session.invalidate();
		//2. �α����������� redirect
		return "redirect:/user/login";
	}
	
	//���̵� �ߺ� �˻�
	@PostMapping("/id-check")
	@ResponseBody
	public Response idCheck(@RequestParam("id") String id) {
		System.out.println(id);
		
		//�Ķ���ͷ� ���� id�� db�� �����ϴ��� Ȯ��
		int cnt = userService.selectUsersCntById(id);
		System.out.println(cnt);
		
		//�̹� �����Ѵٸ� result�� "success" code�� 1
		//�������� �ʴ´ٸ� result�� "success" code�� 0
		//��ȸ�� �ȵǾ��ٸ� result�� "fail" code�� -1
		Response res = new Response();
		res.setCode(cnt);
		res.setResult("success");
		
		return res;
	}
	
	//ȸ������ ����
	//1. ȸ������ ���� ȭ��
	//2. ȸ������ ���� ó��
	
	//ȸ��Ż��
	//1. ȸ��Ż�� ó��
}
