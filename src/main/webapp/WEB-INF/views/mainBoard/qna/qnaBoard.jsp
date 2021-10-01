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
        
        /* [페이징] */
		.center {
			text-align: center;
		}
		
		ul.pagination{
			list-style-type: none;
		}
		
		ul.pagination li{
			display: inline-block;
		}
		
		ul.pagination a {
			color: black;
			float: left;
			padding: 4px 8px;
			text-decoration: none;
			transition: background-color .3s;
			margin: 0px;
		}
		
		ul.pagination a.active {
			background-color: #4CAF50;
			color: white;
			border: 1px solid #4CAF50;
		}
		
		ul.pagination a:hover:not(.active) {background-color: #ddd;}
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
                    <li><a href="/main/qna/board.do" class="active">고객센터</a></li>
                </ul>
                <button class="login_btn">로그인</button>
            </div>
        </nav>
    </header>
    <!-- ./navbar -->
    
    <div>현재 <span style="color:orange;">${fn:length(list) }건</span>의 게시물이 있습니다</div>
    
    <table>
    	<thead>
    		<th>번호</th>
    		<th>제목</th>
    		<th>답변</th>
    		<th>작성자</th>
    		<th>등록일</th>
    	</thead>
    	<tbody>
	    	<%-- 
	    	<c:choose>
				<c:when test="${empty list || fn:length(list) == 0 }">
				</c:when>
				<c:otherwise>
					<c:forEach var="dto" items="${list }" varStatus="status">
						<tr>
							<td>${dto.id }</td>
							<td><a href="view.do?id=${dto.id }">${dto.subject }</a></td>
							<td>
								<c:choose>
									<c:when test="${dto.replyState eq false }">
									</c:when>
									<c:otherwise>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left-fill" viewBox="0 0 16 16">
										  <path d="M2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
										</svg>1
									</c:otherwise>
								</c:choose>
							</td>
							<td>${listName[status.index] }</td>
							<td>${dto.regdate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose> 
			--%>
    	</tbody>
    </table>
    
	<button type="button" onclick="location.href='write.do'">문의하기</button>
	
	<!-- 페이징 -->
	<div class="center">
		<ul class="pagination" id="pagination"></ul>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="/JS/qnaBoard.js"></script>
</body>
</html>