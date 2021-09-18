<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h1>로그인 페이지</h1>
<form action="/loginOk" method="POST"> <%-- loginProcessingUrl() 로 세팅한 url, 반드시 POST! --%>
	<%-- 일단 아이디/패스워드 의 name 은 'username' 과 'password' 로 하자 (시큐리티의 디폴트) --%>
	<%-- 만약 다른 name  을 사용하려면 SecurityConfig 에서 .usernameParameter("userid") 해주면 된다 --%>
	<input type="text" name="userid" placeholder="아이디 입력"/><br>
	<input type="password" name="password" placeholder="패스워드 입력"/><br>
	<button>로그인</button>
</form>
<a href="/join">회원가입을 아직 하지 않으셨나요</a>

</body>
</html>