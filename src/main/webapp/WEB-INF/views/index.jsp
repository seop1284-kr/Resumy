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
			
	<div class="container-lg main-content">
		<!-- 공채 주간달력 -->
		<iframe id="pageFrame" name="pageFrame" class="w-100" src="http://www.jobkorea.co.kr/Starter/calendar/sub/week" height="250px" frameborder="0" scrolling="auto"></iframe>
		
		<ul class="row">
		
			<!-- 채용정보 -->
			<li class="col-lg-6 col-md-12">
			
				<!-- title -->
				<div class="d-flex justify-content-between">
					<h4>채용정보 (오늘까지 지원하세요!)</h4>
					<a href="/companyBoard"><i class="far fa-plus-square"></i></a>
					
				</div>
				<!-- content -->
				<table>
					<colgroup>
					</colgroup>
					<thead>
						<tr>
							<td>기업명</td>
							<td>고용형태</td>
							<td>제목</td>
						</tr>
					</thead>
					<tbody id="recruitBoard">
					</tbody>
				</table>
				
			</li>
			
			<!-- 뉴스 -->
			<li class="col-lg-6 col-md-12">
			
				<!-- title -->
				<div class="d-flex justify-content-between">
					<h4>취업뉴스 확인하기</h4>
					<a href="#"><i class="fas fa-search-plus"></i></a>
				</div>
				
				<!-- content -->
				<table>
					<colgroup>
					</colgroup>
					<thead>
					</thead>
					<tbody id="newsBoard">
					</tbody>
				</table>
				
			</li>
			
			<!-- 자소서 게시판 -->
			<li class="col-lg-6 col-md-12">
			
				<!-- title -->
				<div class="d-flex justify-content-between">
					<h4>최신 업데이트 자소서</h4>
					<a href="/fedBoard"><i class="far fa-plus-square"></i></a>
				</div>
				
				<!-- content -->
				<table>
					<colgroup>
					</colgroup>
					<thead>
						<tr>
							<td>제목</td>
							<td>내용</td>
						</tr>
					</thead>
					<tbody id="fedBoard">
					</tbody>
				</table>
				
			</li>
			
			<!-- 고객센터 -->
			<li class="col-lg-6 col-md-12">
				
				<!-- title -->
				<div class="d-flex justify-content-between">
					<h4>최신 고객센터 문의글</h4>
					<a href="/main/qna/board.do"><i class="far fa-plus-square"></i></a>
				</div>
				
				<!-- content -->
				<table>
					<colgroup>
					</colgroup>
					<thead>
						<tr>
							<td>제목</td>
							<td>날짜</td>
						</tr>
					</thead>
					<tbody id="qnaBoard">
					</tbody>
				</table>
			</li>
			
		</ul>
		
	</div>
	
	<c:import url="./layout/footer.jsp"></c:import>
	<%-- 푸터 끝 --%>

	<!-- MY JS -->
	<script src="/JS/index.js"></script>
</body>
</html>