<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:import url="./layout/head.jsp"></c:import>
<%-- 헤드 끝 --%>

	<!-- My CSS -->
	<link href="/css/index.css" rel="stylesheet">

<style>

header {
	/* index에서는 header margin 없애기 */
    margin-bottom: 0;
}

.heroImg {
	text-align: center;
	background-image: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)), url(https://i.ibb.co/jRSpn1C/skyscrapers-hero-Img.jpg);
	background-color: black;
	background-position: center;
    background-size: cover;
    height: 50vh;
    width: 100%;
    min-height: 250px;
    color: #FFFFF3;
    color: var(--resumy-white);
    margin-bottom: 30px;

}

.heroImg .heroImgTxt {	
	margin-top: 110px;
    margin-bottom: auto;
}

.heroImg div p {
	font-size: 1.5vw;
	text-align: center;  
}

.heroImg div h1 {
	font-size: 3vw;
	text-align: center;
}

.heroImg img {
	width: 20%;
}

@media screen and (max-width: 991px){
	.heroImg {
		height: 300px;
	}
	
	.heroImgTxt {
		display: none;
	}
	
	.heroImg img {
		width: 50%;
		margin-top: 65px;
	}
}

@media screen and (max-width: 730px){
	.heroImg {
		height: 250px;
	}	
	.heroImgTxt {
		display: none;
	}

	.heroImg img {
		width: 50%;
		margin-top: 65px;
	}
} 
</style>

</head>

<body class="wrapper" id="page-top">

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
		
	<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
		</a>
		
	</div>
	
	<c:import url="./layout/footer.jsp"></c:import>
	<%-- 푸터 끝 --%>

    <!-- Custom scripts for all pages-->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/js/sb-admin-2.min.js"></script>
	
	<!-- MY JS -->
	<script src="/JS/index.js"></script> <!-- 채용정보API -->
</body>
</html>