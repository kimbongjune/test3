<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here!</title>
</head>
<body>
	<%-- 
		EL(Expression Language, 표현식) : <%=  %>을 대체하여 사용
		${}를 이용해 데이터를 꺼낼 수 있다.
		${}안에서 사용 가능한 기본 객체
		1. session
		2. request
		3. param
		el은 웹페이지 내에서 setAttribute로 넣은 값을 키만 이용해서 꺼낼 수 있다.
		순서
		jspContext -> ServletRequest -> HttpSession -> ServletContext
	--%>
	<div>${sessionScope.user}</div>
	<div>${user}</div>
	<!-- 
		el을 이용한 세션 접근
		request에서 꺼내오는 것에 대한 우선순위가 높기 때문에 위의 user는
		request.getAttribute("user")이다. 
	-->
	
	<div>${requestScope.user}</div>
	<div>${user}</div>
	<!-- el을 이용한 reqeust 접근 -->
	
	<div>${param.bno}</div>
	<!-- el을 이용한 request.getParameter -->
	
	정수 : ${2}<br>
	실수 : ${1.5}<br>
	논리 : ${true}<br>
	문자열 : ${"문자열1"}<br>
	문자열 : ${'문자열2'}<br>
	null : ${null}<br>
	<!-- el에서 null값은 빈문자 처리 -->
	<!-- el의 리터럴(값) 표현식 -->
	
	덧셈 : ${1 + 1}<br>
	뺄셈 : ${2 - 1}<br>
	곱셈 : ${4 * 4}<br>
	나눗셈 : ${4 / 2}<br>
	나눗셈 : ${4 div 2}<br>
	나머지 : ${4 % 2}<br>
	나머지 : ${5 mod 2}<br>
	<!-- el의 나눗셈은 실수로 계산 -->
	<!-- el의 산술연산 -->
	
	같다 : ${10 == 10}<br>
	같다 : ${10 eq 10}<br>
	같지않다 : ${10 != 10}<br>
	같지않다 : ${10 ne 10}<br>
	크다 : ${10 > 10}<br>
	크다 : ${10 gt 10}<br>
	크거나 같다 : ${10 >= 10}<br>
	크거나 같다 : ${10 ge 10}<br>
	작다 : ${10 < 10}<br>
	작다 : ${10 lt 10}<br>
	작거나 같다 : ${10 <= 10}<br>
	작거나 같다 : ${10 le 10}<br>
	<!-- el의 비교연산자 -->
	
	and : ${true && true}<br>
	and : ${true and true}<br>
	or : ${true || true}<br>
	or : ${true or true}<br>
	not : ${!true}<br>
	not : ${not true}<br>
	<!-- 논리연산자 -->
	
	${10 > 20 ? "크다" : "작다"}
	<!-- 삼항연산자 -->
</body>
</html>