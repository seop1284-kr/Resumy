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
	
	<!-- ./navbar -->
		<div class="row">
		<!-- ./aside -->

		<section class="col">

			<article>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>
				<button type="button" class="topListBtn" onclick="updateMember('memberList')">수정하기</button>

				<!-- 기본정보 리스트 -->
				<div id="content1"></div>
				<br>
				<form name='memberList' id='memberList' action='' method='post'>
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
						<td>
							<select  id ="gender" name="gender">
									<option value="">선택하세요</option>
		           					<option value="1">남</option>
		           					<option value="2">여</option>
							</select>
						</td>
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
				</form>
			</article>
			
			
			<!-- 학력사항 -->
			<article>
				<br>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>

				<!-- 학력 목록 -->
				<div id="content2"></div>
				<table width="100%">
					<tr>
						<td width="25%"><button type="button" class="" id="btnSchool01" name="btnSchool01">초등학교
								졸업</button></td>
						<td width="25%"><button type="button" class="" id="btnSchool02" name="btnSchool02">중학교
								졸업</button></td>
						<td width="25%"><button type="button" class="" id="btnSchool03" name="btnSchool03">고등학교
								졸업</button></td>
						<td width="25%"><button type="button" class="" id="btnSchool04" name="btnSchool04">대학/대학원<br>
								이상 졸업</button></td>
					</tr>
				</table>
				
				
				<%-- 초등학교 정보 --%>
				<form name='spec01' id='spec01' action='' method='post'>
					<table  width="100%">
						<tr>
							<td width="80%" colspan="2"><h4><b>초등학교 정보입력</b></h4></td>
							<td width="20%"><button type="button" class="" name ="btnWriteSpec01" id ="btnWriteSpec01" onclick="writeSpec('spec01')">등록</button> <button type="button" class="" id ="btnUpdateSpec01" name ="btnUpdateSpec01" onclick="updateSpec('spec01')">수정</button></td>
						</tr>
						<tr>
							<td width="20%" name="school01">학교명</td>
							<td width="60%"><input type="text" id="school_01" name="name" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" name="schoolArea01">지역</td>
							<td width="60%"><input type="text" id="schoolArea_01" name="area" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_01" name="cat" value="">
				</form>
				
				<%-- 중학교 정보 --%>
				<form name='spec02' id='spec02' action='' method='post'>
					<table width="100%">
						<tr>
							<td width="80%" colspan="2"><h4><b>중학교 정보입력</b></h4></td>
							<td width="20%"><button type="button" class="" id ="btnWriteSpec02" name ="btnWriteSpec02" onclick="writeSpec('spec02')">등록</button> <button type="button" class="" id ="btnUpdateSpec02" name ="btnUpdateSpec02" onclick="updateSpec('spec02')">수정</button></td>
						</tr>
						<tr>
							<td width="20%" name="school02">학교명</td>
							<td width="60%"><input type="text" id="school_02" name="name" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" name="schoolArea02">지역</td>
							<td width="60%"><input type="text" id="schoolArea_02" name="area" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_02" name="cat" value="">
				</form>
				
				<%-- 고등학교 정보 --%>
				<form name='spec03' id='spec03' action='' method='post'>
					<table  width="100%">
						<tr>
							<td width="80%" colspan="2"><h4><b>고등학교 정보입력</b></h4></td>
							<td width="20%"><button type="button" class="" id ="btnWriteSpec03" name ="btnWriteSpec03" onclick="writeSpec('spec03')">등록</button> <button type="button" class="" id ="btnUpdateSpec03" name ="btnUpdateSpec03" onclick="updateSpec('spec03')">수정</button></td>
						</tr>
						<tr>
							<td width="20%">학교명</td>
							<td width="60%"><input type="text" id="school_03"  name="name" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%">지역</td>
							<td width="60%"><input type="text" id="schoolArea_03"  name="area" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%">전공</td>
							<td width="60%"><input type="text" id="major_03" name="major" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_03" name="cat" value="">
				</form>
				
				<%-- 대학/대학원 정보 --%>
				<form name='spec04' id='spec04' action='' method='post'>
					<table width="100%">
						<tr>
							<td width="80%" colspan="2"><h4><b>대학∙대학원 정보입력</b></h4></td>
							<td width="20%"><button type="button" class="" id ="btnWriteSpec04" name ="btnWriteSpec04" onclick="writeSpec('spec04')">등록</button> <button type="button" class="" id ="btnUpdateSpec04" name ="btnUpdateSpec04" onclick="updateSpec('spec04')">수정</button></td>
						</tr>
						<tr>
							<td width="20%">대학</td>
							<td width="60%">
								<select  id ="university_04" name="university" >
									<option value="">선택하세요</option>
		           					<option value="01">2,3년제</option>
		           					<option value="02">4년제</option>
		            				<option value="03">대학원(석사)</option>
		            				<option value="04">대학원(박사)</option>
								</select>
							</td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%">학교명</td>
							<td width="60%"><input type="text"  id ="school_04" name="name" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%">지역</td>
							<td width="60%"><input type="text"  id ="schoolArea_04" name="area" value=""></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%">전공계열</td>
							<td width="60%"><input type="text"  id ="major_04" name="major" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_04" name="cat" value="">
				</form>
			</article>


			<!-- 경력사항 -->
			<article>
				<br>
				<!-- 제목 -->
				<h4 id="title" style="font-weight: bold"></h4>

				<!-- 경력사항 선택 -->
				<div id="content3"></div>

				<table width="100%">
					<tr>
						<td width="25%"><button type="button" class="" id="btnNewcomer">신입</button></td>
						<td width="25%"><button type="button" class="" id="btnCareer">경력</button></td>
					</tr>
				</table>
				<hr>
				<%-- 경력정보 --%>
				<div id="careerContent"></div>
				<div id="careerPuls"></div>
				<button type="button" class="" id="btnCareerPuls">경력추가+</button>
			</article>
		</section>

	</div>

	
</body>

</html>