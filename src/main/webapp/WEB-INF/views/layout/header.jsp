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
					<button class="rounded-pill login_btn" onclick="location.href='/login'">로그인</button>
				</sec:authorize>
	
				<%-- 로그인 : 관리자 --%>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="dropdown">
						<button class="dropdown-toggle d-flex justify-content-center align-items-center users_btn" data-toggle="dropdown">
							<sec:authentication property="principal.username"/>
						</button>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          <label class="label">마이페이지</label>
				          <a class="dropdown-item" href="/myp?menu=resume">자소서 관리</a>
				          <a class="dropdown-item" href="/myp?menu=file">파일 관리</a>
				          <a class="dropdown-item" href="/myp?menu=historyMng">이력 관리</a>
				          <div class="dropdown-divider"></div>
				          <label class="label">관리자 페이지</label>
				          <a class="dropdown-item" href="/mng/fed">자소서 피드백 관리</a>
				          <a class="dropdown-item" href="/mng/qna/board.do">고객센터 답변 관리</a>
				          <a class="dropdown-item" href="/mng/member">회원 관리</a>
				          <div class="dropdown-divider"></div>
				          <a class="dropdown-item" href="/logout">로그아웃</a>
				        </div>
					</div>
				</sec:authorize>
				
				<%-- 로그인 : 일반사용자 --%>
				<sec:authorize access="!hasRole('ROLE_ADMIN')">
					<div class="dropdown">
						<button class="dropdown-toggle d-flex justify-content-center align-items-center users_btn" data-toggle="dropdown">
							<i class="fas fa-user-circle fa-fw mr-1"></i> <%-- fa-fw : 균등너비 --%>
							<sec:authentication property="principal.username"/> 님
						</button>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          <label class="label">마이페이지</label>
				          <a class="dropdown-item" href="/myp?menu=resume">자소서 관리</a>
				          <a class="dropdown-item" href="/myp?menu=file">파일 관리</a>
				          <a class="dropdown-item" href="/myp?menu=historyMng">이력 관리</a>
				          <div class="dropdown-divider"></div>
				          <a class="dropdown-item" href="/logout">로그아웃</a>
				        </div>
					</div>
				</sec:authorize>


				<sec:authorize access="isAuthenticated()">
					
				</sec:authorize>
	
			</div>
			<!-- ./container-md -->
			
		</nav>
		
	</header>
	<!-- header 메뉴 끝 -->