<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- 스타일, js 라이브러리 -->
<link rel="stylesheet" type="text/css" href="/CSS/mngFed.css">
<script src="/JS/mngFed.js"></script>

<body>
	<div>
		<%-- 글 목록 --%>
		<div id="list">
			<h4>자소서 피드백 관리</h4>
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
						<th>게시물 번호</th>
						<th>자소서 제목</th>
						<th>피드백 내용</th>
						<th>피드백 업로드</th>
						<th>#</th>
					</thead>
					<tbody>
					</tbody>
				</table>
			</form>

			<%-- bottom 버튼 --%>
			<div class="d01">
				<div class="right">
					<button type="button" id="btnDel" class="btn danger">글삭제</button>
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