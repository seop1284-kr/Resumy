<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html lang='ko'>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>고객센터 - RESUMY</title>
   
    <link href="/img/fibicon.png" rel="shortcut icon" type="image/x-icon">
    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/navbar.css" rel="stylesheet">
    <link href="/css/footer.css" rel="stylesheet">
    <!-- font-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    <!-- font-family: 'Gowun Dodum', sans-serif; -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap" rel="stylesheet">
    <!-- font-family: 'Roboto Condensed', sans-serif; -->
    
    <!-- 스타일, js 라이브러리 -->
	<link rel="stylesheet" type="text/css" href="/CSS/mngFed.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/bb29575d31.js"></script>
	<script src="/JS/fedBoard.js"></script>
    
    
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>

<body>
    <header class="container-md">
        <nav class="navbar navbar-expand-md fixed-top">
            <div class="container-md">
                <a class="navbar-brand" href="index.html">
                    <img src="/img/logo_main.png">
                </a>
                <ul>
                    <li><a href="companyBoard.do">기업정보</a></li>
                    <li><a href="fedBoard">자소서 게시판</a></li>
                    <li><a href="qnaBoard.do" class="active">고객센터</a></li>
                </ul>
                <button class="login_btn">로그인</button>
            </div>
        </nav>
    </header>
    <!-- ./navbar -->
    
    
    <!-- 검색 -->
    <form id="frmKeyword" name="frmKeyword">
		<input type="text" id="keyword" name="keyword" style=" width:50vh"/>
		<button type="button" id="searchBtn">검색</button>
	</form>
	
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
</html>