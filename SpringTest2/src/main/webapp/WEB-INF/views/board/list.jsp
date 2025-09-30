<%@page import="com.example.ezen.board.BoardVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ListBoardVOO> list 
		= (ListBoardVOO>)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty sessionScope.user}">
		<a href="<c:url value="/board/write" />">글쓰기</a>
		<a href="${pageContext.request.contextPath}/board/write">글쓰기</a>
	</c:if>
	<table>
		<tr>
			<th>게시글번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bno}</td>
				<td>
					<a href="<c:url value="/board/boards/${board.bno}" />">
						${board.title}
					</a>
				</td>
				<td>${board.author}</td>
			</tr>
		</c:forEach>
		<%--
			for(int i = 0; i < list.size(); i ++){
				BoardVO vo = list.get(i);
				%>
					<tr>
						<td><%= vo.getBno() %></td>
						<td><%= vo.getTitle() %></td>
						<td><%= vo.getAuthor() %></td>
					</tr>
				<%
			}
		--%>
	</table>
</body>
</html>