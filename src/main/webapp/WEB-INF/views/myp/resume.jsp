<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Resume JS -->
<script src="/JS/resume.js"></script>

<!-- My CSS -->
<link href="/css/resume.css" rel="stylesheet">

<!-- font-family: 'Jua', sans-serif; -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    
<div class="col-10">
	<div class="container-md">
		<!-- 제목 -->
		<h4 id="title" style="font-weight: bold"></h4>

		<!-- 검색 -->
		<br>

		<div class="input-group w-50" id="searchBox">
			<input class="customSearchTextField form-control" type="text" id="keyword" placeholder="자소서 제목, 항목, 내용 등 키워드"
				style="width: 25em" />
			<div class="input-group-prepend">
				<button type="button" class="searchBtn btn btn-mint"><i class="fas fa-search"></i></button>
			</div>
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
							<input class="input-title-text" type="text" placeholder="제목(기업명)을 입력해주세요." name="title" maxlength=15 required>
							<input class="checkBox" type="checkbox" name="pub" value="true">공개
							<input class="checkBox" type="checkbox" name="fin" value="true">작성완료<br>
						</div>
						<br>
						
						<textarea class="input-question-text p-3" type="text" placeholder="질문을 입력해주세요."
							name="question" value="" maxlength=255 required></textarea>
						<textarea class="input-content-text p-3" placeholder="내용을 입력해주세요." name="content" value=""
							required></textarea>
					</div>
					<div id="content_text_plus"></div>
				</div>

				<%-- 하단 버튼 : 글 조회 --%>
				<div class="btn_group_view">
					<div class="gr d-flex flex-row-reverse mt-5">
						<button type="button" class="listBtn btn btn-mint ml-2">목록</button>
						<button type="button" class="updateBtn btn btn-mint ml-2">자소서 수정</button>
						<button type="button" class="deleteBtn btn btn-danger ml-2">자소서 삭제</button>
					</div>
				</div>

				<%-- 하단 버튼 : 글 작성 --%>
				<div class="btn_group_write">
					<button type="button" class="plusBtn btn btn-mint mt-2">추가</button>
					<div class="gr d-flex flex-row-reverse mt-3">
						<button type="button" class="wrListBtn btn btn-danger ml-2">작성 취소</button>
						<button type="submit" class="btn btn-mint ml-2">자소서 저장</button>
					</div>
				</div>

				<%-- 하단 버튼 : 글 수정 --%>
				<div class="btn_group_update">
					<button type="button" class="plusBtn btn btn-mint mt-2">추가</button>
					<div class="gr d-flex flex-row-reverse mt-3">
						<button type="button" class="updateCancelBtn btn btn-danger ml-2">수정 취소</button>
						<button type="submit" class="btn btn-mint ml-2">자소서 저장</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
