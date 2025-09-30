<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>JSTL(JSP Standard Tag Library, jsp 표준 태그 라이브러리)</div>
	<div>
		JSTL을 이용하면 태그를 이용해 조건문, 변수, 반복문 등을 사용할 수 있다.
	</div>
	
	<div>1. 값 출력</div>
	<c:out value="hi" /><br>
	<c:out value="${55}"/><br>
	<c:out value="${null}" default="null값" /><br>
	
	<div>2. 변수(객체)</div>
	<c:set var="data" value="hello" />
	${data}
	<c:set var="data2" value="${data}" />
	${data}
	
	<div>3. url : 톰캣의 contextpath가 반영된 url경로를 반환한다</div>
	<c:url value="/home"/><br>
	
	<a href="/">홈1</a><br>
	<a href="<%= request.getContextPath() %>/">홈2</a><br>
	<a href="<c:url value="/" />">홈3</a><br>
	
	<div>4. if</div>
	<c:if test="${false}">
		<div>true!</div>
	</c:if>
	
	user : null or ""
	
	<c:if test="${not empty sessionScope.user}">
		<div>로그인 함</div>
	</c:if>
	
	<div>5. if else-if else</div>
	<div>choose when otherwise</div>
	<c:set var="v" value="5" />
	만약 
	v가 1이면 "금메달" 그렇지 않고 
	v가 2이면 "은메달" 그렇지 않고 
	v가 3이면 "동메달" 그렇지 않다면 등외
	
	<c:choose>
		<c:when test="${v == 1}">
			<div>금메달</div>
		</c:when>
		<c:when test="${v == 2}">
			<div>은메달</div>
		</c:when>
		<c:when test="${v == 3}">
			<div>동메달</div>
		</c:when>
		<c:otherwise>
			<div>등외</div>
		</c:otherwise>
	</c:choose>
	
	<div>6. 반복문</div>
	<c:forEach var="s" begin="0" end="5">
		${s}
	</c:forEach>
	<br>
	
	<c:forEach var="s" begin="1" end="5" step="2">
		${s}
	</c:forEach>
	<br>
	
	<c:forEach var="s" items="${list}">
		${s}
	</c:forEach>
	<br>
	
	<c:forEach var="s" items="${array}">
		${s}
	</c:forEach>
	
</body>
</html>