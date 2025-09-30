package com.example.ezen;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ezen.board.BoardVO;
import com.example.ezen.user.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//DI 의존성주입
	//1. 필드주입
	//2. 생성자주입
	//3. setter주입
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		UserVO user = (UserVO)session.getAttribute("user");
		System.out.println(user);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/el", method = RequestMethod.GET)
	public String el(
			HttpSession session, HttpServletRequest request, Model model) 
	{
		logger.info("session 비교 : {}", session == request.getSession());
		session.setAttribute("user", "hong");
		model.addAttribute("user", "테스트");
		return "el";
	}
	
	@RequestMapping(value = "/jstl", method = RequestMethod.GET)
	public String jstl(Model model) {
		
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		String[] array = {"a", "b", "c", "d"};
		
		model.addAttribute("list", list);
		model.addAttribute("array", array);
		
//		for(int i = 0; i < list.size(); i ++) {
//			System.out.println(list.get(i));
//		}
//		
//		for(String s : list) {
//			System.out.println(s);
//		}
		
		
//		for(int i = 0; i < array.length; i ++) {
//			System.out.println(array[i]);
//		}
//		
//		for(String s : array) {
//			System.out.println(s);
//		}
		
		return "jstl";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public BoardVO test() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목1");
		vo.setBody("본문1");
		vo.setAuthor("hong");
		vo.setBno(1);
		return vo;
	}
	
}
