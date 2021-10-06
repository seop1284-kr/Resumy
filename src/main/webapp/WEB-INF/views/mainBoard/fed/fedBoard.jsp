<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp">
</c:import>

<!-- My CSS -->
<link href="/css/dataTables.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" href="/CSS/fedBoard.css"> -->

</head>

<body>

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="fed" />
	</c:import>

	<section class="container-md">
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
						<th>피드백 수</th>
						<th>공개 날짜</th>
						<th>사용자 아이디</th>
					</thead>
					<tbody>
					</tbody>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	
		<%-- [페이징] --%>
		<div class="center">
			<ul class="pagination" id="pagination"></ul>
		</div>
	</section>
	
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
	
	<!-- My Script -->
	<script src="/JS/fedBoard.js"></script>
</body>
</html>