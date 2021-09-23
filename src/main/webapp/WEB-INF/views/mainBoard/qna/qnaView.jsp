<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객센터 문의글 읽기 - RESUMY</title>
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
                    <li><a href="fedboard">자소서 게시판</a></li>
                    <li><a href="qnaBoard.do" class="active">고객센터</a></li>
                </ul>
                <button class="login_btn">로그인</button>
            </div>
        </nav>
    </header>
    <!-- ./navbar -->
    
	<c:choose>
		<c:when test="${empty qlist || fn:length(qlist) == 0 }">
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<td colspan="4">${qlist[0].subject }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${qlist[0].regdate }</td>
					<td>작성자</td>
					<td>${userName }</td>
				</tr>
			</table>
			
			<div>${qlist[0].content }</div>
			
			<h2>답변</h2>
			<table>
				<tr>
					<td>아이콘</td>
					<td>admin</td>
					<td>${alist[0].regdate }</td>
				</tr>
				<tr>
					<td colspan="3">${alist[0].reply }</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
	<button >삭제</button>
	<button onClick="location.href='qnaUpdate.do?id=${qlist[0].id }'">수정</button>
	<button onClick="location.href='qnaBoard.do'">목록</button>
	
	<div style="color:red">**삭제 js 구현 : 1) 문의글 id와 현 계정 id가 일치하지 않으면 팝업</div>
	<div style="color:red">**삭제 js 구현 : 2) 1을 만족할 경우 삭제 여부 확인 팝업</div>
	<div style="color:red">**수정 js 구현 : 1) 문의글 id와 현 계정 id가 일치하지 않으면 팝업</div>
	<div style="color:red">**수정 js 구현 : 2) 1을 만족할 경우 답변이 있으면 수정 불가</div>
	<div style="color:red">**수정 js 구현 : 3) 1, 2를 만족할 경우 경로 이동</div>
</body>
</html>