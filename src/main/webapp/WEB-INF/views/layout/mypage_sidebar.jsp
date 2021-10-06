<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<%
String menu = "resume"; // menu parameter 가 없으면 resume page 디폴트

String menu_param = request.getParameter("menu");
if (menu_param != null) {
	try {
		menu = menu_param;
	} catch (NumberFormatException e) {

	}
}
%>

<!-- mypage_header 메뉴 시작 -->



<aside class="col-2" style="height: 100%">
	<h4>
		<b>마이페이지</b>
	</h4>
	<hr>
	<ul>
		<li class="<%=(menu.equals("resume")) ? "active" : "" %>">
			<a <%=(!menu.equals("resume")) ? "href='/myp?menu=resume'" : "" %>>자소서 관리</a>	
		</li>
		<li class="<%=(menu.equals("file")) ? "active" : "" %>">
			<a <%=(!menu.equals("file")) ? "href='/myp?menu=file'" : "" %>>파일 관리</a>
		</li>
		<li class="<%=(menu.equals("historyMng")) ? "active" : "" %>">
			<a <%=(!menu.equals("historyMng")) ? "href='/myp?menu=historyMng'" : "" %>>이력 관리</a>
		</li>
	</ul>
</aside>


<!-- mypage_header 메뉴 끝 -->