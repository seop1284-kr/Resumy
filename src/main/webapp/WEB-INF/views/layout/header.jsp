<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link href="/img/fibicon.png" rel="shortcut icon" type="image/x-icon">
<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/mypageTemplate.css" rel="stylesheet">

<!-- font-awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<!-- font-family: 'Gowun Dodum', sans-serif; -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<!-- font-family: 'Roboto Condensed', sans-serif; -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/assets/dist/js/bootstrap.min.js"></script>
<!-- bootrstap 을 따르는 문서 -->



<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

<link href="/css/navbar.css" rel="stylesheet">

<%
String headerMenu = "main";

String header_menu_param = request.getParameter("headerMenu");
if (header_menu_param != null) {
	try {
		headerMenu = header_menu_param;
	} catch (NumberFormatException e) {

	}
}
%>


<!-- a tag post -->
<form name=form1 action="/" method=put id="company">
	<input type=hidden name="headerMenu" value="company"> <input
		type=hidden name="content" value="companyBoard">
</form>
<form name=form2 action="/" method=put id="fed">
	<input type=hidden name="headerMenu" value="fed"> <input
		type=hidden name="content" value="fedBoard">
</form>
<form name=form3 action="/main/qna/board.do" method=put id="qna">
	<input type=hidden name="headerMenu" value="qna"> <input
		type=hidden name="content" value="main/qna/board.do">

</form>



<!-- header 메뉴 시작 -->
<header>
	<nav class="navbar navbar-expand-md fixed-top">
		<div class="container-md">
			<a class="navbar-brand" href="/"> <img src="/img/logo_main.png">
			</a>
			<ul>
				<li><a
					class="<%=(headerMenu.equals("company")) ? "active" : ""%>"
					onclick="company.submit()">기업정보</a></li>
				<li><a class="<%=(headerMenu.equals("fed")) ? "active" : ""%>"
					onclick="fed.submit()">자소서 게시판</a></li>
				<li><a class="<%=(headerMenu.equals("qna")) ? "active" : ""%>"
					onclick="qna.submit()">고객센터</a></li>
			</ul>

			<!-- 비로그인 -->
			<sec:authorize access="isAnonymous()">
				<button class="login_btn" onclick="location.href='/login'">로그인</button>
			</sec:authorize>

			<!-- 로그인 -->
			<sec:authorize access="isAuthenticated()">

				<button class="login_btn" onclick="location.href='/myp'">
					<div style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
					<sec:authentication property="principal.username"/></div>
				</button>
			</sec:authorize>



		</div>
	</nav>
</header>
<!-- header 메뉴 끝 -->




