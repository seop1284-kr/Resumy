<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
	<form name=form1 action="/companyBoard" method=put id="company">
		<input type=hidden name="headerMenu" value="company">
	</form>
	<form name=form2 action="/fedBoard" method=put id="fed">
		<input type=hidden name="headerMenu" value="fed">
	</form>
	<form name=form3 action="/main/qna/board.do" method=put id="qna">
		<input type=hidden name="headerMenu" value="qna">
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
	
				<%-- 비로그인 --%>
				<sec:authorize access="isAnonymous()">
					<button class="login_btn" onclick="location.href='/login'">로그인</button>
				</sec:authorize>
	
				<%-- 로그인 --%>
				<sec:authorize access="isAuthenticated()">
	
					<button class="login_btn" onclick="location.href='/myp'">
						<div style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
						<sec:authentication property="principal.username"/></div>
					</button>
				</sec:authorize>
	
			</div>
			<!-- ./container-md -->
			
		</nav>
		
	</header>
	<!-- header 메뉴 끝 -->