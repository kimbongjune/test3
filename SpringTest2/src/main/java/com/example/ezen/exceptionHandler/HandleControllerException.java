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

//��Ʈ�ѷ����� �߻��ϴ� ���ܸ� ó���ϴ� �ڵ鷯
@ControllerAdvice
//@ControllerAdvice(basePackages = {"a.b.c.board.BoardController"})
public class HandleControllerException {
	
	//����ó�� �޼���
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String test(MethodArgumentTypeMismatchException e) {
		System.out.println("���� �߻�");
		return "redirect:/board/boards";
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Model model) {
	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    model.addAttribute("errors", errors);
	    // �ʿ��� ��� �Է°��� �ٽ� �Ѱ��ֱ�
	    model.addAttribute("human", ex.getBindingResult().getTarget());

	    return "signupForm"; // ���� �� �̸�
	}

}
