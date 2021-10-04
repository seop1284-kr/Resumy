<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<!DOCTYPE html>
<html lang='ko'>

<head>
    <meta charset="UTF-8">
    <title>고객센터 - RESUMY</title>
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
    <link href="/css/dataTables.css" rel="stylesheet">
    
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
        
        /* 문의하기 버튼 위치 설정 */
        button.btn_write {
       		float: right;
       		transform: translateY(-10em);
        }
        
        /* 페이징 위치 설정 */
        div.dataTables_wrapper div.dataTables_paginate {
        	margin-top: 3.5em;
        	margin-bottom: 3em;
        }
    </style>
</head>

<body>
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="qna" />
	</c:import>
    <!-- ./navbar -->
    
    <!-- Begin Page Content -->
    <div class="container-lg mt-4">

        <!-- Page Heading -->
        <div class="d-s-flex align-items-center justify-content-between">
            <h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>고객센터</b>
            </h1>
        </div>
        
        <div class="mt-5 mb-2">현재 <span style="color:orange;">${fn:length(list) }건</span>의 게시물이 있습니다</div>
        
        <!-- DataTales Example -->
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
            	<thead>
            		<tr>
            			<th class="width-sm">번호</th>
            			<th>제목</th>
            			<th class="width-sm">답변</th>
            			<th class="width-md">작성자</th>
            			<th style="width:13em">등록일</th>
            		</tr>
            	</thead>
            </table>
            <button type="button" class="btn btn-mint btn_write" onclick="location.href='write.do'">문의하기</button>
        </div>

    </div>
    <!-- /.container-lg -->
    
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
	
	<!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/JS/qnaBoard.js"></script>
</body>
</html>