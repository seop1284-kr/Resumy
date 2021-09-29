<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- 스타일, js -->
<link rel="stylesheet" type="text/css" href="/CSS/mngFed.css">
<script src="/JS/fedBoard.js"></script>

<body>

	<!-- 검색 -->
	<input type="text" id="keyword" name="keyword" style="width: 50vh" />
	<button type="button" id="searchBtn">검색</button>

	<%-- 글 목록 --%>
	<div id="list">
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
					<th>자소서 제목</th>
					<th>내용</th>
					<th>공개 날짜</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>


	<!--
    <table>
		<c:choose>
			<c:when test="${empty list || fn:length(list) == 0 }">
			</c:when>
			<c:otherwise>
				<c:forEach var="introResult" items="${list }" varStatus="status">
					<table>
						<tr>
							
							<td><a href="fedView?id=${introResult.intro.id }">
							${introResult.intro.title }</a></td>
							<td>${introResult.conList[0].question }</td>
							<td>${introResult.conList[0].content }</td>
							<td>${introResult.intro.modydate }</td>
							
						</tr>
					</table>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	-->


	</div>
	<div class="clear"></div>

	<%-- [페이징] --%>
	<div class="center">
		<ul class="pagination" id="pagination"></ul>
	</div>
</body>