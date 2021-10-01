<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- 스타일, js 라이브러리 -->
<link rel="stylesheet" type="text/css" href="/CSS/mngFed.css">
<script src="/JS/mngMember.js"></script>

<body>
	<div>
		<%-- 글 목록 --%>
		<div id="list">
			<h4>회원 관리</h4>
			<hr>
			<%-- header 헤더 --%>
			<div class="d01">
				<div class="left" id="pageinfo"></div>
				<div class="right" id="pageRows"></div>
			</div>
			<div class="clear"></div>

			<%-- 목록 --%>
			<form id="frmList" name="frmList">
				<table>
					<thead>
						<th>이름</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>생년월일</th>
						<th>가입일</th>
						<th>#</th>
					</thead>
					<tbody>
					</tbody>
				</table>
			</form>

			<%-- bottom 버튼 --%>
			<div class="d01">
				<div class="right">
					<button type="button" id="btnDel" class="btn danger">회원삭제</button>
				</div>

			</div>

		</div>
		<div class="clear"></div>

		<%-- [페이징] --%>
		<div class="center">
			<ul class="pagination" id="pagination"></ul>
		</div>
	</div>
</body>