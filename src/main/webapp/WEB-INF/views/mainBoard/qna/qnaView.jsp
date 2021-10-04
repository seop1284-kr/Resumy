<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>고객센터 문의글 읽기 - RESUMY</title>
    <link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">
    
	 <!-- Custom fonts for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/navbar.css" rel="stylesheet">
    <link href="/css/footer.css" rel="stylesheet">
    <link href="/css/qnaView.css" rel="stylesheet">
    
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

<body class="text-gray-800">
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="qna" />
	</c:import>
    <!-- ./navbar -->
    
    <!-- Begin Page Content -->
    <div class="container-lg mt-4">

        <!-- Page Heading -->
        <div class="d-s-flex align-items-center justify-content-between">
            <h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>문의글 읽기</b>
            </h1>
        </div>
        
       	<!-- 문의글 -->
       	<div class="shadow">
	        <c:choose>
				<c:when test="${empty qdto }">
				</c:when>
				<c:otherwise>
				
					<!-- tableInfo -->
					<table class="tableInfo" width="100%" cellspacing="0">
						<tr>
							<td colspan="4"><b>${qdto.subject }</b></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td class="text-gray-500">${qdto.regdate }</td>
							<td>작성자</td>
							<td class="text-gray-500">${userName }</td>
						</tr>
					</table>
					
					<!-- tableContent -->
					<div class="textContent">${	qdto.content }</div>
					
				</c:otherwise>
			</c:choose>
		</div>
		<!-- ./문의글 -->
		
		<h4><b>답변</b></h4>
				
		<!-- tableRply -->
		<table class="tableRply shadow" width="100%" cellspacing="0">
			<c:choose>
				<c:when test="${empty adto }">
					<tr>
						<td class="text-gray-500">
							<i class="fas fa-spinner fa-spin mr-2"></i>
							질문하신 내용에 답변을 준비하고 있습니다.
						</td>
					</tr>
				</c:when>
				<c:otherwise>
			
					<tr class="notEmpty">
						<td><i class="fas fa-user-circle text-gray-400" style="font-size: 1.5em;"></i></td>
						<td>admin</td>
						<td class="text-gray-500">${adto.regdate }</td>
					</tr>
					<tr class="notEmpty">
						<td colspan="3">${adto.reply }</td>
					</tr>
				
				</c:otherwise>
			</c:choose>
		</table>
		<!-- ./답변 -->
		
		<button type="button" class="btn btn-mint btn_list" onClick="location.href='board.do'">목록</button>
		<button type="button" class="btn btn-mint btn_update" onClick="location.href='update.do?id=${qdto.id }'">수정</button>
		<button type="button" class="btn btn-mint btn_del" onClick="location.href='deleteOk.do?id=${qdto.id }'">삭제</button>
			
    </div>
    <!-- /.container-lg -->
    
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
</body>
</html>