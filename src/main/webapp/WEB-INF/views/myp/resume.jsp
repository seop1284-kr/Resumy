<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Resume JS -->
<script src="/JS/resume.js"></script>

<!-- Resume CSS -->
<link href="/css/resume.css" rel="stylesheet">

<!-- font-family: 'Jua', sans-serif; -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    
<div class="col">
	<div class="container-md">
		<!-- 제목 -->
		<h4 id="title" style="font-weight: bold"></h4>

		<!-- 검색 -->
		<br>

		<div id="searchBox">


			<input type="text" id="keyword" placeholder="자소서 제목, 항목, 내용 등 키워드"
				style="width: 50vh" />
			<button type="button" class="searchBtn btn btn-mint">검색</button>
		</div>

		<!-- 자소서 목록 리스트 -->
		<div id="content"></div>


		<%-- 글 작성 / 보기 / 수정 폼 --%>
		<form id="frmWrite" name="frmWrite" method="post" target="iframe1">
			<%-- submit 후 이동 막는 방안 --%>
			<iframe id="iframe1" name="iframe1" style="display: none"></iframe>
			<input type="hidden" name="iid">


			<div id="crud_form_box">
				
				<div id="content_text_box">
					<div id="content_text">
						<div class="input-title-line">
							<input class="input-title-text" type="text" placeholder="자소서 제목을 입력해주세요." name="title" maxlength=15 required>
							<input class="checkBox" type="checkbox" name="pub" value="true">공개
							<input class="checkBox" type="checkbox" name="fin" value="true">작성완료<br>
						</div>
						<br>
						
					
						 
						<textarea class="input-question-text" type="text" placeholder="질문을 입력해주세요."
							name="question" value="" maxlength=255 required></textarea>
						<textarea class="input-content-text" placeholder="내용을 입력해주세요." name="content" value=""
							required></textarea>
					</div>
					<div id="content_text_plus"></div>
				</div>

				<%-- 하단 버튼 : 글 조회 --%>
				<div class="d01 btn_group_view">
					<button type="button" class="listBtn btn btn-mint">목록</button>
					<button type="button" class="deleteBtn btn btn-mint">자소서 삭제</button>
					<button type="button" class="updateBtn btn btn-mint">자소서 수정</button>
				</div>

				<%-- 하단 버튼 : 글 작성 --%>
				<div class="d01 btn_group_write">
					<button type="button" class="plusBtn btn btn-mint">추가</button>
					<button type="button" class="listBtn btn btn-mint">목록</button>
					<button type="submit" class="btn btn-mint">자소서 저장</button>
				</div>

				<%-- 하단 버튼 : 글 수정 --%>
				<div class="d01 btn_group_update">
					<button type="button" class="plusBtn btn btn-mint">추가</button>
					<button type="button" class="updateCancelBtn btn btn-mint">수정 취소</button>
					<button type="submit" class="btn btn-mint">자소서 저장</button>
				</div>
			</div>
		</form>
	</div>
</div>
