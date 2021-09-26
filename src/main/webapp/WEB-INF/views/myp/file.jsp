<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<!DOCTYPE html>
<html lang='ko'>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>RESUMY - 채용 도우미 사이트</title>
    <link href="/img/fibicon.png" rel="shortcut icon" type="image/x-icon">
    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/navbar.css" rel="stylesheet">
    <link href="/css/footer.css" rel="stylesheet">
    <link href="/css/file.css" rel="stylesheet">
    <link href="/css/mypageTemplate.css" rel="stylesheet">    
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

<body class="container-md">
    <header>
        <nav class="navbar navbar-expand-md fixed-top">
            <div class="container-md">
                <a class="navbar-brand" href="index.html">
                    <img src="/img/logo_main.png">
                </a>
                <ul>
                    <li>기업정보</li>
                    <li>자소서 게시판</li>
                    <li>고객센터</li>
                </ul>
                <button class="login_btn">로그인</button>
            </div>
        </nav>
    </header>
    <!-- ./navbar -->

    <div class="row">
        <aside class="col-2">
            <h4><b>마이페이지</b></h4>
            <hr>
            <ul>
                <li><a href="/myp/resume">자기소개서 관리</a></li>
                <li class="active"><a href="file.jsp">파일 관리</a></li>
                <li><a href="/myp/historyMng">이력 관리</a></li>
            </ul>
        </aside>
        <!-- ./aside -->

		
	
        <section class="col">
        
        	<h4 id="title" style="font-weight: bold">나의 파일 관리</h4>
        	
        	
        	<%-- 우상단 버튼들 --%>
        	<div>
        		<button type="button" id="uploadBtn" class="uploadBtn">업로드</button>
        		<button type="button" id="downloadBtn" class="downloadBtn">다운로드</button>
        		<button type="button" id="deleteBtn" class="deleteBtn">삭제</button>
        	</div>
        	
        	<form id="fileList">
        	<div id="content"><!-- 데이터베이스 출력 --></div>    
            </form>
            
        </section>
    </div>

<%-- 파일 업로드 모달 --%>
<div id="dlg_file" class="modal">
	<form class="modal-content animate" id="frmFile" name="frmFile" method="post" enctype="multipart/form-data">
		<div class="container">
			
			
			<input type="hidden" name="fid" value="0"> <%-- 읽기, 삭제, 수정을 위해 필요 --%>
			
			<label for="file">파일 업로드</label>
			<input type="file" name="file" required>
			<br>		
			<label for="memo">MEMO</label>
			<input type="text" placeholder="8글자 이내" name="memo">			
			<br>
			<div class="btn_group_file">
				<button type="button" class="btn_cancel">취소</button>
				<button type="submit" class="btn_upload">업로드</button>
			</div>
		</div>
	</form>
</div>


    <footer>
        <div class="row">
            <div class="col-2">
                <img src="/img/logo_ contrast.png">
            </div>
            <div class="col-9">
                프로젝트명 : 채용 도우미 사이트 Resumy(리주마이)<br>
                팀명 : 간개다모 (김진섭 팀장, 김민수, 노수빈, 하병노)<br>
                이메일 : seop1284@gmail.com (김진섭), kimpkoiw@gmail.com (김민수), binigy97@gmail.com (노수빈), hahbr88@gmail.com (하병노)
            </div>
            <div class="col">
                CopyrightⓒGangeadamo 2021-09-07 ~ 2021-10-15
            </div>
        </div>
    </footer>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/assets/dist/js/bootstrap.min.js"></script>
    <script src="/JS/file.js"></script> <!-- bootrstap 을 따르는 문서 -->
</body>

</html>