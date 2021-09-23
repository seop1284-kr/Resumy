<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang='ko'>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>RESUMY - 채용 도우미 사이트</title>
<link href="/img/fibicon.png" rel="shortcut icon" type="image/x-icon">
<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/navbar.css" rel="stylesheet">
<link href="/css/footer.css" rel="stylesheet">
<link href="/css/history.css" rel="stylesheet">
<!-- font-awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<!-- font-family: 'Gowun Dodum', sans-serif; -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<!-- font-family: 'Roboto Condensed', sans-serif; -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="/assets/dist/js/bootstrap.min.js"></script>
<script src="/JS/career.js"></script>
<script src="/JS/historyMng.js"></script> <!-- bootrstap 을 따르는 문서 -->
<!-- bootrstap 을 따르는 문서 -->

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
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
				<a class="navbar-brand" href="index.html"> <img
					src="/img/logo_main.png">
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
			<h4>
				<b>마이페이지</b>
			</h4>
			<hr>
			<ul>
				<li>자기소개서 관리</li>
				<li>파일 관리</li>
				<li class="active">이력 관리</li>
			</ul>
		</aside>
		<!-- ./aside -->

		<section class="col">

			<article>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>
				<button type="button" class="topListBtn">수정하기</button>

				<!-- 기본정보 리스트 -->
				<div id="content1"></div>
				<br>
				<table width="100%">
					<tr>
						<td width="10%"><h4>
								<b>이름</b>
							</h4></td>
						<td width="40%" style="text-align: left;"><input type="text"
							name="name" value=""></td>
						<td width="15%"></td>
						<td width="35%"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" value=""></td>
						<td>성별</td>
						<td><input type="text" name="gender" value=""></td>
					</tr>
					<tr>
						<td>휴대폰</td>
						<td><input type="text" name="phone" value=""></td>
						<td>출생년도</td>
						<td><input type="text" name="birthday" value=""></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address" value=""></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<%--			  id="test" style= "visibility:hidden;"
		     글 작성 / 보기 / 수정 폼
	        <form id="frmWrite" name="frmWrite" method="get" target="iframe1">
	        	submit 후 이동 막는 방안 
	        	
	       		<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
				<div id="crud_form_box">
					<div id="content_text_box">
						<div id="content_text">
							<input type="text" placeholder="자소서 제목(필수)" name="title" required>
							<input type="text" placeholder="질문" name="question" value="">
							<textarea placeholder="내용" name="content"  value=""></textarea>					
						</div>
						<div id="content_text_plus"></div>
					</div> --%>

				<%-- 				 하단 버튼 : 글 조회 
					<div class="d01 btn_group_view">
						<button type="button" class="listBtn">목록</button>
						<button type="button" class="deleteBtn">자소서 삭제</button>
						<button type="button" class="updateBtn">자소서 수정</button>
					</div>
					
					 하단 버튼 : 글 작성 
					<div class="d01 btn_group_write">
						<button type="button" class="plusBtn">추가</button>					
						<button type="button" class="listBtn">목록</button>
						<button type="submit" >자소서 저장</button>
					</div>
					
					 하단 버튼 : 글 수정 
					<div class="d01 btn_group_update">
						<button type="button" class="plusBtn">추가</button>					
						<button type="button" class="updateCancelBtn">수정 취소</button>
						<button type="submit">자소서 저장</button>
					</div> 
				</div>
			</form>
					--%>
			</article>
			
			
			<!-- 학력사항 -->
			<article>
				<br>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>

				<!-- 자소서 목록 리스트 -->
				<div id="content2"></div>
				<table width="100%">
					<tr>
						<td width="25%"><button type="button" class="">초등학교
								졸업</button></td>
						<td width="25%"><button type="button" class="">중학교
								졸업</button></td>
						<td width="25%"><button type="button" class="">고등학교
								졸업</button></td>
						<td width="25%"><button type="button" class="">대학/대학원
								이상 졸업</button></td>
					</tr>
				</table>
				<%-- 초등학교 정보 --%>
				<table id="spec01" width="100%">
					<tr>
						<td width="80%" colspan="2"><h4><b>초등학교 정보입력</b></h4></td>
						<td width="20%"><button type="button" class="">저장</button></td>
					</tr>
					<tr>
						<td width="20%">학교명</td>
						<td width="60%"><input type="text" name="name" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">지역</td>
						<td width="60%"><input type="text" name="area" value=""></td>
						<td width="20%"></td>
					</tr>
				</table>
				
				<%-- 중학교 정보 --%>
				<table id="spec02" width="100%">
					<tr>
						<td width="80%" colspan="2"><h4><b>중학교 정보입력</b></h4></td>
						<td width="20%"><button type="button" class="">저장</button></td>
					</tr>
					<tr>
						<td width="20%">학교명</td>
						<td width="60%"><input type="text" name="name" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">지역</td>
						<td width="60%"><input type="text" name="area" value=""></td>
						<td width="20%"></td>
					</tr>
				</table>
				
				<%-- 고등학교 정보 --%>
				<table id="spec03" width="100%">
					<tr>
						<td width="80%" colspan="2"><h4><b>고등학교 정보입력</b></h4></td>
						<td width="20%"><button type="button" class="">저장</button></td>
					</tr>
					<tr>
						<td width="20%">학교명</td>
						<td width="60%"><input type="text" name="name" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">지역</td>
						<td width="60%"><input type="text" name="area" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">전공</td>
						<td width="60%"><input type="text" name="major" value=""></td>
						<td width="20%"></td>
					</tr>
				</table>
				
				<%-- 대학/대학원 정보 --%>
				<table id="spec04" width="100%">
					<tr>
						<td width="80%" colspan="2"><h4><b>대학∙대학원 정보입력</b></h4></td>
						<td width="20%"><button type="button" class="">저장</button></td>
					</tr>
					<tr>
						<td width="20%">대학</td>
						<td width="60%"><input type="text" name="cat" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">학교명</td>
						<td width="60%"><input type="text" name="name" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">지역</td>
						<td width="60%"><input type="text" name="area" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">전공계열</td>
						<td width="60%"><input type="text" name="major" value=""></td>
						<td width="20%"></td>
					</tr>
				</table>
			</article>


			<!-- 경력사항 -->
			<article>
				<br>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>

				<!-- 자소서 목록 리스트 -->
				<div id="content3"></div>

				<table width="100%">
					<tr>
						<td width="25%"><button type="button" class="">신입</button></td>
						<td width="25%"><button type="button" class="">경력</button></td>
					</tr>
				</table>
				<hr>
				<%-- 초등학교 정보 --%>
				<table id="spec01" width="100%">
					<tr>
						<td width="20%">회사명</td>
						<td width="60%"><input type="text" name="company" value="" placeholder="회사명을 입력하세요"></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">재직기간</td>
						<td width="70%"><input type="text" name="hiredate" value="">  ~  <input type="text" name="leavedate" value=""> <input type="text" name="now" value="" placeholder="퇴사&재직중 입력하세요"> </td>
						<td width="10%"></td>
					</tr>
					<tr>
						<td width="20%">퇴사사유</td>
						<td width="60%"><input type="text" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">직급/직책</td>
						<td width="60%"><input type="text" name="post" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">근무부서</td>
						<td width="60%"><input type="text" name="dept" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">지역</td>
						<td width="60%"><input type="text" name="area" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">연봉</td>
						<td width="60%"><input type="text" name="salary" value=""> <input type="text" name="manwon" value="만원"></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td width="20%">담당업무</td>
						<td width="60%"><input type="text" name="work" value=""></td>
						<td width="20%"></td>
					</tr>
					<tr>
						<td colspan="3"><hr></td>
					</tr>
					<tr>
						<td width="20%"></td>
						<td width="60%"></td>
						<td width="20%"><button type="button" class="">경력추가+</button></td>
					</tr>
				</table>
				
			</article>
		</section>

	</div>

	<footer >
		<div class="row">
			<div class="col-2">
				<img src="/img/logo_ contrast.png">
			</div>
			<div class="col-9">
				프로젝트명 : 채용 도우미 사이트 Resumy(리주마이)<br> 팀명 : 간개다모 (김진섭 팀장, 김민수,
				노수빈, 하병노)<br> 이메일 : seop1284@gmail.com (김진섭),
				kimpkoiw@gmail.com (김민수), binigy97@gmail.com (노수빈),
				hahbr88@gmail.com (하병노)
			</div>
			<div class="col">CopyrightⓒGangeadamo 2021-09-07 ~ 2021-10-15</div>
		</div>
	</footer>


</body>

</html>