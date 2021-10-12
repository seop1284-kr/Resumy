<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:import url="./layout/head.jsp"></c:import>
<%-- 헤드 끝 --%>

	<!-- My CSS -->
	<link href="/css/index.css" rel="stylesheet">
	<link href="/css/blink.css" rel="stylesheet">
</head>

<body class="wrapper" id="page-top">

	<c:import url="./layout/header.jsp">
		<c:param name="headerMenu" value="main" />
	</c:import>
	<%-- 헤더 끝 --%>
	
	<!-- 히어로 이미지 -->
	<div class="hero mb-5">
		<div class="container pt-4">
			<ul class="text-white my-5">
				<li class="h5 mb-5">My Resume!</li>
				<li class="h4 mb-2">손쉬운 자소서, 이력서, 포트폴리오 관리</li>
				<li class="h2 font-weight-bold">누구나 합격할 수 있다.</li>
			</ul>
			<button type="button" onclick="location.href='/login'" class="btn btn-primary border-secondary rounded-pill">이용해보기 <i class="fas fa-chevron-right fa-sm"></i></button>
		</div>
    </div>
	
	<!-- Page Contents -->
	<section class="container-lg main-content">
	
		<!-- 공채 주간달력 -->
		<iframe id="pageFrame" name="pageFrame" class="w-100 mb-5" src="http://www.jobkorea.co.kr/Starter/calendar/sub/week" height="250px" frameborder="0" scrolling="auto"></iframe>
		
		<!-- 주간달력 하단의 정보 -->
		<ul class="row">
		
			<!-- 채용정보 -->
			<li class="col-lg-6 col-md-12 mb-5">
				<!-- title -->
				<div class="d-flex justify-content-between">
					<i class="fas fa-building mr-3"></i>
					<h4>채용정보</h4>
					<a class="ml-auto" href="/companyBoard"><i class="far fa-plus-square"></i></a>
				</div>

				<!-- content -->
				<div class="table-wrap">
					<table>
						<colgroup>
							<col width=30/>
							<col width=70/>							
						</colgroup>
						<thead>
							<tr>
								<td colspan="2">오늘마감!</td>
							</tr>
						</thead>
						<tbody id="recruitBoard">
						</tbody>
					</table>
				</div>
			</li>
			
			<!-- 뉴스 -->
			<li class="col-lg-6 col-md-12 mb-5">
				<!-- title -->
				<div class="d-flex justify-content-between">
					<i class="far fa-newspaper mr-3"></i>
					<h4>취업뉴스</h4>
					<a class="ml-auto"><i class="fas fa-search-plus" id="btn_search"></i></a>
				</div>
					
				<!-- search button -->
				<div class="table-wrap">
					<ul class="d-flex searchBox">
						<li class="btn btn-light rounded-0 col" id="btn_news_it" data-keyword="게임 웹 앱 해킹 보안 네트워크 통신 AI 인공지능 IoT">IT 이슈</li>
						<li class="btn btn-light rounded-0 col" id="btn_news_economy" data-keyword="경제 취업 정책">경제 이슈</li>
						<li class="btn btn-light rounded-0 col" id="btn_news_job" data-keyword="취업시장 취업박람회 취업교육 취준생 청년">취업 소식</li>
						<li class="input-group justify-content-end" id="search_wrap" style="display: none;">
							<input type="text" id="btn_news_input" placeholder="검색어 입력">
							<span class="input-group-text"><i class="fas fa-search" id="btn_news_search"></i></span>
						</li>
					</ul>
					
					<!-- content -->
					<table>
						<colgroup>
							<col width=80/>							
							<col width=20/>
						</colgroup>
						<tbody id="newsBoard">
						</tbody>
					</table>
				</div>
			</li>
			
			<!-- 자소서 게시판 -->
			<li class="col-lg-6 col-md-12 mb-md-5">
				<!-- title -->
				<div class="d-flex justify-content-between">
					<i class="far fa-address-card mr-3"></i>
					<h4>자기소개서</h4>
					<a class="ml-auto" href="/fedBoard"><i class="far fa-plus-square"></i></a>
				</div>
					
				<!-- content -->
				<div class="table-wrap">
					<table>
						<colgroup>
							<col width=30/>
							<col width=70/>							
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
				</div>
			</li>
			
			<!-- 고객센터 -->
			<li class="col-lg-6 col-md-12">
				<!-- title -->
				<div class="d-flex justify-content-between">
					<i class="far fa-comment-dots mr-3"></i>
					<h4>고객센터</h4>
					<a class="ml-auto" href="/main/qna/board.do"><i class="far fa-plus-square"></i></a>
				</div>
					
				<!-- content -->
				<div class="table-wrap">
					<table>
						<colgroup>
							<col width=70/>							
							<col width=30/>
						</colgroup>
						<tbody id="qnaBoard">
						</tbody>
					</table>
				</div>
			</li>
			
		</ul>
		<!-- ./주간달력 하단의 정보 -->
		
	</section>
	<!-- ./Page Contents -->
	
	<c:import url="./layout/footer.jsp"></c:import>
	<%-- 푸터 끝 --%>

	<!-- MY JS -->
	<script src="/JS/index.js"></script>
</body>
</html>