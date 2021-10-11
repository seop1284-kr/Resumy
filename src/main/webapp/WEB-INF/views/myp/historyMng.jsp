<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="/css/history.css" rel="stylesheet">


<!-- css -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="/assets/bootsnipp/css/AlertGroup.css" rel="stylesheet">
  
		
<div class="col">
	<div class="container-md">
		<article>
			<!-- 제목 -->
			<h4 id="title" style="font-weight: bold"></h4>

			<!-- 기본정보 리스트 -->
			<h4><b>기본정보</b></h4>
			<div class="basic">
				<form name='memberList' id='memberList' action='' method='post' >
					<table width="100%" >
						<tr>
							<td colspan="3"></td>
							<td style="text-align: right;"><button type="submit" style="width:80px;" class="btn btn-mint" >수정</button></td>
						</tr>
						<tr>
							<td width="10%">이름</td>
							<td width="40%" style="text-align: left;"><input type="text"
								name="name" required></td>
							<td width="10%"></td>
							<td width="40%"></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="text" name="email" value="" required></td>
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
							<td><input type="text" name="phone" value="" placeholder="'-'제외하고 숫자만입력" ></td>
							<td>생년월일</td>
							<td>
		            			<input  name="birthday" class="inputBox1 bg-white" id="historyDatepicker" readonly>
		          			</td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" name="address" class="inputBox3" value=""></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
		</article>
		
		
		<!-- 학력사항 -->
		<article>
			<br>
			<!-- 제목 -->
			<h4 id="title" style="font-weight: bold"></h4>

			<!-- 학력 목록 -->
			<h4><b>학력사항</b></h4>
				<table width="100%">
					<tr>
						<td width="25%"><button type="button" style="width:100%" class="btn btn-mint" id="btnSchool01" name="btnSchool01">초등학교
								졸업</button></td>
						<td width="25%"><button type="button" style="width:100%" class="btn btn-mint" id="btnSchool02" name="btnSchool02">중학교
								졸업</button></td>
						<td width="25%"><button type="button" style="width:100%" class="btn btn-mint" id="btnSchool03" name="btnSchool03">고등학교
								졸업</button></td>
						<td width="25%"><button type="button" style="width:100%" class="btn btn-mint" id="btnSchool04" name="btnSchool04">대학/대학원
								이상 졸업</button></td>
					</tr>
				</table>
				<hr>
				
				<%-- 초등학교 정보 --%>
				<form name='spec01' class="spec" id='spec01' onsubmit="return false;" method='post' height="300px">
					<table  width="100%" class="school">
						<tr>
							<td width="80%" class="title" colspan="2"><h5><b>초등학교 정보입력</b></h5></td>
							<td width="20%" class="btnWriteUpdate"><button type="submit" style="width:80px;" class="btn btn-mint" name ="btnWriteSpec01" id ="btnWriteSpec01" >등록</button> <button type="submit" style="width:80px;" class="btn btn-mint" id ="btnUpdateSpec01" name ="btnUpdateSpec01" >수정</button></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle">학교명</td>
							<td width="60%"><input type="text" id="school_01" name="name" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle" style="vertical-align:top;">지역</td>
							<td width="60%" style="vertical-align:top;"><input type="text"  id="schoolArea_01" name="area" value="" required></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_01" name="cat" value="">
				</form>
				
				<%-- 중학교 정보 --%>
				<form name='spec02' class="spec" id='spec02' onsubmit="return false;" method='post'>
					<table width="100%" class="school" >
						<tr>
							<td width="80%" class="title" colspan="2"><h5><b>중학교 정보입력</b></h5></td>
							<td width="20%" class="btnWriteUpdate"><button type="submit" style="width:80px;" class="btn btn-mint" id ="btnWriteSpec02" name ="btnWriteSpec02" >등록</button> <button type="submit" style="width:80px;" class="btn btn-mint" id ="btnUpdateSpec02" name ="btnUpdateSpec02" >수정</button></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle">학교명</td>
							<td width="60%"><input type="text" id="school_02" name="name" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle" style="vertical-align:top;">지역</td>
							<td width="60%" style="vertical-align:top;"><input type="text" id="schoolArea_02" name="area" value="" required></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_02" name="cat" value="">
				</form>
				
				<%-- 고등학교 정보 --%>
				<form name='spec03' class="spec" id='spec03' onsubmit="return false;" method='post'>
					<table  width="100%" class="school"  >
						<tr>
							<td width="80%" class="title" colspan="2"><h5><b>고등학교 정보입력</b></h5></td>
							<td width="20%" class="btnWriteUpdate"><button type="submit" style="width:80px;" class="btn btn-mint" id ="btnWriteSpec03" name ="btnWriteSpec03" >등록</button> <button type="submit" style="width:80px;" class="btn btn-mint" id ="btnUpdateSpec03" name ="btnUpdateSpec03" >수정</button></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle">학교명</td>
							<td width="60%"><input type="text" id="school_03"  name="name" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" height="20%" class="subtitle" style="vertical-align:top;">지역</td>
							<td width="60%" style="vertical-align:top;"><input type="text" id="schoolArea_03"  name="area" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%"  class="subtitle" style="vertical-align:top;">전공</td>
							<td width="60%" style="vertical-align:top;"><input type="text" id="major_03" name="major" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_03" name="cat" value="">
				</form>
				
				<%-- 대학/대학원 정보 --%>
				<form name='spec04' class="spec" id='spec04' onsubmit="return false;" method='post'>
					<table width="100%" class="school">
						<tr>
							<td width="80%" class="title" colspan="2"><h5><b>대학∙대학원 정보입력</b></h5></td>
							<td width="20%" class="btnWriteUpdate"><button type="submit" style="width:80px;" class="btn btn-mint" id ="btnWriteSpec04" name ="btnWriteSpec04" >등록</button> <button type="submit" style="width:80px;"  class="btn btn-mint" id ="btnUpdateSpec04" name ="btnUpdateSpec04" >수정</button></td>
						</tr>
						<tr>
							<td width="20%" height="20%" class="subtitle">대학</td>
							<td width="60%" height="20%">
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
							<td width="20%" height="15%" class="subtitle" style="vertical-align:top;">학교명</td>
							<td width="60%" height="15%" style="vertical-align:top;"><input type="text"  id ="school_04" name="name" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" height="15%" class="subtitle" style="vertical-align:top;">지역</td>
							<td width="60%" height="15%" style="vertical-align:top;"><input type="text"  id ="schoolArea_04" name="area" value="" required></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="20%" class="subtitle" style="vertical-align:top;">전공계열</td>
							<td width="60%" style="vertical-align:top;"><input type="text"  id ="major_04" name="major" value=""></td>
							<td width="20%"></td>
						</tr>
					</table>
					<input type="hidden" id="cat_04" name="cat" value="">
				</form>
		</article>
		<br>

		<!-- 경력사항 -->
		<article>
			<br>
			<!-- 제목 -->
			<h4 id="title" style="font-weight: bold"></h4>

			<!-- 경력사항 선택 -->
			<h4><b>경력사항</b></h4>
				<table width="100%">
					<tr>
						<td width="25%"><button style="width:50%" class="btn btn-mint active" type="button" class="" id="btnNewcomer">신입</button></td>
						<td width="25%"><button style="width:50%" class="btn btn-mint" type="button" class="" id="btnCareer">경력</button></td>
					</tr>
				</table>
				
			<hr>
			<%-- 경력정보 --%>
			<div id="careerContent"></div>
			<div id="careerPuls"></div>
			<br>
			<button type="button" class="plusBtn btn btn-mint" id="btnCareerPuls">경력추가+</button>

		</article>
		<!-- Jquery js-->
		<script src="/JS/historyMng.js"></script>
		
	</div>
</div>
