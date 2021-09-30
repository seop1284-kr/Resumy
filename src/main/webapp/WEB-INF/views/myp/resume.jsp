<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="/JS/resume.js"></script>
<body class="container-md">


	<div class="row">
	
		<section class="col">
			
			<!-- 제목 -->
			<h4 id="title" style="font-weight: bold"></h4>

			<!-- 검색 -->
			<div id="searchBox">
				<input type="text" id="keyword" style="width: 50vh" />
				<button type="button" class="searchBtn">검색</button>
			</div>

			<!-- 자소서 목록 리스트 -->
			<div id="content"></div>


			<%-- 글 작성 / 보기 / 수정 폼 --%>
			<form id="frmWrite" name="frmWrite" method="post" target="iframe1">
				<%-- submit 후 이동 막는 방안 --%>
				<iframe id="iframe1" name="iframe1" style="display: none"></iframe>
				<input type="hidden" name="iid">


				<div id="crud_form_box">
					<input class="checkBox" type="checkbox" name="pub" value="true">공개<br>
					<input class="checkBox" type="checkbox" name="fin" value="true">작성완료<br>

					<div id="content_text_box">
						<div id="content_text">
							<input type="text" placeholder="자소서 제목(필수)" name="title" required>
							<input type="text" placeholder="질문" name="question" value="" required>
							<textarea placeholder="내용" name="content" value="" required></textarea>
						</div>
						<div id="content_text_plus"></div>
					</div>

					<%-- 하단 버튼 : 글 조회 --%>
					<div class="d01 btn_group_view">
						<button type="button" class="listBtn">목록</button>
						<button type="button" class="deleteBtn">자소서 삭제</button>
						<button type="button" class="updateBtn">자소서 수정</button>
					</div>

					<%-- 하단 버튼 : 글 작성 --%>
					<div class="d01 btn_group_write">
						<button type="button" class="plusBtn">추가</button>
						<button type="button" class="listBtn">목록</button>
						<button type="submit">자소서 저장</button>
					</div>

					<%-- 하단 버튼 : 글 수정 --%>
					<div class="d01 btn_group_update">
						<button type="button" class="plusBtn">추가</button>
						<button type="button" class="updateCancelBtn">수정 취소</button>
						<button type="submit">자소서 저장</button>
					</div>
				</div>
			</form>

		</section>
	</div>

</body>
