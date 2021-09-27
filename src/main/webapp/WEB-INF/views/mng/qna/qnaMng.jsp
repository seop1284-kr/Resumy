<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html lang='ko'>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>고객센터 관리 - RESUMY 관리자</title>
    <link href="/img/fibicon.png" rel="shortcut icon" type="image/x-icon">
    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
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
    
	<form name="frm_chk" id="frm_chk" action="qnaQDeleteOk.do" method="get" onsubmit="return frmChkSubmit();">
	    <table>
			<c:choose>
				<c:when test="${empty listQ || fn:length(listQ) == 0 }">
				</c:when>
				<c:otherwise>
					<c:forEach var="dto" items="${listQ }" varStatus="status">
						<tr>
							<td>${dto.id }</td>
							<td><a href="/main/qna/view.do?id=${dto.id }">${dto.subject }</a></td>
							<td>${dto.content }</td>
							<td>${dto.regdate }</td>
							<td>
								<c:choose>
									<c:when test="${dto.replyState eq false}">
										<span>-</span>
									</c:when>
									<c:otherwise>
										<span class="replyState_complete">완료</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<button type="button" class="btn_replyWriteModal" data-id="${dto.id }" data-toggle="modal" data-target="#replyWriteModal" style="border:none; background:none;">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
									  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
									  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
									</svg>
								</button>
							</td>
							<td><input type="checkbox" name="id" class="chk_delete" value="${dto.id }"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
		<div>(총 ${fn:length(listQ) }건)</div>
		
		<button type="submit" id="btn_qnaQDeleteOk">항목 삭제</button>
	</form>
	
	<!-- modal -->
	<div class="modal fade" id="replyWriteModal" tabindex="-1" aria-labelledby="replyWriteModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	     	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
			  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
			  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
			</svg>
	        <span class="modal-title" id="exampleModalLabel">
	        	<span class="q_id">00</span>
	        	번 문의글 답변 작성하기</span>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
		  	<form name="frm" action="updateOk.do" method="post">
		       	<input type="hidden" name="id" class="q_id">
		    	<ul class="row">
		        	<li class="col-2">작성자</li>
		        	<li class="col-3" name="name" id="q_name"></li>
		        	<li class="col-2">날짜</li>
		        	<li class="col" name="regdate" id="q_regdate"></li>
		        </ul>
		        <ul class="row">
		        	<li class="col-2">제목</li>
		        	<li class="col" name="subject" id="q_subject"></li>
		        </ul>
		        <ul class="row">
		        	<li class="col-12">내용</li>
		        	<li class="col-12" name="content" id="q_content"></li>
		        </ul>
		        <input type="text" name="reply" id="r_reply"placeholder="답변을 적어주세요." style="width:100%">
		        <button type="button" class="btn btn-secondary" id="btn_delete">답변 삭제</button>
		        <button type="submit" class="btn btn-primary">답변 완료</button>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- ./답글 모달 -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="/assets/dist/js/bootstrap.min.js"></script>
    <script src="/JS/qnaMng.js"></script>
</body>
</html>