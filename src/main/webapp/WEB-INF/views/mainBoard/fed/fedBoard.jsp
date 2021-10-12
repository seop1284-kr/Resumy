<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 헤드 --%>
<c:import url="../../layout/head.jsp"></c:import>

	<!-- My CSS -->
	<link href="/css/dataTables.css" rel="stylesheet">
	<link href="/css/fedBoard.css" rel="stylesheet">
	<!-- <link rel="stylesheet" type="text/css" href="/CSS/fedBoard.css"> -->

</head>

<body class="wrapper">

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="fed" />
	</c:import>
	
	<!-- 검색 -->
	<div class="searchContainer mb-5">
		<div class="searchWrapper container">
			<div class="row">
				<div class="col-7 d-flex justify-content-center align-items-center flex-column">
					<h5 class="searchTitle mb-1 highlight"> <mark>무엇이든 검색해보세요.</mark></h5>
					<h5 class="searchTitle mb-4"> 딱 맞는 합격 자소서가 <strong class="text-danger">1초</strong> 안에 나옵니다.</h5>					
					<input type="text" class="customSearchTextField form-control" 
						id="customSearchTextField" placeholder="자소서 항목, 내용, 제목, 아이디 등 키워드"/>
				</div>
				<div class="banner col-5">
					<img src="/img/img_banner.png">
				</div>
			</div>
		</div>
	</div>

	<!-- main-content 시작 -->
	<section class="container-md main-content">
		
		<!--
		<input type="text" id="keyword" name="keyword" style="width: 50vh" />
		<button type="button" id="searchBtn">검색</button>
	 	-->
		
		<!-- DataTales Example -->
		<div class="table-responsive">
            <table class="table table-bordered table-layout-fixed" id="dataTable" width="100%" cellspacing="0">
            	<colgroup>
			        <col width="15%"/>
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
						<th>수정일</th>
            		</tr>
            	</thead>
            </table>
        </div>
        
	</section>
	<!-- main-content 끝 -->

	<%-- 푸터 --%>
	<c:import url="../../layout/footer.jsp">
	</c:import>
	
	<!-- Page level plugins -->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	
	<!-- My Script -->
	<script src="/JS/fedBoard.js"></script>
</body>
</html>