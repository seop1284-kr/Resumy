<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<link href="/css/file.css" rel="stylesheet">

<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<!-- font-family: 'Jua', sans-serif; -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

<!-- JS -->
<script src="https://kit.fontawesome.com/8358df18ed.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/assets/dist/js/bootstrap.min.js"></script>
<script src="/JS/file.js"></script>

<!-- VIEW BODY -->
<div class="col" style="color: black;">
	<div class="container-md">
		
	    <section>
	    
	    	<h4 id="title">나의 파일 관리</h4>
	    	
	    	<div class="d-flex justify-content-between align-items-end">
	    		<div style="font-weight: lighter;">현재 파일 개수 : <span id="fileCnt" style="color:orange;"></span> / 남은 저장 공간 : <span id="leftCnt" style="color:orange;"></span> / 파일 하나당 최대 5MB</div>
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
	
	</div>
</div>
