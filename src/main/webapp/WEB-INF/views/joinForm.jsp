<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Resumy - 회원가입</title>
	<!-- 파비콘 -->
	<link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">
	<!-- FontAwesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<!-- Bootstrap CSS -->
	<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
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
    <!-- Bootsnipp -->
    <link href="/assets/bootsnipp/css/AlertGroup.css" rel="stylesheet">
    <!-- JQuery UI -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
 <body class="bg-light py-5">
	<div class="container shadow p-5">
		<!-- logo -->
	    <a href="/" class="d-block text-center"><img class="mb-4" src="/img/logo_shadow.png" alt="Resumy 로고" width="300"></a>
	    
		<form id="formJoin" class="mb-5" action="/joinOk" method="POST">
	    	<!-- id -->
	    	<div class="mb-3">
			  <label for="userid">아이디 <span class="text-danger">*</span></label>
			  <div class="input-group mb-2">
			    <input type="text" name="userid" class="form-control" id="userid" maxlength="16" placeholder="아이디 입력" required>
			    <button type="button" class="btn btn-primary ml-3" id="chkId" onclick="checkId()">중복확인</button>
			  </div>
			  <!-- alert -->
			  <div class="alert alert-danger alert-dismissable" id="chkIdErrorRE">
	            <strong style="font-size: 15px;">[아이디 형식 오류] </strong> 아이디는 8 ~ 16자리 사이로 입력하셔야 합니다.
	          </div>
			  <div class="alert alert-danger alert-dismissable" id="chkIdErrorExist">
	            <strong style="font-size: 15px;">[아이디 중복확인 오류] </strong> 존재하는 아이디입니다.
	          </div>
			  <div class="alert alert-danger alert-dismissable" id="chkIdErrorNull">
	            <strong style="font-size: 15px;">[아이디 중복확인 오류] </strong> 아이디를 입력한 후 중복확인 버튼을 눌러주세요.
	          </div>
			  <div class="alert alert-success alert-dismissable" id="chkIdSuccess">
	            <strong style="font-size: 15px;">[아이디 중복확인 성공] </strong> 입력하신 아이디는 사용할 수 있는 아이디입니다.
	          </div>
	    	</div>
	         
	        <!-- pw -->
	        <div class="mb-3">
	          <div class="mb-3" id="pwform">
			    <label for="password">비밀번호 <span class="text-danger">*</span></label>
	            <input type="password" class="form-control mb-1" id="password" placeholder="비밀번호 입력 (8 ~ 16자리)">
	            <input type="password" name="pw" class="form-control" id="chkPassword" placeholder="비밀번호 재입력">
	          </div>
	          <!-- alert -->
			  <div class="alert alert-danger alert-dismissable" maxlength="16" id="chkPwErrorRE"> <%-- RE : regular expression --%>
	            <strong style="font-size: 15px;">[비밀번호 형식 오류] </strong> 비밀번호는 8 ~ 16자리 사이로 입력하셔야 합니다.
	          </div>
			  <div class="alert alert-danger alert-dismissable" id="chkPwErrorNull">
	            <strong style="font-size: 15px;">[비밀번호 입력 오류] </strong> 위의 비밀번호 입력칸을 먼저 채워주세요.
	          </div>
			  <div class="alert alert-danger alert-dismissable" id="chkPwErrorFail">
	            <strong style="font-size: 15px;">[비밀번호 입력 오류] </strong> 입력하신 비밀번호와 맞지 않습니다.
	          </div>
			  <div class="alert alert-success alert-dismissable" id="chkPwSuccess">
	            <strong style="font-size: 15px;">[비밀번호 확인 성공] </strong> 입력하신 비밀번호는 사용할 수 있는 비밀번호입니다.
	          </div>
	        </div>
	        
	        <!-- name -->
	        <div class="mb-3">
			  <label for="password">이름 <span class="text-danger">*</span></label>
			  <input type="text" name="name" class="form-control" id="name" placeholder="이름을 입력해주세요">
	        </div>
	        
	        <!-- birth -->
	        <div class="mb-3">
	          <label for="year">생년월일 <span class="text-muted">(선택)</span> <span class="text-danger">*만 15세 이하는 가입이 불가능합니다.</span></label>
	          <div class="input-group">
	            <input type="text" name="birthday" class="form-control" id="datepicker">
	            <div class="input-group-prepend">
				  <span class="input-group-text"><i class="far fa-calendar-alt"></i></span>
				</div>
	          </div>
	        </div>
	         
			<!-- email -->
	        <div class="mb-3">
	          <label for="">이메일 <span class="text-danger">*</span></label>
		      <div class="input-group mb-2">
			    <input type="text" name="email" class="form-control" id="prefixEmail" placeholder="이메일" required>
			    <div class="input-group-prepend">
				  <span class="input-group-text">@</span>
				</div>
		        <select class="custom-select d-block" id="suffixEmail" required>
				  <option value="-1">선택하세요</option>
				  <option value="naver.com">naver.com</option>
				  <option value="daum.com">daum.com</option>
				  <option value="gmail.com">gmail.com</option>
				</select>
	            <button type="button" class="btn btn-primary ml-3" id="chkEmail" onclick="checkEmail()">인증하기</button>
	          </div>
		      <!-- email check message alert -->
			  <div class="alert alert-danger alert-dismissable" id="chkEmailErrorNull"> <%-- RE : regular expression --%>
	            <strong style="font-size: 15px;">[이메일 입력 오류] </strong> 인증가능한 이메일을 입력한 뒤 인증하기 버튼을 눌러주세요.
	          </div>
			</div>
			
			<div class="mb-3" id="chkEmailNumBox">
			  <label for="">이메일 인증하기</label>
			  <div class="input-group mb-2">
		        <input type="text" name="chkEmailNum" class="form-control mt-1" id="chkEmailNum" placeholder="인증번호 입력" required>
		        <button type="button" class="btn btn-primary ml-3" id="btn_chkEmailNum" onclick="checkEmailNum()">확인하기</button>
	          </div>
	          <!-- email check Success/Fail alert -->
			  <div class="alert alert-danger alert-dismissable" id="chkEmailNumErrorNull">
	            <strong style="font-size: 15px;">[이메일 인증 오류] </strong> 인증번호를 입력한 뒤 확인버튼을 눌러주세요.
	          </div>
			  <div class="alert alert-danger alert-dismissable" id="chkEmailNumFail">
	            <strong style="font-size: 15px;">[이메일 인증 오류] </strong> 인증이 실패했습니다.
	          </div>
			  <div class="alert alert-success alert-dismissable" id="chkEmailNumSuccess">
	            <strong style="font-size: 15px;">[이메일 확인 성공] </strong> 입력하신 이메일의 인증이 완료되었습니다.
	          </div>
			</div>
	    	
	    	<hr class="my-5">
	    	
	        <button class="btn btn-primary btn-lg btn-block" type="submit" id="btn_submit" disabled>회원가입</button>
	        
		</form>
	</div>
	<!-- Bootstrap -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="/assets/dist/js/bootstrap.min.js"></script>
	<!-- JQuery UI -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- My JS -->
	<script src="/JS/join.js"></script>
</body>
</html>