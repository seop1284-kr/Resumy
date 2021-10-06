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
	
	
		<!-- DataTales Example -->
		<div class="table-responsive">
            <table class="table table-bordered table-layout-fixed" id="dataTable" width="100%" cellspacing="0">
            	<colgroup>
			        <col width="20%"/>
			        <col/>
			        <col width="8%"/>
			        <col width="10%"/>
			        <col width="15%"/>
			    </colgroup>
            	<thead>
            		<tr>
            			<th>자소서 제목</th>
						<th>내용</th>
						<th>댓글</th>
						<th>아이디</th>
						<th>수정 날짜</th>
            		</tr>
            	</thead>
            </table>
        </div>
        
		<%-- 글 목록 --%>
		<div id="list">
			<%-- header 헤더 --%>
			<div class="d01">
				<div class="left" id="pageinfo"></div>
				<div class="right" id="pageRows"></div>
			</div>
			<div class="clear"></div>
			<%-- 목록 --%>
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
	
	<!-- Page level plugins -->
    <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	
	
	<!-- My Script -->
	<script src="/JS/fedBoard2.js"></script>
</body>
</html>