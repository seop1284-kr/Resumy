<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="/css/mypageTemplate.css" rel="stylesheet">

<%
String menu = "fed"; // menu parameter 가 없으면 resume page 디폴트

String menu_param = request.getParameter("menu");
if (menu_param != null) {
	try {
		menu = menu_param;
	} catch (NumberFormatException e) {

	}
}
%>

<!-- mypage_header 메뉴 시작 -->



<aside class="col-2">
	<h4>
		<b>관리자</b>
	</h4>
	<hr>
	<ul>
		<li class="<%=(menu.equals("fed")) ? "active" : "" %>">
			<a <%=(!menu.equals("fed")) ? "href='/mng?menu=fed'" : "" %>>자소서 피드백 관리</a>	
		</li>
		<li class="<%=(menu.equals("qna/board.do")) ? "active" : "" %>">
			<a <%=(!menu.equals("qna/board.do")) ? "href='/mng?menu=qna/board.do'" : "" %>>고객 센터 관리</a>
		</li>
		<li class="<%=(menu.equals("member")) ? "active" : "" %>">
			<a <%=(!menu.equals("member")) ? "href='/mng?menu=member'" : "" %>>회원 관리</a>
		</li>
	</ul>
</aside>


<!-- mypage_header 메뉴 끝 -->