<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
</head>
<body>
<h1>회원가입 페이지</h1>
<hr>
<form action="/joinOk" method="POST">
	<input type="text" name="userid" placeHolder="아이디 입력"/><br>
	<input type="password" name="pw" placeHolder="패스워드 입력"/><br>
	<input type="text" name="name" placeHolder="이름 입력"/><br>
	<input type="email" name="email" placeHolder="이메일 입력"/><br>
	
	<input type="submit" value="회원가입">
</form>
</body>
</html>