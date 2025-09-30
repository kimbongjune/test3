package com.example.ezen.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//컨트롤러에서 발생하는 예외를 처리하는 핸들러
@ControllerAdvice
//@ControllerAdvice(basePackages = {"a.b.c.board.BoardController"})
public class HandleControllerException {
	
	//예외처리 메서드
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String test(MethodArgumentTypeMismatchException e) {
		System.out.println("예외 발생");
		return "redirect:/board/boards";
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Model model) {
	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    model.addAttribute("errors", errors);
	    // 필요한 경우 입력값도 다시 넘겨주기
	    model.addAttribute("human", ex.getBindingResult().getTarget());

	    return "signupForm"; // 에러 뷰 이름
	}

}
