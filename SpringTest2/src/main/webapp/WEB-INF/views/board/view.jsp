<%@page import="com.example.ezen.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	//1. request.getAttribute로 데이터 받기
	BoardVO board = (BoardVO)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>게시글번호 : ${board.bno}</div>
	<div>제목 : ${board.title}</div>
	<div>본문 : ${board.body}</div>
	<div>작성자 : ${board.user.nick}</div>
	<div>작성일 : ${board.createDate}</div>
	
	<div>
		<c:forEach var="file" items="${board.fileList}">
			<div>
				<a  download="${file.originalName}" 
					href="<c:url value="/uploads/${file.savedName}" />">
					${file.originalName}
				</a><br>
				
				<c:if test="${fn:contains(file.contentType, 'image')}">
					<img  width="300px"
					  src="<c:url value="/uploads/${file.savedName}" />">
				</c:if>
				
			</div>
		</c:forEach>
	</div>
	
	<!-- 게시글 작성자와, 로그인한 사용자의 정보가 동일해야 수정삭제 버튼이 보임 -->
	<c:if test="${sessionScope.user.id == board.author}">
		<input type="button" value="수정" onclick="moveModifyPage(${board.bno})">
		<form 
			action="<c:url value="/board/delete/${board.bno}" />" 
			method="post">
			<input type="submit" value="삭제">
		</form>
	</c:if>
</body>
<script>
	let bno = ${board.bno};
	console.log(bno);
	
	let title = "${board.title}";
	console.log(title);
	
	function moveModifyPage(bno){
		location.href="${pageContext.request.contextPath}/board/modify/"+bno;
	}
</script>
</html>