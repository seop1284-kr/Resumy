<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객센터 문의글 수정 - RESUMY</title>
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

<%-- 
	result == 0 의미 : 1 또는 2 만족
	1) 현재 로그인한 계정 != 문의글 작성한 계정
	2) 문의글에 답글 있음
--%> 
<c:choose>
	<%-- 경고창 팝업만 띄움 --%>
	<c:when test="${result != 0 }">
		<script>
			alert("다른 사용자의 문의글이나 답변이 달린 문의글은 수정할 수 없습니다.");
			history.back();
		</script>
	</c:when>
	
	<%-- 글 수정 페이지 보여줌 --%>
	<c:otherwise>
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
		                    <li><a href="/main/qna/board.do" class="active">고객센터</a></li>
		                </ul>
		                <button class="login_btn">로그인</button>
		            </div>
		        </nav>
		    </header>
		    <!-- ./navbar -->
			
			<table>
				<tr>
					<td colspan="4">${qdto.subject }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${qdto.regdate }</td>
					<td>작성자</td>
					<td>${userName }</td>
				</tr>
			</table>
			<form name="frm" action="updateOk.do" method="post">
				<input type="hidden" name="id" value="${qdto.id }">
				<input type="text" name="content" value="${qdto.content }" required style="display: block; width:90vh"/>
				<button type="submit">수정완료</button>
			</form>
		</body>
	</c:otherwise>
</c:choose>

</html>

