<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp"></c:import>
	<!-- My CSS -->
	<link href="/css/recruitInfo.css" rel="stylesheet">

</head>

<body class="wrapper">

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="company" />
	</c:import>

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





