<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
<style>
	#idValid{
		display : none;
	}
</style>
<script src="<c:url value="/resources/js/jquery-3.7.1.min.js" />"></script>

<script>
	//1. id입력창에 키보드 입력이 발생하면 해당 id를 서버로 전송
	//2. 서버에서 전송받은 id를 이용해 중복된 id가 db에 존재하는지 확인
	//3. db에서 확인 후 응답
	//4. 응답을 받은 후 응답 결과에 따라 사용자에게 피드백
	
	$(document).ready(function(){
		//html문서가 전부 다 로딩되면 실행되는 함수
		
		//아이디 중복확인이 되었는지 검사
		let idCheckFlag = false;
		
		$("input[name='id']").keyup(function(){
			idCheckFlag = false;
			//입력된 텍스트를 id변수에 대입
			let id = $(this).val();
			console.log(id);
			
			if(id.trim() == ""){
				return;
			}
			
			//id입력값을 서버(컨트롤러)로 전송
			$.ajax({
				url : "<c:url value='/user/id-check' />",
				type : "post",
				data : {
					"id" : id
				},
				//요청부분
				success : function(response){
					console.log(response.code);
					//response의 code가 1이면 가입불가 0이면 가능
					if(response.code == 1){
						$("#idValid")
							.show()
							.css("color", "red")
							.text("중복된 아이디가 존재합니다.");
						idCheckFlag = false;
					}else{
						$("#idValid")
							.show()
							.css("color", "green")
							.text("사용가능한 아이디 입니다.");
						idCheckFlag = true;
					}
				},
				error : function(){
					console.log("에러")
					idCheckFlag = false;
				}
			});
			
		});
		
		//폼 submit 검증 함수
		$("form").submit(function(){
			//아이디 비밀번호 닉네임 빈값 검증
			//아이디 중복확인 검증
			if(!idCheckFlag){
				return false;
			}
			
			return true;
		});
	});
	
</script>
</head>
<body>
	<form action="<c:url value="/user/signup" />" method="post">
		<input type="text" name="id"><br>
		<div id="idValid"></div>
		<!-- id -> Id -> Id() -> setId() -->
		<input type="text" name="pw"><br>
		<input type="text" name="nick"><br>
		<input type="submit">
	</form>
</body>
</html>