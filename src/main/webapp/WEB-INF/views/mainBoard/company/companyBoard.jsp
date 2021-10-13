<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp"></c:import>
	<!-- My CSS -->
	<link href="/css/recruitInfo.css" rel="stylesheet">
	
	<!-- font -->
	<link href="https://fonts.googleapis.com/css2?family=Yeon+Sung&display=swap" rel="stylesheet">
</head>

<body class="wrapper" id="page-top">

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="company" />
	</c:import>

	<div class="mb">
		<blockquote class="text-center d-flex flex-column">
			<div class="d-flex justify-content-between">
				<i class="fas fa-quote-left rotate-n-15"></i>
				<span id="quotation">당신의 취업 성공을 언제나 응원합니다</span>
				<i class="fas fa-quote-right rotate-15 justify-content-end"></i>
			</div>
			
			<div class="text-right">
				<i class="fas fa-minus"></i>
				<span id="source"> RESUMY </span>
				<i class="fas fa-minus"></i>
			</div>
			
		</blockquote>
	</div>

	<!-- content -->
	<div class="container main-content">
		<ul id="infoList" class="list"></ul>
		
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
		</a>
	</div>
	
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp"></c:import>
	
	<!-- Custom scripts for all pages-->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/js/sb-admin-2.min.js"></script>
	
	<!-- MY JS -->
	<script src="/JS/recruitInfo.js"></script> <!-- 채용정보API -->

</body>
</html>





