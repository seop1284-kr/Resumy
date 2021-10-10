<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:import url="./layout/head.jsp"></c:import>
<%-- 헤드 끝 --%>

	<!-- My CSS -->
	<link href="/css/index.css" rel="stylesheet">

</head>

<body class="wrapper">

	<c:import url="./layout/header.jsp">
		<c:param name="headerMenu" value="main" />
	</c:import>
	<%-- 헤더 끝 --%>
	
	<section class="heroImg">
        
            <!-- 히어로 이미지 -->
            <div class="heroImgTxt">
            <p>손쉬운 자소서, 이력서, 포트폴리오 관리 <p>
            <h1>누구나 합격할 수 있다</h1>
        	</div>
        	<img alt="RESUMY" src="/img/logo_main.png">
    </section>
			
	<div class="container main-content">
		<ul id="infoList" class="list"></ul>
	</div>
		
	<c:import url="./layout/footer.jsp"></c:import>
	<%-- 푸터 끝 --%>
	
	<!-- MY JS -->
	<script src="/JS/index.js"></script> <!-- 채용정보API -->
</body>
</html>