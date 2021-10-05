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
 	<link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">

	 <!-- Custom fonts for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/file.css" rel="stylesheet">
    <link href="/css/mypageTemplate.css" rel="stylesheet">    
    
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

   	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <!-- font-family: 'Jua', sans-serif; -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

   
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

<body style="color: black;" class="container-md">
	
    <section class="col">
    
    	<h4 id="title">나의 파일 관리</h4>
    	<div></div>
    	
    	<div class="d-flex justify-content-between align-items-end">
    		<div class="text-gray-700" style="font-weight: lighter;">현재 파일 개수 : <span id="fileCnt" style="color:orange;"></span> / 남은 저장 공간 : <span id="leftCnt" style="color:orange;"></span> / 파일 하나당 최대 5MB</div>
    		<!-- <div class="mt-5 mb-2">저장 가능한 15개의 파일 중 현재 <span id="fileCnt2" style="color:orange;"></span>의 파일이 있습니다</div> -->
    		
    		<%-- 우상단 버튼들 --%>
    		<div class="d-flex justify-content-end">
    			<button type="button" id="uploadBtn" class="uploadBtn btn btn-mint mr-2"><i class="fas fa-upload"></i>&nbsp;&nbsp;업로드</button>
    			<button type="button" id="downloadBtn" name="filename" class="downloadBtn btn btn-mint" value="${fileName}"><i class="fas fa-download"></i>&nbsp;&nbsp;다운로드</button>
    		</div>
    		
    	</div>
    	
    	<form id="fileList">
    		<div id="content"><!-- 데이터베이스 출력 --></div>    
        </form>
        
    	<button type="button" id="deleteBtn" class="deleteBtn btn btn-mint"><i class="fas fa-trash-alt"></i>&nbsp;&nbsp;삭제</button>
        
    </section>
    



<%-- 파일 업로드 모달 --%>
<div id="dlg_file" class="modal">
	<form class="modal-content animate" id="frmFile" name="frmFile" method="post" enctype="Multipart/form-data">
		<div class="container">
			
			
			<input type="hidden" name="fid" value="0"> 
			
			<label for="file">파일 업로드</label>
			<input type="file" name="file" accept=".xlsx, .xls, .doc, .docx, .hwpx, .png, .jepg, .zip, .pptx, .ppt, .txt, .pdf" required>
			<br>		
			<label for="memo">MEMO</label>
			<input type="text" placeholder="8글자 이내" name="memo">			
			<br>
			
		</div>
		<div class="btn_group_file">
			<button type="button" class="btn_cancel">취소</button>
			<button type="submit" class="btn_upload">업로드</button>
		</div>
	</form>
</div>

	<script src="https://kit.fontawesome.com/8358df18ed.js" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/assets/dist/js/bootstrap.min.js"></script>
    <script src="/JS/file.js"></script> <!-- bootrstap 을 따르는 문서 -->
</body>

</html>